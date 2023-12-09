package dominio;

/**
 * Esta clase es la encargada de definir el modo de juego Normal.
 * Autor: Chicuazuque-Sierra
 * Version: 1.2 09/12/2023
 */
public class ModoNormal extends ModoJuego {

    /**
     * Constructor de la clase.
     */
    public ModoNormal() {
        super();
    }

    /**
     * Este metodo se encarga de jugar una piedra en el tablero.
     * 
     * @param row        Fila donde se quiere jugar la piedra.
     * @param col        Columna donde se quiere jugar la piedra.
     * @param tipoPiedra Tipo de piedra que se quiere jugar.
     * @return Tablero con la piedra jugada.
     */
    public Celda[][] jugar(int row, int col, String tipoPiedra) {
        return ponerPiedra(row, col, tipoPiedra);
    }

}
