package dominio;

/**
 * Esta clase es la encargada de definir el modo de juego Limite de fichas.
 * Autor: Chicuazuque-Sierra
 * Version: 1.2 09/12/2023
 */
public class ModoLimiteTiempo extends ModoJuego {

    /**
     * Constructor de la clase.
     */
    public ModoLimiteTiempo() {
        super();
    }

    /**
     * Este metodo se encarga de jugar una piedra en el tablero.
     * 
     * @param row       Fila donde se quiere jugar la piedra.
     * @param col       Columna donde se quiere jugar la piedra.
     * @param tipoCelda Tipo de celda que se quiere jugar.
     * @return Tablero con la piedra jugada.
     */
    public Celda[][] jugar(int row, int col, String tipoCelda) {
        return ponerPiedra(row, col, tipoCelda);
    }

}
