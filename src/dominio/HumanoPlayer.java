package dominio;

/*
 * Esta clase es una instancia de un jugador humano.
 * Autor: Chicuazuque-Sierra
 * Version: 1.0 25/11/2023
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

    public Celda[][] jugar(int row, int col, String tipoPiedra) {
        return Gomoku.getInstance().getModoDeJuego().jugar(row, col, tipoPiedra);
    }

}