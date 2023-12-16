/**
 * 
 * Esta clase controla todas las reglas y actividades que ocurren durante el juego.
 * 
 * @author Chicuazuque-Sierra
 * @version 1.0 25/11/2023
 * 
 */
package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import dominio.*;

public class GameController implements ActionListener {

	private CellComponent cell;
	private static String tipoDePiedra = "Piedra pesada";

	/**
	 * 
	 * Constructor de la clase GameController.
	 * 
	 * @param cell
	 */
	public GameController(CellComponent cell) {
		this.cell = cell;
	}

	/**
	 * 
	 * El método actionPerformed() contiene los pasos que deben realizarse
	 * cuando se hace clic en una celda en el tablero.
	 * 
	 */
	public void actionPerformed(ActionEvent event) {
		if (cell.getEnableClick()) {
			if (TypeOfRock.getElijioTipoDePiedra()) {
				Celda[][] temp = GomokuGUI.gomoku.jugada(cell.getRow(), cell.getCol(), tipoDePiedra);
				for (int i = 0; i < Board.WIDTH; i++) {
					for (int j = 0; j < Board.HEIGHT; j++) {
						if (temp[i][j] instanceof Ocupada) {
							cell.setTipoDePiedra(tipoDePiedra);
							if (temp[i][j].getPiedra().getName().equals("Blanca")) {
								GomokuGUI.getBoardVisual().getCells()[i][j].setColor(Cell.WHITE);

							} else {
								GomokuGUI.getBoardVisual().getCells()[i][j].setColor(Cell.BLACK);
							}
						} else if (temp[i][j] instanceof Mina) {
							GomokuGUI.getBoardVisual().getCells()[i][j].setColor(Cell.MINE);
							GomokuGUI.getBoardVisual().getCells()[i][j].setEnableClick(true);
						} else if (temp[i][j] instanceof Teleport) {
							GomokuGUI.getBoardVisual().getCells()[i][j].setColor(Cell.TELEPORT);
							GomokuGUI.getBoardVisual().getCells()[i][j].setEnableClick(true);
						} else if (temp[i][j] instanceof Vacia) {
							GomokuGUI.getBoardVisual().getCells()[i][j].setColor(Cell.EMPTY);
							GomokuGUI.getBoardVisual().getCells()[i][j].setEnableClick(true);

						} else {
							GomokuGUI.getBoardVisual().getCells()[i][j].setEnabled(true);
						}

					}
				}
				if (Gomoku.getTurno().equals("Blanca")) {
					JLabel info = GomokuGUI.getInfoComponent().getCurrentPlayer();
					info.setText("Turno: " + GomokuGUI.getFirstName()
							+ " |   Color ficha: Blanca | Puntaje: " + Gomoku.getInstance().getPlayer1().getScore());
					if (Gomoku.getInstance().getModoDeJuego() instanceof ModoLimiteTiempo) {
						TimerDownComponent.switchToTimer2();
					} else {
						TimerComponent.switchToTimer2();
					}
				} else {
					if (Gomoku.getPlayer2() instanceof HumanoPlayer) {
						JLabel info = GomokuGUI.getInfoComponent().getCurrentPlayer();
						info.setText("Turno: " + GomokuGUI.getSecondName()
								+ " |   Color ficha: Negra   | Puntaje: "
								+ Gomoku.getInstance().getPlayer2().getScore());
						if (Gomoku.getInstance().getModoDeJuego() instanceof ModoLimiteTiempo) {
							TimerDownComponent.switchToTimer1();
						} else {
							TimerComponent.switchToTimer1();
						}
					}
				}

				GomokuGUI.getBoardVisual().repaint();
				checkCellAvailability();
				checkWinneroffTime();
				if (Gomoku.getInstance().getHayMensaje()) {
					JOptionPane.showMessageDialog(null,
							Gomoku.getInstance().getMensaje(),
							"Evento", JOptionPane.INFORMATION_MESSAGE);
					Gomoku.getInstance().setHayMensjae(false);
				}
				TypeOfRock.actualizarTextoBotones();
				TypeOfRock.setElijioTipoDePiedra(false);
			} else {
				JOptionPane.showMessageDialog(null,
						"Por favor elija el tipo de piedra que desea jugar!",
						"Advertencia", JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"Celda ocupada.\nPor favor vuelva a elegir otra celda!",
					"Advertencia", JOptionPane.WARNING_MESSAGE);
		}

	}

	/**
	 * 
	 * Método getter para obtener el tipo de piedra que se está utilizando.
	 * 
	 * @return Tipo de piedra que se está utilizando.
	 */
	public static void setPiedra(String newPiedra) {
		tipoDePiedra = newPiedra;
	}

	public void checkWinneroffTime() {
		if (Gomoku.getInstance().getSeEncontroGanador()) {
			cellDisableClick();
			cell.setIsCellOfWin(true);
			notifyWinner();
			if (Gomoku.getInstance().getModoDeJuego() instanceof ModoLimiteTiempo) {
				TimerDownComponent.getTimer().stop();
				TimerDownComponent.getTimer2().stop();
			} else {
				TimerComponent.getTimer().stop();
				TimerComponent.getTimer2().stop();
			}
			int[][] win = Gomoku.getInstance().getPosicionesGanadoras();
			for (int i = 0; i < win.length; i++) {
				BoardVisual.getCells()[win[0][i]][win[1][i]].setIsCellOfWin(true);
				System.out.print(win[0][i] + " " + win[1][i] + " ");
			}

		}
	}

	/**
	 * 
	 * Este método deshabilita la capacidad de hacer clic en todas las celdas
	 * después de que se haya encontrado un ganador en el juego.
	 * 
	 */
	public void cellDisableClick() {
		for (int i = 0; i < Board.HEIGHT; i++) {
			for (int j = 0; j < Board.WIDTH; j++) {
				BoardVisual.getCells()[i][j].setEnableClick(false);
			}

		}
	}

	/**
	 * 
	 * Este método anuncia al ganador y lo muestra en el JLabel de la clase
	 * InfoComponent().
	 * 
	 */
	public void notifyWinner() {
		JLabel info = GomokuGUI.getInfoComponent().getCurrentPlayer();
		if (Gomoku.getInstance().getGanador().equals("Negra")) {
			info.setText("Bravo! " + GomokuGUI.getFirstName() + " ganó:)");
		} else {
			info.setText("Bravo! " + GomokuGUI.getSecondName() + " ganó :");
		}
	}

	/**
	 * 
	 * metodo Que reinicia el juego
	 * 
	 */
	public static void restartGame() {
		Gomoku.getInstance().setHayMensjae(false);
		Gomoku.getInstance().getPlayer1().setPiedrasPesadas(5);
		Gomoku.getInstance().getPlayer1().setPiedrasLigeras(5);
		Gomoku.getInstance().getPlayer2().setPiedrasPesadas(5);
		Gomoku.getInstance().getPlayer2().setPiedrasLigeras(5);
		if (Gomoku.getInstance().getModoDeJuego() instanceof ModoLimiteTiempo) {
			TimerDownComponent.getTimer().stop();
			TimerDownComponent.getTimer2().stop();
			TimerDownComponent.resetTimer();
		} else {
			TimerComponent.getTimer().stop();
			TimerComponent.getTimer2().stop();
			TimerComponent.resetTimer();
		}
		GomokuGUI.getBoardVisual().clearBoard();
		Gomoku.getInstance().setSeEncontroGanador(false);
		GomokuGUI.getInfoComponent().clearInfo();
		TypeOfRock.actualizarTextoBotones();
	}

	/**
	 * 
	 * methodo para verificar si todas las celdas estan llenas
	 * 
	 */
	public void checkCellAvailability() {
		boolean cellAvailable = false;
		for (int i = 0; i < Board.HEIGHT; i++) {
			for (int j = 0; j < Board.WIDTH; j++) {
				if (BoardVisual.getCells()[i][j].getColor() == Cell.EMPTY) {
					cellAvailable = true;
				}
			}
		}
		if (!cellAvailable) {
			cellDisableClick();
			JOptionPane.showMessageDialog(null,
					"Draw, all cells are filled.",
					"Draw", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public static void guardarEstadoJuego() {
		try{
		JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter(null, "Archivos de datos (*.dat)", "dat"));
            int seleccion = fileChooser.showSaveDialog(null);
			if (seleccion == JFileChooser.APPROVE_OPTION) {
                File archivo = fileChooser.getSelectedFile();
        

        // Obtener la puntuación actual de los jugadores
        int puntuacionJugador1 = Gomoku.getInstance().getPlayer1().getScore();
        int puntuacionJugador2 = Gomoku.getInstance().getPlayer2().getScore();

            // Crear un BufferedWriter para escribir en el archivo
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));

            // Escribir la información en el archivo
            writer.write("Puntuación Jugador 1: " + puntuacionJugador1 + "\n");
            writer.write("Puntuación Jugador 2: " + puntuacionJugador2 + "\n");

            // Cerrar el BufferedWriter
            writer.close();

            // Mensaje de confirmación
            JOptionPane.showMessageDialog(null,
                    "El estado del juego se ha guardado exitosamente en " + archivo,
                    "Guardar juego", JOptionPane.INFORMATION_MESSAGE);
        }
	} catch (IOException e) {
            // Manejar cualquier error al escribir el archivo
            JOptionPane.showMessageDialog(null,
                    "Error al guardar el estado del juego: " + e.getMessage(),
                    "Guardar juego", JOptionPane.ERROR_MESSAGE);
        }
    }

}
