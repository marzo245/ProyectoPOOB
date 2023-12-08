/**
 * 
 * Esta clase está diseñada para determinar qué celda es la más ventajosa
 * al calcular la situación de las celdas circundantes. De esta manera, la
 * computadora puede decidir su siguiente movimiento colocando una ficha en
 * esa celda. Se prioriza bloquear al jugador para evitar que forme
 * sus fichas en una disposición ganadora. Si no hay movimientos defensivos,
 * la computadora intenta encontrar una celda para formar sus propias fichas.
 * 
 * @author Chicuazuque-Sierra
 * @version 1.0 25/11/2023
 * 
 */
package presentacion;

import java.util.Random;

import dominio.Board;

public class ComputerPlayer {

	public static final int DELTA_X[] = { -1, 1, -1, 1, 0, 0, -1, 1 };
	public static final int DELTA_Y[] = { -1, 1, 1, -1, 1, -1, 0, 0 };
	public static final int NUMBER_DIRECTION = 8;
	/*
	 * La variable "profit" se utiliza para almacenar las ganancias obtenidas
	 * de cada celda.
	 */
	private static int profit[][];
	private static int rowMaxProfit;
	private static int colMaxProfit;
	/*
	 * La variable "countProfitWhiteDone" indica si el proceso de cálculo de
	 * ganancias para las celdas que contienen fichas blancas o fichas de la
	 * computadora ya ha sido realizado o no.
	 */
	private static boolean countProfitWhiteDone;

	/**
	 * 
	 * Constructor de la clase ComputerPlayer.
	 * 
	 */
	ComputerPlayer() {
		profit = new int[Board.HEIGHT][Board.WIDTH];
		resetProfit();

		rowMaxProfit = 0;
		colMaxProfit = 0;

		countProfitWhiteDone = false;
	}

	/**
	 * 
	 * Este método restablece el valor de ganancia para cada celda a 0.
	 * 
	 */
	public void resetProfit() {
		for (int i = 0; i < Board.HEIGHT; i++) {
			for (int j = 0; j < Board.WIDTH; j++) {
				profit[i][j] = 0;
			}
		}
	}

	/**
	 * 
	 * Este método calcula las ganancias para cada celda circundante que
	 * contiene una ficha negra. Cuantas más fichas negras haya alrededor de
	 * la celda, mayor será su ganancia para colocar una ficha blanca y bloquear
	 * la formación de fichas negras en una disposición ganadora.
	 * 
	 * @param cells
	 */
	public void countProfitBlack(CellComponent[][] cells) {
		resetProfit();
		rowMaxProfit = 0;
		colMaxProfit = 0;
		countProfitWhiteDone = false;

		for (int i = 0; i < Board.HEIGHT; i++) {
			for (int j = 0; j < Board.WIDTH; j++) {
				if (cells[i][j].getColor() == Cell.BLACK) {
					for (int k = 0; k < NUMBER_DIRECTION; k++) {
						int sum = 1;
						int row = i + DELTA_X[k];
						int col = j + DELTA_Y[k];
						while (GameController.stillOnBoard(row, col) && cells[row][col]
								.getColor() == Cell.BLACK) {
							sum++;
							row += DELTA_X[k];
							col += DELTA_Y[k];
						}
						if (GameController.stillOnBoard(row, col)) {
							profit[row][col] += sum;
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 * Este método busca la celda con la mayor ganancia. Si hay varias celdas con
	 * la misma ganancia, se elige al azar entre ellas. Si la ganancia es baja y
	 * aún no se ha realizado el cálculo de las ganancias para las fichas blancas
	 * después de que el juego ha progresado, se realiza ese cálculo.
	 * 
	 * @param cells
	 */
	public void searchMaxProfit(CellComponent[][] cells) {
		int max = -1;
		for (int i = 0; i < Board.HEIGHT; i++) {
			for (int j = 0; j < Board.WIDTH; j++) {
				if (profit[i][j] > max && cells[i][j].getColor() == Cell.EMPTY) {
					max = profit[i][j];
					rowMaxProfit = i;
					colMaxProfit = j;
				}
				/*
				 * Si las ganancias son iguales, se realiza una elección aleatoria entre ellas.
				 */
				else if (profit[i][j] == max && cells[i][j].getColor() == Cell.EMPTY) {
					Random rand = new Random();
					int randomNumber = rand.nextInt(2);
					if (randomNumber == 0) {
						rowMaxProfit = i;
						colMaxProfit = j;
					}
				}
			}
		}
		if (max <= 3 && !countProfitWhiteDone && GameController.getNumStep() > 3) {
			countProfitWhite(cells);
			searchMaxProfit(cells);
		}
	}

	/**
	 * 
	 * Este método calcula las ganancias para cada celda que contiene una ficha
	 * blanca.
	 * Luego busca la ganancia más alta, lo que significa la disposición ganadora
	 * que
	 * requiere la menor cantidad de movimientos.
	 * 
	 * @param cells
	 */
	public void countProfitWhite(CellComponent[][] cells) {
		resetProfit();
		rowMaxProfit = 0;
		colMaxProfit = 0;
		countProfitWhiteDone = true;

		for (int i = 0; i < Board.HEIGHT; i++) {
			for (int j = 0; j < Board.WIDTH; j++) {
				if (cells[i][j].getColor() == Cell.WHITE) {
					for (int k = 0; k < NUMBER_DIRECTION; k++) {
						int sum = 1;
						int row = i + DELTA_X[k];
						int col = j + DELTA_Y[k];
						while (GameController.stillOnBoard(row, col) && cells[row][col]
								.getColor() == Cell.WHITE) {
							sum++;
							row += DELTA_X[k];
							col += DELTA_Y[k];
						}
						if (GameController.stillOnBoard(row, col)) {
							profit[row][col] += sum;
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 * Este método busca la última celda que necesita ser ocupada para formar una
	 * disposición ganadora. Debe llamarse al final para evitar que la computadora
	 * realice un movimiento incorrecto.
	 * 
	 * @param cells
	 */
	public void searchOneStepMore(CellComponent[][] cells) {
		resetProfit();

		for (int i = 0; i < Board.HEIGHT; i++) {
			for (int j = 0; j < Board.WIDTH; j++) {
				if (cells[i][j].getColor() == Cell.WHITE) {
					for (int k = 0; k < NUMBER_DIRECTION; k++) {
						int sum = 1;
						int row = i + DELTA_X[k];
						int col = j + DELTA_Y[k];
						while (GameController.stillOnBoard(row, col) && cells[row][col]
								.getColor() == Cell.WHITE) {
							sum++;
							row += DELTA_X[k];
							col += DELTA_Y[k];
						}
						if (GameController.stillOnBoard(row, col)) {
							if (sum == 4 && cells[row][col].getColor() == Cell.EMPTY) {
								rowMaxProfit = row;
								colMaxProfit = col;
								return;
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 * Este método representa la lógica de juego de la computadora al llamar a los
	 * diferentes métodos para calcular ganancias.
	 * 
	 */
	public void play() {
		CellComponent[][] cells = BoardComponent.getCells();

		countProfitBlack(cells);
		searchMaxProfit(cells);
		searchOneStepMore(cells);

		cells[rowMaxProfit][colMaxProfit].setEnabled(false);
		cells[rowMaxProfit][colMaxProfit].setColor(Cell.WHITE);
		GomokuGUI.getBoardComponent().repaint();
		cells[rowMaxProfit][colMaxProfit].setEnabled(true);
	}

	public int getRow() {
		return rowMaxProfit;
	}

	public int getCol() {
		return colMaxProfit;
	}

}
