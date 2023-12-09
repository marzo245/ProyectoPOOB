package dominio;

/**
 * Esta clase define el tipo de celda Teleport la cual tiene una vida de -1
 * y cada ronda que pasa no pierde vida
 * Su comportamiento es mover al jugador a una posición aleatoria
 * 
 * Autor: Chicuazuque-Sierra
 * Version: 1.2 09/12/2023
 */
public class Teleport extends Celda {

    /**
     * Constructor de la clase
     */
    public Teleport() {
        super();
    }

    /**
     * Este metodo define el comportamiento de la celda una vez es activada
     * 
     * @param row fila de la celda
     * @param col columna de la celda
     * 
     */
    public void actuando(int row, int col) {
        int randomRow, randomCol;
        do {
            randomRow = (int) (Math.random() * (Board.HEIGHT - 1));
            randomCol = (int) (Math.random() * (Board.WIDTH - 1));
        } while (Gomoku.getInstance().getCelda(randomRow, randomCol) instanceof Ocupada);

        // Guarda las coordenadas antiguas antes de teleportar
        int oldRow = row;
        int oldCol = col;

        // Mueve al jugador a la nueva posición
        Gomoku.getInstance().setCelda(oldRow, oldCol, new Vacia());
        Gomoku.getInstance().setCelda(randomRow, randomCol, new Ocupada());

        // Actualiza el nombre de la piedra en la nueva posición
        Celda newCelda = Gomoku.getInstance().getCelda(randomRow, randomCol);
        if (newCelda instanceof Ocupada) {
            if (Gomoku.getInstance().getTurno().equals("Blanca")) {
                newCelda.getPiedra().setName("Blanca");
            } else {
                newCelda.getPiedra().setName("Negra");
            }
        }
    }

    /**
     * Este metodo se encarga de clonar la celda
     */
    @Override
    public Celda clonar() {
        return new Vacia();
    }
}
