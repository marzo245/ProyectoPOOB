/**
 * 
 * Esta clase representa una celda en el tablero del juego. Cada celda tiene información
 * sobre su fila, columna y si es parte de la disposición ganadora. La celda también puede
 * ser configurada para ser clickeable o no.
 * 
 * @author Chicuazuque-Sierra
 * @version 1.0 25/11/2023
 * 
 */
package presentacion;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;

public class CellComponent extends JButton {
	private String tipoDePiedra = "Null";
	private int row;
	private int col;
	private Cell color;
	private boolean isCellOfWin;
	private boolean enableClick;

	/**
	 * 
	 * Constructor de la clase CellComponent.
	 * 
	 * @param row
	 *            Fila de la celda que se está creando.
	 * @param col
	 *            Columna de la celda que se está creando.
	 */
	public CellComponent(int row, int col) {
		this.row = row;
		this.col = col;
		this.color = Cell.EMPTY;
		this.isCellOfWin = false;
		this.enableClick = true;
		if ((row + col) % 2 == 0) {
			setBackground(Color.GRAY);
		} else {
			setBackground(Color.LIGHT_GRAY);
		}
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
	}

	/**
	 * 
	 * Método getter para obtener la fila de la celda.
	 * 
	 * @return Fila de la celda.
	 */
	public int getRow() {
		return row;
	}

	/**
	 * 
	 * Método getter para obtener la columna de la celda.
	 * 
	 * @return Columna de la celda.
	 */
	public int getCol() {
		return col;
	}

	/**
	 * 
	 * Método setter para establecer el color de la celda.
	 * 
	 * @param color
	 *              Nuevo color de la ficha en la celda.
	 */
	public void setColor(Cell color) {
		this.color = color;
		if (color == Cell.WHITE || color == Cell.BLACK) {
			enableClick = false;
		}
	}

	/**
	 * 
	 * Método getter para obtener el color de la celda.
	 * 
	 * @return Color de la ficha en la celda.
	 */
	public Cell getColor() {
		return color;
	}

	/**
	 * 
	 * Método setter para indicar si la celda es parte de la disposición ganadora o
	 * no.
	 * 
	 * @param value
	 *              Nuevo valor que indica si la celda es parte de la disposición
	 *              ganadora.
	 */
	public void setIsCellOfWin(boolean value) {
		isCellOfWin = value;
	}

	public void setTipoDePiedra(String tipoDePiedra) {
		this.tipoDePiedra = tipoDePiedra;
	}

	/**
	 * 
	 * Método getter para obtener información sobre si la celda se puede clickear o
	 * no.
	 * 
	 * @return true
	 *         si la celda se puede clickear.
	 * @return false
	 *         si la celda no se puede clickear.
	 */
	public boolean getEnableClick() {
		return enableClick;
	}

	/**
	 * 
	 * Método setter para cambiar si la celda se puede clickear o no.
	 * 
	 * @param value
	 *              Nuevo valor que indica si la celda se puede clickear.
	 */
	public void setEnableClick(boolean value) {
		enableClick = value;
	}

	/**
	 * Método para dibujar la ficha en la celda cuando se selecciona.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		/*
		 * Configura el anti-aliasing para que los bordes de la ficha se vean suaves.
		 */
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		if (color == Cell.BLACK) {
			g2.setColor(Color.BLACK);
			g2.fillOval(4, 4, getWidth() - 8, getHeight() - 8);
		} else if (color == Cell.WHITE) {
			g2.setColor(Color.WHITE);
			g2.fillOval(4, 4, getWidth() - 8, getHeight() - 8);
		} else if (color == Cell.TELEPORT) {
			g2.setColor(Color.BLUE);
			g2.fillOval(4, 4, getWidth() - 8, getHeight() - 8);
			// Esto se hace nada mas con la intencionde que sepamos nosotros donde esta el
			// tp toca quitarlo para la presentacion final
		} else if (color == Cell.MINE) {
			g2.setColor(Color.RED);
			g2.fillOval(4, 4, getWidth() - 8, getHeight() - 8);
			// Esto se hace nada mas con la intencionde que sepamos nosotros donde esta el
			// tp toca quitarlo para la presentacion final
		} else if (color == Cell.GOLDEN) {
			g2.setColor(Color.YELLOW);
			g2.fillOval(4, 4, getWidth() - 8, getHeight() - 8);
		}
		// Esto se hace nada mas con la intencionde que sepamos nosotros donde esta el
		// tp toca quitarlo para la presentacion final

		/*
		 * Dibuja un borde alrededor de la piedra de pendiendo del tipo que sea
		 */
		if (tipoDePiedra.equals("Piedra Pesada") && color != Cell.EMPTY) {
			g2.setStroke(new BasicStroke(3));
			g2.setColor(Color.ORANGE);
			g2.drawOval(4, 4, getWidth() - 8, getHeight() - 8);
		} else if (tipoDePiedra.equals("Piedra Ligera") && color != Cell.EMPTY) {
			g2.setStroke(new BasicStroke(3));
			g2.setColor(Color.GREEN);
			g2.drawOval(4, 4, getWidth() - 8, getHeight() - 8);
		}

		/*
		 * Si la celda es parte de la disposición ganadora,
		 * entonces se vuelve a dibujar y se agrega un borde para
		 * resaltar qué celdas son parte de la disposición ganadora.
		 */
		if (isCellOfWin) {
			if (color == Cell.BLACK) {
				g2.setColor(Color.BLACK);
				g2.fillOval(4, 4, getWidth() - 8, getHeight() - 8);
			} else if (color == Cell.WHITE) {
				g2.setColor(Color.WHITE);
				g2.fillOval(4, 4, getWidth() - 8, getHeight() - 8);
			}
			g2.setStroke(new BasicStroke(3));
			g2.setColor(Color.RED);
			g2.drawOval(4, 4, getWidth() - 8, getHeight() - 8);
		}
	}
}
