package dominio;

/**
 * Esta clase define el tipo de piedra ligera la cual tiene una vida de 3
 * y cada ronda que pasa pierde una vida
 * Cumplido su ciclo de vida se convierte en una piedra vacia
 * Autor: Chicuazuque-Sierra
 * Version: 1.2 09/12/2023
 */
public class PiedraLigera extends Piedra {

    /**
     * Constructor de la clase
     */
    public PiedraLigera(int row, int col) {
        super(row, col);
        vida = 3;
    }

    /**
     * Este metodo define cuando pasa una ronda en el juego
     */
    public void ronda() {
        vida -= 1;
    }

}