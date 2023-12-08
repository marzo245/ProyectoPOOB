/**
 * Esta clase es responsable de crear y modificar el título del juego.
 * 
 * author Chicuazuque-Sierra
 * version 1.0 25/11/2023
 */
package presentacion;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class TitleComponent extends JPanel {

	/**
	 * Constructor de la clase TitleComponent.
	 */
	public TitleComponent() {
		setBackground(Color.BLACK);
	}

	/**
	 * Este método paintComponent() se utiliza para dibujar la imagen del título del
	 * juego, que proviene de un archivo
	 * en formato .png.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		BufferedImage gomokuTitle = null;
		try {
			gomokuTitle = ImageIO.read(new File("tituloDelJuego.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		g.drawImage(gomokuTitle, 0, 0, 800, 150, null);
	}
}
