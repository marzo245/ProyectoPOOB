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
    public void actuando(int row, int col, String tipoDePiedra) {
        int randomRow, randomCol;
        do {
            randomRow = (int) (Math.random() * (Board.HEIGHT - 1));
            randomCol = (int) (Math.random() * (Board.WIDTH - 1));
        } while (Gomoku.getCelda(randomRow, randomCol) instanceof Ocupada);

        // Guarda las coordenadas antiguas antes de teleportar
        int oldRow = row;
        int oldCol = col;

        // Mueve al jugador a la nueva posición
        Gomoku.setCelda(oldRow, oldCol, new Vacia());
        Gomoku.setCelda(randomRow, randomCol, new Ocupada());

        // Actualiza el nombre de la piedra en la nueva posición
        Celda newCelda = Gomoku.getCelda(randomRow, randomCol);
        if (newCelda instanceof Ocupada) {
            if (Gomoku.getTurno().equals("Blanca")) {
                newCelda.getPiedra().setName("Blanca");
            } else {
                newCelda.getPiedra().setName("Negra");
            }
        }
        Celda celda = Gomoku.getCelda(randomRow, randomCol);

        // Si es PiedraVacia, crea una nueva instancia de Piedra y asígnale el nombre
        Piedra nuevaPiedra;
        if (tipoDePiedra.equals("Piedra Pesada")) {
            nuevaPiedra = new PiedraPesada(row, col);

        } else if (tipoDePiedra.equals("Piedra Normal")) {
            nuevaPiedra = new PiedraNormal(row, col);
        } else {
            // Agrega condiciones para otros tipos de piedra si es necesario
            nuevaPiedra = new PiedraLigera(row, col);
        }
        nuevaPiedra.setName(Gomoku.getTurno());
        nuevaPiedra.setVida(3);
        // Asigna la nueva piedra a la celda
        celda.setPiedra(nuevaPiedra);
    }

    /**
     * Este metodo se encarga de clonar la celda
     */
    @Override
    public Celda clonar() {
        return new Vacia();
    }
}
