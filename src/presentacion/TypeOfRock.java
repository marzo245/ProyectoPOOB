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

    /**
     * Constructor de la clase ButtonComponent.
     */
    public TypeOfRock() {
        setBackground(Color.BLACK);
        piedraPesada = new JButton("Piedra Pesada");
        piedraPesada.addActionListener(this);
        piedraPesada.setForeground(Color.RED);
        piedraPesada.setFont(new Font("SansSerif", Font.PLAIN, 50));
        piedraPesada.setOpaque(false);
        piedraPesada.setContentAreaFilled(false);
        piedraPesada.setFocusPainted(false);
        piedraPesada.setText("Piedras pesadas :" + Gomoku.getInstance().getPlayer1().getPiedrasPesadas());
        piedraLigera = new JButton("Piedra Ligera");
        piedraLigera.addActionListener(this);
        piedraLigera.setForeground(Color.RED);
        piedraLigera.setFont(new Font("SansSerif", Font.PLAIN, 50));
        piedraLigera.setOpaque(false);
        piedraLigera.setContentAreaFilled(false);
        piedraLigera.setFocusPainted(false);
        piedraLigera.setText("Piedras ligeras :" + Gomoku.getInstance().getPlayer1().getPiedrasLigeras());
        JPanel concontenedorDeRocas = new JPanel();
        concontenedorDeRocas.setLayout(new GridLayout(2, 1));
        concontenedorDeRocas.setBackground(Color.BLACK);

        add(concontenedorDeRocas);
        concontenedorDeRocas.add(piedraLigera);
        concontenedorDeRocas.add(piedraPesada);
    }

    /**
     * Este método contiene las acciones que se realizarán cuando
     * se hace clic en los botones de esta clase.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == piedraPesada) {
            JOptionPane.showMessageDialog(null,
                    "Elegiste la piedra pesada.\\n" + //
                            "Ponla en una celda vacía!",
                    "Advertencia esta piedra nunca muere", JOptionPane.WARNING_MESSAGE);
            GameController.setPiedra("Piedra pesada");
        } else if (e.getSource() == piedraLigera) {
            JOptionPane.showMessageDialog(null,
                    "Elegiste la piedra ligera.\nPonla en una celda vacía!",
                    "Advertencia tiene 3 vidas de turno", JOptionPane.WARNING_MESSAGE);
            GameController.setPiedra("Piedra ligera");
        }
    }

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