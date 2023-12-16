/**
 * 
 * Esta clase representa un componente del tablero. Un tablero tiene celdas
 * dispuestas en filas y columnas.
 * 
 * @author Chicuazuque-Sierra
 * @version 2.0 16/12/2023
 * 
 */
package presentacion;

import dominio.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BoardVisual extends JPanel {

	private static CellComponent[][] cells;

	/**
	 * 
	 * El método paintComponent() se encarga de mostrar la imagen del tablero.
	 * 
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		BufferedImage boardBackground = null;
		try {
			boardBackground = ImageIO.read(new File("fondoDelTablero.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		g.drawImage(boardBackground, 0, 0, 600, 600, null);
	}

	/**
	 * 
	 * Constructor de la clase BoardVisual.
	 * 
	 */
	public BoardVisual() {
		setPreferredSize(new Dimension(500, 500));
		setLayout(new GridLayout(Board.HEIGHT, Board.WIDTH));

		cells = new CellComponent[Board.HEIGHT][Board.WIDTH];
		Gomoku.getBoard();
		Celda[][] board = Gomoku.getBoard();
		for (int i = 0; i < Board.HEIGHT; i++) {
			for (int j = 0; j < Board.WIDTH; j++) {
				cells[i][j] = new CellComponent(i, j);
				cells[i][j].addActionListener(new GameController(cells[i][j]));
				add(cells[i][j]);
				if (board[i][j] instanceof Mina) {
					cells[i][j].setColor(Cell.MINE);
				} else if (board[i][j] instanceof Teleport) {
					cells[i][j].setColor(Cell.TELEPORT);
				}
			}
		}
	}

	/**
	 * 
	 * Este método retorna la matriz de componentes del tablero.
	 * 
	 * @return cells
	 */
	public static CellComponent[][] getCells() {
		return cells;
	}

	/**
	 * 
	 * Este método elimina todos los componentes del tablero y los vuelve a crear.
	 * 
	 */
	public void clearBoard() {
		removeAll();
		updateUI();
		setLayout(new GridLayout(Board.HEIGHT, Board.WIDTH));
		cells = new CellComponent[Board.HEIGHT][Board.WIDTH];

		cells = new CellComponent[Board.HEIGHT][Board.WIDTH];
		Gomoku.getInstance().clearBoard();
		Celda[][] board = Gomoku.getBoard();
		for (int i = 0; i < Board.HEIGHT; i++) {
			for (int j = 0; j < Board.WIDTH; j++) {
				cells[i][j] = new CellComponent(i, j);
				cells[i][j].addActionListener(new GameController(cells[i][j]));
				add(cells[i][j]);
				if (board[i][j] instanceof Mina) {
					cells[i][j].setColor(Cell.MINE);
				} else if (board[i][j] instanceof Teleport) {
					cells[i][j].setColor(Cell.TELEPORT);
				}
			}
		}
	}
}
