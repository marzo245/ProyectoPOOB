/**
 * Esta clase se utiliza para mostrar los 5 puntajes de los juegos anteriores.
 * 
 * @author Chicuazuque-Sierra
 * @version 1.0 25/11/2023
 */
package presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreComponent extends JPanel {

	private JPanel highScoreContainer;
	private static JLabel[][] table;
	public static final int NUM_ROW = 6;
	public static final int NUM_COL = 2;

	/**
	 * Constructor de la clase ScoreComponent.
	 */
	public ScoreComponent() {

		highScoreContainer = new JPanel();
		highScoreContainer.setPreferredSize(new Dimension(300, 300));
		highScoreContainer.setLayout(new GridLayout(6, 2));
		add(highScoreContainer);
		setBackground(Color.BLACK);
		highScoreContainer.setBackground(Color.BLACK);

		table = new JLabel[NUM_ROW][NUM_COL];
		for (int i = 0; i < NUM_ROW; i++) {
			for (int j = 0; j < NUM_COL; j++) {
				table[i][j] = new JLabel();
				table[i][j].setFont(new Font("SansSerif", Font.PLAIN, 18));
				table[i][j].setForeground(Color.WHITE);
				highScoreContainer.add(table[i][j]);
			}
			table[i][0].setText("anónimo");
			table[i][1].setText("0");
		}
		table[0][0].setText("NOMBRE");
		table[0][1].setText("PASO");
	}

	/**
	 * Este método se utiliza para actualizar el puntaje cada vez que se completa un
	 * nuevo juego.
	 * 
	 * @param name nombre del jugador que ganó
	 * @param step número de pasos necesarios para ganar
	 */
	public static void updateScore(String name, int step) {
		if (name == null)
			return;
		for (int i = NUM_ROW - 1; i >= 2; i--) {
			table[i][0].setText(table[i - 1][0].getText());
			table[i][1].setText(table[i - 1][1].getText());
		}
		table[1][0].setText(name.toLowerCase());
		table[1][1].setText("" + step);
	}

}
