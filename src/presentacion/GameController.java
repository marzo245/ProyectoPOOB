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
								+ GomokuGUI.gomoku.getPlayer2().getScore());
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
		if (GomokuGUI.gomoku.getSeEncontroGanador()) {
			cellDisableClick();
			notifyWinner();

			if (Gomoku.getInstance().getModoDeJuego() instanceof ModoLimiteTiempo) {
				TimerDownComponent.getTimer().stop();
				TimerDownComponent.getTimer2().stop();
			} else {
				TimerComponent.getTimer().stop();
				TimerComponent.getTimer2().stop();
			}

			int[][] posiciones = GomokuGUI.gomoku.getPosicionesGanadoras();

			// Recorre las posiciones ganadoras y marca las celdas correspondientes como
			// ganadoras
			for (int i = 0; i < posiciones[0].length; i++) {
				int row = posiciones[0][i];
				int col = posiciones[1][i];

				// Supongamos que miBoard tiene un método setCeldaOfWin que establece
				// isCellOfWin(true)
				GomokuGUI.getBoardVisual().getCells()[row][col].setIsCellOfWin(true);
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
		GomokuGUI.gomoku.setSeEncontroGanador(false);
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

}
