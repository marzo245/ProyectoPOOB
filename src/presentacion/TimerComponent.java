/**
 * Esta clase representa el temporizador del juego. Muestra el temporizador en la
 * pantalla del juego para medir cuánto tiempo ha transcurrido durante el juego.
 * 
 * author Chicuazuque-Sierra
 * version 1.0 25/11/2023
 */
package presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TimerComponent extends JPanel {
	private static Timer timer;
	public static final int TENTH_SEC = 100;
	private static JLabel timeLabel;
	/*
	 * La variable millisecond almacena el valor de milisegundos que está en curso.
	 */
	private static int millisecond;
	/*
	 * La variable second almacena el valor de segundos que está en curso.
	 */
	private static int second;
	/*
	 * La variable minute almacena el valor de minutos que está en curso.
	 */
	private static int minute;
	/*
	 * La variable secondString almacena el valor de la variable second en formato
	 * de cadena de texto.
	 */
	private static String secondString;
	/*
	 * La variable minuteString almacena el valor de la variable minute en formato
	 * de cadena de texto.
	 */
	private static String minuteString;

	/**
	 * Constructor de la clase TimerComponent.
	 */
	public TimerComponent() {
		setPreferredSize(new Dimension(300, 300));
		setBackground(Color.BLACK);
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		millisecond = 0;
		second = millisecond / TENTH_SEC;
		minute = 0;

		secondString = "" + second;
		minuteString = "" + minute;
		if (second < 10) {
			secondString = "0" + secondString;
		}
		if (minute < 10) {
			minuteString = "0" + minuteString;
		}

		timeLabel = new JLabel();
		timeLabel.setFont(new Font("SansSerif", Font.PLAIN, 80));
		timeLabel.setForeground(Color.WHITE);
		timeLabel.setText(minuteString + ":" + secondString);
		add(timeLabel);

		timer = new Timer(TENTH_SEC, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				millisecond++;
				second = millisecond / 10;
				if (second == 60) {
					minute++;
					millisecond = 0;
					second = 0;
				}
				secondString = "" + second;
				minuteString = "" + minute;
				if (second < 10) {
					secondString = "0" + secondString;
				}
				if (minute < 10) {
					minuteString = "0" + minuteString;
				}
				timeLabel.setText(minuteString + ":" + secondString);
			}
		});
	}

	/**
	 * Getter para obtener el temporizador.
	 * 
	 * @return Timer el temporizador
	 */
	public static Timer getTimer() {
		return timer;
	}

	/**
	 * Este método se utiliza para restablecer el temporizador a cero.
	 */
	public static void resetTimer() {
		millisecond = 0;
		second = millisecond / 10;
		minute = 0;
		secondString = "" + second;
		minuteString = "" + minute;
		if (second < 10) {
			secondString = "0" + secondString;
		}
		if (minute < 10) {
			minuteString = "0" + minuteString;
		}
		timeLabel.setText(minuteString + ":" + secondString);
	}
}
