package dominio;

/*
 * Esta clase es una instancia de un jugador humano.
 * Autor: Chicuazuque-Sierra
 * Version: 1.2 09/12/2023
 */
public class HumanoPlayer extends Player {

    /*
     * Constructor de la clase.
     * param name Nombre del jugador.
     * param color Color del jugador.
     */
    public HumanoPlayer(String name, String color) {
        super(name, color);
    }

    /*
     * Metodo que permite jugar una piedra en el tablero.
     * param row Fila donde se quiere jugar la piedra.
     * param col Columna donde se quiere jugar la piedra.
     * param tipoPiedra Tipo de piedra que se quiere jugar.
     * return Tablero con la piedra jugada.
     */
    public Celda[][] jugar(int row, int col, String tipoPiedra) {
        return Gomoku.getInstance().getModoDeJuego().jugar(row, col, tipoPiedra);
    }

}