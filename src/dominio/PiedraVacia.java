package dominio;

/**
 * Esta clase define el tipo de piedra vacia la cual tiene una vida de -1
 * y cada ronda que pasa no pierde vida
 * 
 * Autor: Chicuazuque-Sierra
 * Version: 1.2 09/12/2023
 */
public class PiedraVacia extends Piedra {

    /**
     * Constructor de la clase
     */
    public PiedraVacia() {
        super(row, col);
        super.vida = -1;
    }

    /**
     * Este metodo define cuando pasa una ronda en el juego
     */
    public void ronda() {
    }
}