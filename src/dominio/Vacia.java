package dominio;

/**
 * Esta clase define el tipo de celda vacia la cual tiene una vida de -1
 * y cada ronda que pasa no pierde vida
 * 
 * Autor: Chicuazuque-Sierra
 * Version: 1.2 09/12/2023
 */
public class Vacia extends Celda {

    /**
     * Este metodo se encarga del comportamiento de la celda una vez es activada
     */
    public void actuando(int row, int col) {
    }

    /**
     * Este metodo se encarga de clonar la celda
     */
    @Override
    public Celda clonar() {
        return new Vacia();
    }
}
