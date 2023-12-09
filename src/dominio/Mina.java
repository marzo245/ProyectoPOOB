package dominio;

public class Mina extends Celda {

    public Mina() {
        super();
    }

    /**
     * Este metodo se encarga de realizar la accion de la celda
     * 
     * @param row Fila de la celda
     * @param col Columna de la celda
     */
    public void actuando(int row, int col) {
        String turnoActual = Gomoku.getTurno();

        for (int i = Math.max(0, row - 1); i <= Math.min(Board.HEIGHT - 1, row + 1); i++) {
            for (int j = Math.max(0, col - 1); j <= Math.min(Board.WIDTH - 1, col + 1); j++) {
                if (Gomoku.getCelda(i, j) instanceof Ocupada) {
                    boolean propia = Gomoku.getCelda(i, j).getPiedra().getName().equals(turnoActual);
                    calcularPuntos(propia);
                    Gomoku.getInstance().setCelda(i, j, new Vacia());
                }
            }
        }

        Gomoku.getInstance().setCelda(row, col, new Ocupada());
        Piedra piedra = Gomoku.getCelda(row, col).getPiedra();
        piedra.setName(turnoActual.equals("Blanca") ? "WHITE" : "BLACK");
    }

    private void calcularPuntos(boolean propia) {
        if (propia) {
            if (Gomoku.getTurno().equals("Blanca")) {
                Gomoku.getPlayer1().addScore(-50);
            } else {
                Gomoku.getPlayer2().addScore(-50);
            }
        } else {
            if (Gomoku.getTurno().equals("Blanca")) {
                Gomoku.getPlayer1().addScore(50);
            } else {
                Gomoku.getPlayer2().addScore(50);
            }
        }
    }

    @Override
    public Celda clonar() {
        return new Vacia();
    }
}
