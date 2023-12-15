import javax.swing.*;
import java.awt.*;

public class MiPanel extends JPanel {
    private Image imagenDeFondo;

    public MiPanel() {
        try {
            imagenDeFondo = javax.imageio.ImageIO.read(new java.net.URL("http://ruta/a/tu/imagen.jpg"));
        } catch (Exception e) {
            /* Manejar error */ }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagenDeFondo, 0, 0, this);
    }
}