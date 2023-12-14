package dominio;

/**
 * Esta clase es la encargada de definir el modo de juego Limite de fichas.
 * Autor: Chicuazuque-Sierra
 * Version: 1.2 09/12/2023
 */
public class ModoLimiteTiempo extends ModoJuego {

    /**
     * Constructor de la clase.
     */
    public ModoLimiteTiempo() {
        super();
    }

    /**
     * Este metodo se encarga de jugar una piedra en el tablero.
     * 
     * @param row       Fila donde se quiere jugar la piedra.
     * @param col       Columna donde se quiere jugar la piedra.
     * @param tipoCelda Tipo de celda que se quiere jugar.
     * @return Tablero con la piedra jugada.
     */
    public Celda[][] jugar(int row, int col, String tipoCelda) {
        if (validarCantidadPiedras(tipoCelda)) {
            return ponerPiedra(row, col, tipoCelda);
        }
        return Gomoku.getInstance().getBoard();
    }

    protected boolean validarCantidadPiedras(String tipoPiedra) {
        if (Gomoku.getInstance().getTurno().equals("Blanca")) {
            if (tipoPiedra.equals("Piedra Pesada")) {
                if (Gomoku.getInstance().getPlayer1().getPiedrasPesadas() > 0) {
                    Gomoku.getInstance().getPlayer1().ronda(tipoPiedra);
                    return true;
                } else {
                    Gomoku.getInstance().getPlayer1().setPiedrasPesadas(10);
                    return true;
                }
            } else if (tipoPiedra.equals("Piedra Ligera")) {
                if (Gomoku.getInstance().getPlayer1().getPiedrasLigeras() > 0) {
                    Gomoku.getInstance().getPlayer1().ronda(tipoPiedra);
                    return true;
                } else {
                    Gomoku.getInstance().getPlayer1().setPiedrasLigeras(10);
                    return true;
                }
            }
        } else {
            if (tipoPiedra.equals("Piedra Pesada")) {
                if (Gomoku.getInstance().getPlayer2().getPiedrasPesadas() > 0) {
                    Gomoku.getInstance().getPlayer2().ronda(tipoPiedra);
                    return true;
                } else {
                    Gomoku.getInstance().getPlayer2().setPiedrasPesadas(10);
                    return false;
                }
            } else if (tipoPiedra.equals("Piedra Ligera")) {
                if (Gomoku.getInstance().getPlayer2().getPiedrasLigeras() > 0) {
                    Gomoku.getInstance().getPlayer2().ronda(tipoPiedra);
                    return true;
                } else {
                    Gomoku.getInstance().getPlayer2().setPiedrasLigeras(10);
                    return false;
                }
            }
        }
        return false;
    }
}
