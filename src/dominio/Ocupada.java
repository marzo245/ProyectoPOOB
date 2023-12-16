package dominio;

/**
 * Esta clase es la encargada de definir el estado de una celda ocupada.
 * Autor: Chicuazuque-Sierra
 * Version: 1.2 09/12/2023
 */
public class Ocupada extends Celda {

    /**
     * Constructor de la clase.
     */
    public Ocupada() {

        super();
    }

    /**
     * Este metodo se encarga de jugar una piedra en el tablero.
     * 
     * @param row Fila donde se quiere jugar la piedra.
     * @param col Columna donde se quiere jugar la piedra.
     * @return Tablero con la piedra jugada.
     */
    public void actuando(int row, int col, String tipoDePiedra) {
    }

    /**
     * Este metodo se encarga de jugar una piedra en el tablero.
     * 
     * @param row Fila donde se quiere jugar la piedra.
     * @param col Columna donde se quiere jugar la piedra.
     * @return Tablero con la piedra jugada.
     */
    public Piedra getPiedra() {
        return piedra;
    }

    /**
     * Este metodo se encarga de jugar una piedra en el tablero.
     * 
     * @param row Fila donde se quiere jugar la piedra.
     * @param col Columna donde se quiere jugar la piedra.
     * @return Tablero con la piedra jugada.
     */
    public void setPiedra(Piedra piedra) {
        super.setPiedra(piedra);
        if (Gomoku.getTurno().equals("Blanca")) {
            piedra.setName("Blanca");
        } else {
            piedra.setName("Negra");
        }
    }

    /**
     * Este metod se encarga de clonar una celda.
     * 
     * @return Celda clonada.
     */
    @Override
    public Celda clonar() {
        return new Ocupada();
    }
}