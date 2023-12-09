package dominio;

public class Teleport extends Celda {

    public Teleport() {
        super();
    }

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

    @Override
    public Celda clonar() {
        return new Vacia();
    }
}
