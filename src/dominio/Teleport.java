package dominio;

public class Teleport extends Celda {

    public void actuando(int row, int col) {
        int randomRow, randomCol;
        do {
            randomRow = (int) (Math.random() * (Board.HEIGHT - 1));
            randomCol = (int) (Math.random() * (Board.WIDTH - 1));
        } while (Gomoku.getInstance().getCelda(randomRow, randomCol) instanceof Vacia);
        if (Gomoku.getInstance().getTurno() == Gomoku.getInstance().getPlayer1().getColor()) {
            Gomoku.getInstance().setCelda(randomRow, randomCol, new Ocupada());
            if (Gomoku.getInstance().getTurno().equals("Blanca")) {
                Gomoku.getInstance().getCelda(randomRow, randomCol).getPiedra().setName("WHITE");
            } else {
                Gomoku.getInstance().getCelda(randomRow, randomCol).getPiedra().setName("BLACK");
            }
        }
    }
}
