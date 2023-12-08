package dominio;

public class Mina extends Celda {

    /**
     * Este metodo se encarga de realizar la accion de la celda
     * 
     * @param row Fila de la celda
     * @param col Columna de la celda
     */
    public void actuando(int row, int col) {
        for (int i = Math.max(0, row - 1); i <= Math.min(Board.HEIGHT - 1, row + 1); i++) {
            for (int j = Math.max(0, col - 1); j <= Math.min(Board.WIDTH - 1, col + 1); j++) {
                if (Gomoku.getInstance().getCelda(i, j) instanceof Ocupada) {
                    if (Gomoku.getInstance().getCelda(i, j).getPiedra().getName()
                            .equals(Gomoku.getInstance().getTurno())) {
                        calcularPuntos(true);
                    } else {
                        calcularPuntos(false);
                    }
                    Gomoku.getInstance().setCelda(i, j, new Vacia());
                }
            }
        }
        Gomoku.getInstance().setCelda(row, col, new Ocupada());
        if (Gomoku.getInstance().getTurno().equals("Blanca")) {
            Gomoku.getInstance().getCelda(row, col).getPiedra().setName("WHITE");
        } else {
            Gomoku.getInstance().getCelda(row, col).getPiedra().setName("BLACK");
        }
    }

    private void calcularPuntos(boolean propia) {
        if (propia) {
            if (Gomoku.getInstance().getTurno().equals("Blanca")) {
                Gomoku.getInstance().getPlayer1().addScore(-50);
            } else {
                Gomoku.getInstance().getPlayer2().addScore(-50);
            }
        } else {
            if (Gomoku.getInstance().getTurno().equals("Blanca")) {
                Gomoku.getInstance().getPlayer1().addScore(50);
            } else {
                Gomoku.getInstance().getPlayer2().addScore(50);
            }
        }
    }
}
