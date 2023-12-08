/*
 * Esta clase almacena datos sobre las dimensiones de un tablero, es decir,
 * la altura y el ancho del tablero.
 * 
 * Autor: Chicuazuque-Sierra
 * Version: 1.0 25/11/2023
 */
package dominio;

public abstract class Celda {
    protected static Piedra piedra = new PiedraVacia();

    /*
     * Devuelve el color de la celda.
     * param row Fila de la celda.
     * param col Columna de la celda.
     * return Color de la celda.
     */
    public abstract void actuando(int row, int col);

    /*
     * Establece el color de la celda.
     * param piedra es un objeto de tipo Piedra.
     */
    public static void setPiedra(String tipoPiedra) {
        if (tipoPiedra.equals("PiedraPesada")) {
            piedra = new PiedraPesada();
        } else if (tipoPiedra.equals("PiedraLigera")) {
            piedra = new PiedraLigera();
        } else
            piedra = new PiedraVacia();
    }

    public static Piedra getPiedra() {
        return piedra;
    }
}