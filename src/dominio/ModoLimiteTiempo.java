package dominio;

public class ModoLimiteTiempo extends ModoJuego {
    public ModoLimiteTiempo() {
        super();
    }

    public Celda[][] jugar(int row, int col, String tipoCelda) {
        if (Gomoku.getTurno().equals(Gomoku.getPlayer1().getColor())) {
            Gomoku.setTurno(Gomoku.getPlayer2().getColor());
            return ponerPiedra(row, col, tipoCelda);
        } else {
            Gomoku.setTurno(Gomoku.getPlayer1().getColor());
            return ponerPiedra(row, col, tipoCelda);
        }
    }

    public Celda[][] ponerPiedra(int row, int col, String tipoPiedra) {
        if (validarPosicion(row, col)) {
            if (Gomoku.getTurno().equals("Blanca")) {
                Gomoku.getBoard()[row][col].getPiedra().setName("WHITE");
                Gomoku.setTurno("Negra");
            } else {
                Gomoku.getBoard()[row][col].getPiedra().setName("BLACK");
                Gomoku.setTurno("Blanca");
            }
            Gomoku.getInstance().setCelda(row, col, new Ocupada());
            Gomoku.getInstance().getCelda(row, col).setPiedra(tipoPiedra);
        }
        Gomoku.getInstance().setMensaje("Celda ocupada");
        Gomoku.getInstance().setHayMensjae(true);
        return Gomoku.getBoard();
    }

    public boolean validarPosicion(int row, int col) {
        if (Gomoku.getInstance().getCelda(row, col) instanceof Vacia)
            return true;
        else if (Gomoku.getInstance().getCelda(row, col) instanceof Mina) {
            Gomoku.getInstance().getCelda(row, col).actuando(row, col);
            Gomoku.getInstance().setMensaje("Has pisado una mina");
            Gomoku.getInstance().setHayMensjae(true);
            return true;
        } else if (Gomoku.getInstance().getCelda(row, col) instanceof Teleport) {
            Gomoku.getInstance().getCelda(row, col).actuando(row, col);
            Gomoku.getInstance().setMensaje("Has pisado un teleport");
            Gomoku.getInstance().setHayMensjae(true);
            return true;
        }

        return false;
    }

}
