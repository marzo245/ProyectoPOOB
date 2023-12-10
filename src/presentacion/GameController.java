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

	private final static ComputerPlayer computerPlayer = new ComputerPlayer();
	private static boolean isPlayWithComputer = false;
	private static boolean isPlayOnlyComputer = false;
	private static boolean winnerFound = false;

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
						+ numStep + " | Puntaje: " + Gomoku.getPlayer1().getScore());
			} else {
				JLabel info = GomokuGUI.getInfoComponent().getCurrentPlayer();
				info.setText("Turno: " + GomokuGUI.getSecondName()
						+ " |   Color ficha: Blanca  | Total movimientos: "
						+ numStep + " | Puntaje: " + Gomoku.getPlayer2().getScore());
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
			System.out.println("Anterior turno: " + Gomoku.getTurno());
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
		for (int i = 0; i < NUMBER_DIRECTION; i += 2) {
			// La coordenada en sí ya tiene una piedra.
			int counter = 1;
			rowOfWin.clear();
			colOfWin.clear();
			rowOfWin.add(row);
			colOfWin.add(col);

			counter += countColor(row + DELTA_X[i], col + DELTA_Y[i], i);
			counter += countColor(row + DELTA_X[i + 1], col + DELTA_Y[i + 1],
					i + 1);

			if (counter >= 5) {
				counter = 0;
				for (int j = 0; j < rowOfWin.size(); j++) {
					BoardComponent.getCells()[rowOfWin.get(j)][colOfWin.get(j)].setIsCellOfWin(true);
				}
				winnerFound = true;
				cellDisableClick();
				notifyWinner();
				TimerComponent.getTimer().stop();
				return;
			}
		}
	}

	/**
	 * Método para contar la cantidad de piedras consecutivas en sentido horizontal,
	 * vertical o diagonal, siempre y cuando las coordenadas verificadas aún estén
	 * en el tablero y el color de la piedra en esas coordenadas sea del jugador
	 * que está en su turno.
	 * 
	 * @param row
	 *              índice de fila
	 * @param col
	 *              índice de columna
	 * @param index
	 *              índice para los arrays DELTA_X y DELTA_Y
	 *
	 */
	public int countColor(int row, int col, int index) {
		int counter = 0;

		while (stillOnBoard(row, col)
				&& BoardComponent.getCells()[row][col].getColor() == lastColor) {
			counter++;
			rowOfWin.add(row);
			colOfWin.add(col);
			row += DELTA_X[index];
			col += DELTA_Y[index];
			if (!stillOnBoard(row, col)) {
				break;
			}
		}

		return counter;
	}

	/**
	 * Método para verificar si el índice dado todavía está dentro del tablero.
	 * 
	 * @param x
	 *          índice de fila
	 * @param y
	 *          índice de columna
	 * @return true si las coordenadas aún están dentro del tablero
	 * @return false si no es así
	 *
	 */
	public static boolean stillOnBoard(int x, int y) {
		if (x >= 0 && x <= 18 && y >= 0 && y <= 18) {
			return true;
		}
		return false;
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
		String winner = lastColor == Cell.BLACK ? "black" : "white";
		String name = "";

		if (winner.equals("black")) {
			name = GomokuGUI.getFirstName();
			ScoreComponent.updateScore(name, numStep);
		}

		else if (!isPlayWithComputer) {
			name = GomokuGUI.getSecondName();
			ScoreComponent.updateScore(name, numStep);
		}
		JLabel info = GomokuGUI.getInfoComponent().getCurrentPlayer();
		if (winner.equals("white") && isPlayWithComputer) {
			info.setText("Ouch! Perdiste :(!");
		} else {
			info.setText("Bravo! " + name + " ganó con " + numStep + " movimientos totales! :)");
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
		winnerFound = false;
		rowOfWin.clear();
		colOfWin.clear();
		TimerComponent.getTimer().stop();
		TimerComponent.resetTimer();
		Gomoku.getInstance().crearBoard(3, 3);
		GomokuGUI.getBoardComponent().clearBoard();
		GomokuGUI.getInfoComponent().clearInfo();
	}

	/*
	 * metodo definir si se juega con la computadora o no
	 */
	public static void setIsPlayWithComputer(Boolean value) {
		isPlayWithComputer = value;
	}

	/*
	 * metodo definir si se juega solo con la computadora o no
	 */
	public static void setIsPlayOnliComputer(Boolean value) {
		isPlayOnlyComputer = value;
	}

	/*
	 * metodo para obtener el valor de la variable isPlayWithComputer
	 */
	public static boolean getIsPlayWithComputer() {
		return isPlayWithComputer;
	}

	/*
	 * metodo para obtener el valor de la variable isPlayOnlyComputer
	 */
	public static boolean getPlayOnliComputer() {
		return isPlayOnlyComputer;
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

	private void handleEmptyCell() {
		numStep++;

		if (lastColor == Cell.BLACK) {
			cell.setColor(Cell.WHITE);
			GomokuGUI.getBoardComponent().repaint();
			lastColor = Cell.WHITE;
			JLabel info = GomokuGUI.getInfoComponent().getCurrentPlayer();
			info.setText("Turno: " + GomokuGUI.getFirstName()
					+ " |   Color ficha: Negra  | Total movimientos: "
					+ numStep);
		} else {
			cell.setColor(Cell.BLACK);
			GomokuGUI.getBoardComponent().repaint();
			lastColor = Cell.BLACK;
			JLabel info = GomokuGUI.getInfoComponent().getCurrentPlayer();
			info.setText("Turno: " + GomokuGUI.getSecondName()
					+ " |   Color ficha: Blanca | Total movimientos: "
					+ numStep);
		}

		checkWinner(cell.getRow(), cell.getCol());
		checkCellAvailability();

		if (!winnerFound && isPlayWithComputer) {

			numStep++;

			computerPlayer.play();
			lastColor = Cell.WHITE;

			JLabel info = GomokuGUI.getInfoComponent().getCurrentPlayer();
			info.setText("Turno: " + GomokuGUI.getFirstName()
					+ " | Color ficha: Negra | Total movimientos: "
					+ numStep);

			checkWinner(computerPlayer.getRow(), computerPlayer.getCol());
			checkCellAvailability();
		}
	}

	private void handleTeleportCell() {
		int randomRow, randomCol;
		do {
			randomRow = (int) (Math.random() * (Board.HEIGHT - 1));
			randomCol = (int) (Math.random() * (Board.WIDTH - 1));
		} while (BoardComponent.getCells()[randomRow][randomCol].getColor() != Cell.EMPTY);

		numStep++;

		if (lastColor == Cell.BLACK) {

			cell.setColor(Cell.EMPTY);
			BoardComponent.getCells()[randomRow][randomCol].setColor(Cell.WHITE);

			GomokuGUI.getBoardComponent().repaint();
			lastColor = Cell.WHITE;
			JLabel info = GomokuGUI.getInfoComponent().getCurrentPlayer();
			info.setText("Turno: " + Gomoku.getPlayer1().getName()
					+ " |   Color ficha: Negra  | Total movimientos: "
					+ numStep);
		}else {
			cell.setColor(Cell.EMPTY);
			BoardComponent.getCells()[randomRow][randomCol].setColor(Cell.BLACK);
			GomokuGUI.getBoardComponent().repaint();
			lastColor = Cell.BLACK;
			JLabel info = GomokuGUI.getInfoComponent().getCurrentPlayer();
			info.setText("Turno: " + Gomoku.getPlayer2().getName()
					+ " |   Color ficha: Negra | Total movimientos: "
					+ numStep);
		}
		
		checkWinner(randomRow, randomCol);
		checkCellAvailability();

		if (!winnerFound && isPlayWithComputer) {
			numStep++;
			computerPlayer.play();
			lastColor = Cell.WHITE;
			JLabel info = GomokuGUI.getInfoComponent().getCurrentPlayer();
			info.setText("Turno: " + GomokuGUI.getFirstName()
					+ " | Color ficha: Negra | Total movimientos: "
					+ numStep);
					
			checkWinner(computerPlayer.getRow(), computerPlayer.getCol());
			checkCellAvailability();
		}

	}

	private void handleMineCell() {
		for (int i = Math.max(0, cell.getRow() - 1); i <= Math.min(Board.HEIGHT - 1, cell.getRow() + 1); i++) {
			for (int j = Math.max(0, cell.getCol() - 1); j <= Math.min(Board.WIDTH - 1, cell.getCol() + 1); j++) {
				BoardComponent.getCells()[i][j].setColor(Cell.EMPTY);
				BoardComponent.getCells()[i][j].setEnableClick(true);
			}
		}
		if (lastColor == Cell.BLACK) {
			lastColor = Cell.WHITE;
			cell.setColor(Cell.WHITE);
			JLabel info = GomokuGUI.getInfoComponent().getCurrentPlayer();
			info.setText("Turno: " + GomokuGUI.getFirstName()
					+ " |   Color ficha: Negra  | Total movimientos: "
					+ numStep);
		} else {
			lastColor = Cell.BLACK;
			cell.setColor(Cell.BLACK);
			JLabel info = GomokuGUI.getInfoComponent().getCurrentPlayer();
			info.setText("Turno: " + GomokuGUI.getSecondName()
					+ " |   Color ficha: Blanca | Total movimientos: "
					+ numStep);
		}

		GomokuGUI.getBoardComponent().repaint();
		JOptionPane.showMessageDialog(null,
				"¡Has activado la celda de Mina!\n"
						+ "Todas las celdas adyacentes han sido eliminadas.",
				"Celda de Mina", JOptionPane.INFORMATION_MESSAGE);

	}

	private void handleGoldenCell() {
		GomokuGUI.getBoardComponent().repaint();
		JOptionPane.showMessageDialog(null,
				"¡Has activado la celda de Golden!\n"
						+ ".",
				"Celda de Mina", JOptionPane.INFORMATION_MESSAGE);
	}

}