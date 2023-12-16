/*
 * Esta clase almacena datos sobre las dimensiones de un tablero, es decir,
 * la altura y el ancho del tablero.
 * 
 * Autor: Chicuazuque-Sierra
 * Version: 1.2 09/12/2023
 */
package dominio;

public abstract class Celda {
    protected Piedra piedra;

    /*
     * Constructor de la clase.
     */
    public Celda() {
        this.piedra = new PiedraVacia(0, 0);
    }

    public abstract Celda clonar();

    /*
     * Devuelve el color de la celda.
     * 
     * @param row Fila de la celda.
     * 
     * @param col Columna de la celda.
     */
    public abstract void actuando(int row, int col, String tipoDePiedra);

    /*
     * Establece el color de la celda.
     * 
     * @param piedra es un objeto de tipo Piedra.
     */
    public void setPiedra(String tipoPiedra, int row, int col) {
        if (tipoPiedra.equals("Piedra Pesada")) {
            piedra = new PiedraPesada(row, col);
        } else if (tipoPiedra.equals("Piedra Ligera")) {
            piedra = new PiedraLigera(row, col);
        } else if (tipoPiedra.equals("Piedra Normal")) {
            piedra = new PiedraNormal(row, col);
        } else {
            piedra = new PiedraVacia(row, col);
        }
    }

    /*
     * Devuelve el color de la celda.
     * 
     * @return Color de la celda.
     */
    public Piedra getPiedra() {
        return piedra;
    }

    /*
     * Establece el color de la celda.
     * 
     * @param piedra es un objeto de tipo Piedra.
     */
    public void setPiedra(Piedra piedra) {
        this.piedra = piedra;
    }

    /*
     * Devuelve el color de la celda.
     * 
     * @return Color de la celda.
     */
    @Override
    public String toString() {
        if (this instanceof Vacia) {
            return "-";
        } else if (this instanceof Mina) {
            return "M";
        } else if (this instanceof Teleport) {
            return "T";
        } else if (this instanceof Ocupada) {
            if (piedra.getName().equals("Blanca")) {
                return "B";
            } else {
                return "N";
            }
        } else
            // Puedes ajustar esto según tu lógica para representar otras celdas
            return "X";
    }
}