package dominio;

/**
 * Esta clase es la encargada de definir el modo de juego Normal.
 * Autor: Chicuazuque-Sierra
 * Version: 1.2 09/12/2023
 */
public class ModoNormal extends ModoJuego {

    /**
     * Constructor de la clase.
     */
    public ModoNormal() {
        super();
    }

    /**
     * Este metodo se encarga de jugar una piedra en el tablero.
     * 
     * @param row        Fila donde se quiere jugar la piedra.
     * @param col        Columna donde se quiere jugar la piedra.
     * @param tipoPiedra Tipo de piedra que se quiere jugar.
     * @return Tablero con la piedra jugada.
     */
    public Celda[][] jugar(int row, int col, String tipoPiedra) {
        if (validarCantidadPiedras(tipoPiedra)) {
            ronda();
            return ponerPiedra(row, col, tipoPiedra);
        }
        return Gomoku.getBoard();
    }

    protected boolean validarCantidadPiedras(String tipoPiedra) {
        System.out.println("validarCantidadPiedras " + tipoPiedra + " " + Gomoku.getTurno());
        if (Gomoku.getInstance().getTurno().equals("Blanca")) {
            if (tipoPiedra.equals("Piedra Pesada")) {

                if (Gomoku.getPlayer1().getPiedrasPesadas() > 0) {
                    Gomoku.getPlayer1().ronda(tipoPiedra);
                    return true;
                } else {
                    Gomoku.getPlayer1().setPiedrasPesadas(10);
                    return true;
                }
            } else if (tipoPiedra.equals("Piedra Ligera")) {
                if (Gomoku.getPlayer1().getPiedrasLigeras() > 0) {
                    Gomoku.getPlayer1().ronda(tipoPiedra);
                    return true;
                } else {
                    Gomoku.getPlayer1().setPiedrasLigeras(10);
                    return true;
                }
            } else {
                return true;
            }
        } else {
            if (tipoPiedra.equals("Piedra Pesada")) {
                if (Gomoku.getPlayer2().getPiedrasPesadas() > 0) {
                    Gomoku.getPlayer2().ronda(tipoPiedra);
                    return true;
                } else {
                    Gomoku.getPlayer2().setPiedrasPesadas(10);
                    return true;
                }
            } else if (tipoPiedra.equals("Piedra Ligera")) {
                if (Gomoku.getPlayer2().getPiedrasLigeras() > 0) {
                    Gomoku.getPlayer2().ronda(tipoPiedra);
                    return true;
                } else {
                    Gomoku.getPlayer2().setPiedrasLigeras(10);
                    return true;
                }
            } else {
                return true;
            }
        }
    }
}
