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
	private static Timer timer2;
	public static final int TENTH_SEC = 100;
	private static JLabel timeLabel;
	/*
	 * La variable millisecond almacena el valor de milisegundos que está en curso.
	 */
	private static int millisecond1;
	private static int millisecond2;
	/*
	 * La variable second almacena el valor de segundos que está en curso.
	 */
	private static int second1;
	private static int second2;
	/*
	 * La variable minute almacena el valor de minutos que está en curso.
	 */
	private static int minute1;
	private static int minute2;
	/*
	 * La variable secondString almacena el valor de la variable second en formato
	 * de cadena de texto.
	 */
	private static String secondString1;
	private static String secondString2;
	/*
	 * La variable minuteString almacena el valor de la variable minute en formato
	 * de cadena de texto.
	 */
	private static String minuteString1;
	private static String minuteString2;

	/**
	 * Constructor de la clase TimerComponent.
	 */
	public TimerComponent() {
		setPreferredSize(new Dimension(300, 300));
		setBackground(Color.BLACK);
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		millisecond1 = 0;
		second1 = millisecond1 / TENTH_SEC;
		minute1 = 0;

		secondString1 = "" + second1;
		minuteString1 = "" + minute1;
		if (second1 < 10) {
			secondString1 = "0" + secondString1;
		}
		if (minute1 < 10) {
			minuteString1 = "0" + minuteString1;
		}
		millisecond2 = 0;
		second2 = millisecond2 / TENTH_SEC;
		minute2 = 0;

		secondString2 = "" + second2;
		minuteString2 = "" + minute2;
		if (second2 < 10) {
			secondString2 = "0" + secondString2;
		}
		if (minute2 < 10) {
			minuteString2 = "0" + minuteString2;
		}
		timeLabel = new JLabel();
		timeLabel.setFont(new Font("SansSerif", Font.PLAIN, 80));
		timeLabel.setForeground(Color.WHITE);
		timeLabel.setText(minuteString1 + ":" + secondString1);
		add(timeLabel);

		timer = new Timer(TENTH_SEC, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				millisecond1++;
				second1 = millisecond1 / 10;
				if (second1 == 60) {
					minute1++;
					millisecond1 = 0;
					second1 = 0;
				}
				secondString1 = "" + second1;
				minuteString1 = "" + minute1;
				if (second1 < 10) {
					secondString1 = "0" + secondString1;
				}
				if (minute1 < 10) {
					minuteString1 = "0" + minuteString1;
				}
				timeLabel.setText(minuteString1 + ":" + secondString1);
			}
		});
		timer2 = new Timer(TENTH_SEC, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				millisecond2++;
				second2 = millisecond2 / 10;
				if (second2 == 60) {
					minute2++;
					millisecond2 = 0;
					second2 = 0;
				}
				secondString2 = "" + second2;
				minuteString2 = "" + minute2;
				if (second2 < 10) {
					secondString2 = "0" + secondString2;
				}
				if (minute1 < 10) {
					minuteString2 = "0" + minuteString2;
				}
				timeLabel.setText(minuteString2 + ":" + secondString2);
			}
		});
	}

	public static void switchToTimer2() {
		if (timer.isRunning()) {
			timer.stop();
		}
		timeLabel.setText(minuteString2 + ":" + secondString2);
		timer2.start();
	}

	public static void switchToTimer1() {
		if (timer2.isRunning()) {
			timer2.stop();
		}
		timeLabel.setText(minuteString1 + ":" + secondString1);
		timer.start();
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
	 * Getter para obtener el temporizador.
	 * 
	 * @return Timer el temporizador
	 */
	public static Timer getTimer2() {
		return timer2;
	}

	/**
	 * Este método se utiliza para restablecer el temporizador a cero.
	 */
	public static void resetTimer() {
		millisecond1 = 0;
		second1 = millisecond1 / TENTH_SEC;
		minute1 = 0;

		secondString1 = "" + second1;
		minuteString1 = "" + minute1;
		if (second1 < 10) {
			secondString1 = "0" + secondString1;
		}
		if (minute1 < 10) {
			minuteString1 = "0" + minuteString1;
		}
		millisecond2 = 0;
		second2 = millisecond2 / TENTH_SEC;
		minute2 = 0;

		secondString2 = "" + second2;
		minuteString2 = "" + minute2;
		if (second2 < 10) {
			secondString2 = "0" + secondString2;
		}
		if (minute2 < 10) {
			minuteString2 = "0" + minuteString2;
		}
		timeLabel.setText(minuteString2 + ":" + secondString2);
	}
}
