/**
 * Esta clase representa el temporizador del juego. Muestra el temporizador en la
 * pantalla del juego para medir cuánto tiempo ha transcurrido durante el juego.
 * 
 * author Chicuazuque-Sierra
 * version 1.0 16/12/2023
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

import dominio.Gomoku;

public class TimerDownComponent extends JPanel {
    private static Timer timer;
    private static Timer timer2;
    public static final int TENTH_SEC = 100;
    private static JLabel timeLabel;
    private static int millisecond1;
    private static int millisecond2;
    private static int second1;
    private static int second2;
    private static int minute1;
    private static int minute2;
    private static String secondString1;
    private static String secondString2;
    private static String minuteString1;
    private static String minuteString2;

    /**
     * Constructor de la clase TimerDownComponent.
     */
    public TimerDownComponent() {
        setPreferredSize(new Dimension(300, 300));
        setBackground(Color.BLACK);
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        millisecond1 = 0;
        second1 = 30; // Inicializa en 1 minuto y 30 segundos
        minute1 = 1;

        secondString1 = "" + second1 % 60;
        minuteString1 = "" + minute1;
        if (second1 % 60 < 10) {
            secondString1 = "0" + secondString1;
        }
        if (minute1 < 10) {
            minuteString1 = "0" + minuteString1;
        }

        millisecond2 = 0;
        second2 = 30; // Inicializa en 1 minuto y 30 segundos
        minute2 = 1;

        secondString2 = "" + second2 % 60;
        minuteString2 = "" + minute2;
        if (second2 % 60 < 10) {
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
                millisecond1--;
                second1 = millisecond1 / 10;
                if (millisecond1 < 0) {
                    minute1--;
                    second1 = 59;
                    millisecond1 = 599;
                    if (minute1 < 0) {
                        timer.stop();
                        Gomoku.getInstance().setSeEncontroGanador(true);
                        Gomoku.getInstance().setGanador("Blanca");
                    }
                }
                secondString1 = "" + second1 % 60;
                minuteString1 = "" + minute1;
                if (second1 % 60 < 10) {
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
                millisecond2--;
                second2 = millisecond2 / 10;
                if (millisecond2 < 0) {
                    minute2--;
                    second2 = 59;
                    millisecond2 = 599;
                    if (minute2 < 0) {
                        timer2.stop();
                        Gomoku.getInstance().setSeEncontroGanador(true);
                        Gomoku.getInstance().setGanador("Negra");

                    }
                }
                secondString2 = "" + second2 % 60;
                minuteString2 = "" + minute2;
                if (second2 % 60 < 10) {
                    secondString2 = "0" + secondString2;
                }
                if (minute2 < 10) {
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

    public static Timer getTimer2() {
        return timer2;
    }

    /**
     * Este método se utiliza para restablecer el temporizador a cero.
     */
    public static void resetTimer() {
        millisecond1 = 0;
        second1 = 30; // Inicializa en 1 minuto y 30 segundos
        minute1 = 1;

        secondString1 = "" + second1 % 60;
        minuteString1 = "" + minute1;
        if (second1 % 60 < 10) {
            secondString1 = "0" + secondString1;
        }
        if (minute1 < 10) {
            minuteString1 = "0" + minuteString1;
        }

        millisecond2 = 0;
        second2 = 30; // Inicializa en 1 minuto y 30 segundos
        minute2 = 1;

        secondString2 = "" + second2 % 60;
        minuteString2 = "" + minute2;
        if (second2 % 60 < 10) {
            secondString2 = "0" + secondString2;
        }
        if (minute2 < 10) {
            minuteString2 = "0" + minuteString2;
        }

        timeLabel.setText(minuteString2 + ":" + secondString2);
    }
}
