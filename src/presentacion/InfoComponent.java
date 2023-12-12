/**
 * Esta clase es un panel que contiene un JLabel para informar
 * sobre la condición actual en el campo de juego.
 * 
 * @author Chicuazuque-Sierra
 * @version 1.0 25/11/2023
 */
package presentacion;

import java.awt.Color;
import java.awt.Font;
import dominio.Gomoku;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoComponent extends JPanel {

	private JLabel currentPlayer;

	/**
	 * Constructor de la clase InfoComponent.
	 */
	public InfoComponent() {
		currentPlayer = new JLabel("Turno: " + Gomoku.getInstance().getPlayer1().getName()
				+ " | Color ficha: Negra | Total movimientos: 0");
		currentPlayer.setFont(new Font("SansSerif", Font.PLAIN, 20));
		currentPlayer.setForeground(Color.WHITE);
		add(currentPlayer);
		setBackground(Color.BLACK);
	}

	public JLabel getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Este método se utiliza para restablecer la información
	 * a sus valores predeterminados antes de que comience el juego.
	 */
	public void clearInfo() {
		currentPlayer.setText("BIENVENIDOS A GOMOKU ");
	}
}
