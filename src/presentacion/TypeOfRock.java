package presentacion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dominio.Gomoku;

public class TypeOfRock extends JPanel implements ActionListener {
    private static JButton piedraPesada;
    private static JButton piedraLigera;
    private static JButton piedraNormal;
    private static boolean elijioTipoDePiedra = false;

    /**
     * Constructor de la clase ButtonComponent.
     */
    public TypeOfRock() {
        setBackground(Color.BLACK);
        piedraPesada = new JButton("Piedra Pesada");
        piedraPesada.addActionListener(this);
        piedraPesada.setForeground(Color.ORANGE);
        piedraPesada.setFont(new Font("SansSerif", Font.PLAIN, 50));
        piedraPesada.setOpaque(false);
        piedraPesada.setContentAreaFilled(false);
        piedraPesada.setFocusPainted(false);
        piedraPesada.setText("Piedras pesadas :" + Gomoku.getInstance().getPlayer1().getPiedrasPesadas());
        piedraLigera = new JButton("Piedra Ligera");
        piedraLigera.addActionListener(this);
        piedraLigera.setForeground(Color.ORANGE);
        piedraLigera.setFont(new Font("SansSerif", Font.PLAIN, 50));
        piedraLigera.setOpaque(false);
        piedraLigera.setContentAreaFilled(false);
        piedraLigera.setFocusPainted(false);
        piedraLigera.setText("Piedras ligeras :" + Gomoku.getInstance().getPlayer1().getPiedrasLigeras());
        piedraNormal = new JButton("Piedra Normal");
        piedraNormal.addActionListener(this);
        piedraNormal.setForeground(Color.ORANGE);
        piedraNormal.setFont(new Font("SansSerif", Font.PLAIN, 50));
        piedraNormal.setOpaque(false);
        piedraNormal.setContentAreaFilled(false);
        piedraNormal.setFocusPainted(false);
        piedraNormal.setText("Piedras normales infinitas");
        JPanel concontenedorDeRocas = new JPanel();
        concontenedorDeRocas.setLayout(new GridLayout(3, 1));
        concontenedorDeRocas.setBackground(Color.BLACK);

        add(concontenedorDeRocas);
        concontenedorDeRocas.add(piedraLigera);
        concontenedorDeRocas.add(piedraPesada);
        concontenedorDeRocas.add(piedraNormal);
    }

    /**
     * Este método contiene las acciones que se realizarán cuando
     * se hace clic en los botones de esta clase.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == piedraPesada) {
            JOptionPane.showMessageDialog(null,
                    "Elegiste la piedra pesada.\n" + //
                            "Ponla en una celda vacía!",
                    "ESTAS PIEDRAS EQUIVALEN A 2 NORMALES", JOptionPane.WARNING_MESSAGE);
            GameController.setPiedra("Piedra Pesada");
            elijioTipoDePiedra = true;
        } else if (e.getSource() == piedraLigera) {
            JOptionPane.showMessageDialog(null,
                    "Elegiste la piedra ligera.\n" +
                            "Ponla en una celda vacía!",
                    "ESTAS PIEDRAS SOLO ESTAN VIVAS 3 RONDAS", JOptionPane.WARNING_MESSAGE);
            GameController.setPiedra("Piedra Ligera");
            elijioTipoDePiedra = true;
        } else if (e.getSource() == piedraNormal) {
            JOptionPane.showMessageDialog(null,
                    "Elegiste la piedra normal.\n" +
                            "Ponla en una celda vacía!",
                    "ESTAS SON LAS TIPICAS PIEDRAS", JOptionPane.WARNING_MESSAGE);
            GameController.setPiedra("Piedra Normal");
            elijioTipoDePiedra = true;
        }
    }

    public static void setElijioTipoDePiedra(boolean newElijioTipoDePiedra) {
        elijioTipoDePiedra = newElijioTipoDePiedra;
    }

    public static boolean getElijioTipoDePiedra() {
        return elijioTipoDePiedra;
    }

    /**
     * Este método actualiza el texto de los botones de esta clase.
     */
    public static void actualizarTextoBotones() {
        if (Gomoku.getInstance().getTurno().equals("Blanca")) {
            piedraPesada.setText("Piedras pesadas :" + Gomoku.getInstance().getPlayer1().getPiedrasPesadas());
            piedraLigera.setText("Piedras ligeras :" + Gomoku.getInstance().getPlayer1().getPiedrasLigeras());
        } else {
            piedraPesada.setText("Piedras pesadas :" + Gomoku.getInstance().getPlayer2().getPiedrasPesadas());
            piedraLigera.setText("Piedras ligeras :" + Gomoku.getInstance().getPlayer2().getPiedrasLigeras());
        }
    }
}