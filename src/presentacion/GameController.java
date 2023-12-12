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
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import dominio.*;

public class GameController implements ActionListener {

	private CellComponent cell;
	private static Cell lastColor;
	private static int numStep = 0;

	/**
	 * ArrayList para almacenar las filas y columnas de las fichas que forman
	 * la disposición ganadora.
	 */
	private static ArrayList<Integer> rowOfWin = new ArrayList<Integer>();
	private static ArrayList<Integer> colOfWin = new ArrayList<Integer>();

	// Arrays para almacenar cambios en filas y columnas para verificar el orden
	// de colores en el tablero.
	public final static int[] DELTA_X = { -1, 1, -1, 1, 0, 0, -1, 1 };
	public final static int[] DELTA_Y = { -1, 1, 1, -1, 1, -1, 0, 0 };
	public final static int NUMBER_DIRECTION = 8;

	/**
	 * 
	 * Constructor de la clase GameController.
	 * 
	 * @param cell
	 */
	public GameController(CellComponent cell) {
		this.cell = cell;
		lastColor = Cell.EMPTY;
	}

	/**
	 * 
	 * El método actionPerformed() contiene los pasos que deben realizarse
	 * cuando se hace clic en una celda en el tablero.
	 * 
	 */
	public void actionPerformed(ActionEvent event) {
		if (cell.getEnableClick()) {
			if (Gomoku.getTurno().equals("Blanca")) {
				JLabel info = GomokuGUI.getInfoComponent().getCurrentPlayer();
				info.setText("Turno: " + GomokuGUI.getFirstName()
						+ " |   Color ficha: Negra  | Total movimientos: "
						+ numStep + " | Puntaje: " + Gomoku.getInstance().getPlayer1().getScore());
				System.out.println(Gomoku.getInstance().getPlayer1().getScore());
			} else {
				JLabel info = GomokuGUI.getInfoComponent().getCurrentPlayer();
				info.setText("Turno: " + GomokuGUI.getSecondName()
						+ " |   Color ficha: Blanca  | Total movimientos: "
						+ numStep + " | Puntaje: " + Gomoku.getInstance().getPlayer2().getScore());
				System.out.println(Gomoku.getInstance().getPlayer2().getScore());
			}

			// if (cell.getColor() == Cell.EMPTY) {
			Celda[][] temp = GomokuGUI.gomoku.getInstance().jugada(cell.getRow(), cell.getCol());
			for (int i = 0; i < Board.WIDTH; i++) {
				for (int j = 0; j < Board.HEIGHT; j++) {
					if (temp[i][j] instanceof Ocupada) {
						if (temp[i][j].getPiedra().getName().equals("Blanca")) {
							lastColor = Cell.BLACK;
							GomokuGUI.getBoardComponent().getCells()[i][j].setColor(Cell.WHITE);
						} else {
							lastColor = Cell.WHITE;
							GomokuGUI.getBoardComponent().getCells()[i][j].setColor(Cell.BLACK);
						}
					} else if (temp[i][j] instanceof Mina) {
						GomokuGUI.getBoardComponent().getCells()[i][j].setColor(Cell.MiINE);
						GomokuGUI.getBoardComponent().getCells()[i][j].setEnableClick(true);
					} else if (temp[i][j] instanceof Teleport) {
						GomokuGUI.getBoardComponent().getCells()[i][j].setColor(Cell.TELEPORT);
						GomokuGUI.getBoardComponent().getCells()[i][j].setEnableClick(true);
					} else if (temp[i][j] instanceof Vacia) {
						GomokuGUI.getBoardComponent().getCells()[i][j].setColor(Cell.EMPTY);
						GomokuGUI.getBoardComponent().getCells()[i][j].setEnableClick(true);

					} else {
						GomokuGUI.getBoardComponent().getCells()[i][j].setEnabled(true);
					}

				}
			}

			GomokuGUI.getBoardComponent().repaint();
			checkWinner(cell.getRow(), cell.getCol());
			checkCellAvailability();
			if (Gomoku.getInstance().getHayMensaje()) {
				JOptionPane.showMessageDialog(null,
						Gomoku.getInstance().getMensaje(),
						"Evento", JOptionPane.INFORMATION_MESSAGE);
				Gomoku.getInstance().setHayMensjae(false);
			}
			Gomoku.getInstance().imprimirTablero();
		} else {
			JOptionPane.showMessageDialog(null,
					"Celda ocupada.\nPor favor vuelva a elegir otra celda!",
					"Advertencia", JOptionPane.WARNING_MESSAGE);
		}

	}

	/**
	 * Método para verificar si la cantidad alrededor de la piedra colocada por
	 * el jugador en su turno es igual a cinco. Si es así, se encuentra al
	 * ganador, todas las celdas se desactivan y se anuncia el ganador, luego
	 * se detiene el temporizador del juego.
	 * 
	 * @param row
	 *            índice de fila
	 * @param col
	 *            índice de columna
	 *
	 */
	public void checkWinner(int row, int col) {
		if (Gomoku.getInstance().getSeEncontroGanador()) {
			cellDisableClick();
			cell.setIsCellOfWin(true);
			notifyWinner();
			TimerComponent.getTimer().stop();
			int[][] win = Gomoku.getInstance().getPosicionesGanadoras();
			for (int i = 0; i < 5; i++) {
				BoardComponent.getCells()[win[0][i]][win[1][i]].setIsCellOfWin(true);
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
				BoardComponent.getCells()[i][j].setEnableClick(false);
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
			info.setText("Bravo! " + GomokuGUI.getFirstName() + " ganó con " + numStep + " movimientos totales! :)");
		} else {
			info.setText("Bravo! " + GomokuGUI.getSecondName() + " ganó con " + numStep + " movimientos totales! :");
		}
	}

	/**
	 * 
	 * method ini dipanggil ketika perminan dimulai ulang,
	 * method ini mensetting nilai-nilai serta tampilan kembali
	 * ke semula sebelum permainan dimulai
	 * 
	 */
	public static void restartGame() {
		numStep = 0;
		rowOfWin.clear();
		colOfWin.clear();
		TimerComponent.getTimer().stop();
		TimerComponent.resetTimer();
		Gomoku.getInstance().crearBoard(3, 3);
		GomokuGUI.getBoardComponent().clearBoard();
		Gomoku.getInstance().setSeEncontroGanador(false);
		GomokuGUI.getInfoComponent().clearInfo();
	}

	/*
	 * metodo para obtener el valor de la variable numStep
	 */
	public static int getNumStep() {
		return numStep;
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
				if (BoardComponent.getCells()[i][j].getColor() == Cell.EMPTY) {
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
