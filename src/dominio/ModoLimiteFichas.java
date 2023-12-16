package dominio;

/**
 * Esta clase es la encargada de definir el modo de juego Limite de fichas.
 * Autor: Chicuazuque-Sierra
 * Version: 1.2 09/12/2023
 */
public class ModoLimiteFichas extends ModoJuego {

    /**
     * Constructor de la clase.
     */
    public ModoLimiteFichas() {
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
        return Gomoku.getBoard();
    }

    protected boolean validarCantidadPiedras(String tipoPiedra) {
        if (Gomoku.getTurno().equals("Blanca")) {
            if (tipoPiedra.equals("Piedra Pesada")) {
                if (Gomoku.getPlayer1().getPiedrasPesadas() == 0) {
                    if (Gomoku.getPlayer1().getPiedrasLigeras() == 0) {
                        Gomoku.getInstance().mensaje("Te quedaste sin piedras");
                        Gomoku.getInstance().setSeEncontroGanador(true);
                        Gomoku.getInstance().setGanador("Negra");
                        Gomoku.getInstance().setHayMensjae(true);
                        Gomoku.getInstance().setTurno("Blanca");
                        return false;
                    } else {
                        Gomoku.getInstance().mensaje("Te quedaste sin piedras pesadas");
                        Gomoku.getInstance().setHayMensjae(true);
                        Gomoku.getInstance().setTurno("Blanca");
                        return false;
                    }
                } else {
                    Gomoku.getPlayer1().ronda(tipoPiedra);
                    return true;
                }
            } else if (tipoPiedra.equals("Piedra Ligera")) {
                if (Gomoku.getPlayer1().getPiedrasLigeras() == 0) {
                    if (Gomoku.getPlayer1().getPiedrasPesadas() == 0) {
                        Gomoku.getInstance().mensaje("Te quedaste sin piedras");
                        Gomoku.getInstance().setSeEncontroGanador(true);
                        Gomoku.getInstance().setGanador("Negra");
                        Gomoku.getInstance().setHayMensjae(true);
                        Gomoku.getInstance().setTurno("Blanca");
                        return false;
                    } else {
                        Gomoku.getInstance().mensaje("Te quedaste sin piedras ligeras");
                        Gomoku.getInstance().setHayMensjae(true);
                        Gomoku.getInstance().setTurno("Blanca");
                        return false;
                    }
                } else {
                    Gomoku.getPlayer1().ronda(tipoPiedra);
                    return true;
                }
            } else {
                return true;
            }
        } else {
            if (tipoPiedra.equals("Piedra Pesada")) {
                if (Gomoku.getPlayer2().getPiedrasPesadas() == 0) {
                    if (Gomoku.getPlayer2().getPiedrasLigeras() == 0) {
                        Gomoku.getInstance().mensaje("Te quedaste sin piedras");
                        Gomoku.getInstance().setSeEncontroGanador(true);
                        Gomoku.getInstance().setGanador("Blanca");
                        Gomoku.getInstance().setHayMensjae(true);
                        Gomoku.getInstance().setTurno("Negra");
                        return false;
                    } else {
                        Gomoku.getInstance().mensaje("Te quedaste sin piedras pesadas");
                        Gomoku.getInstance().setHayMensjae(true);
                        Gomoku.getInstance().setTurno("Negra");
                        return false;
                    }
                } else {
                    Gomoku.getPlayer2().ronda(tipoPiedra);
                    return true;
                }
            } else if (tipoPiedra.equals("Piedra Ligera")) {
                if (Gomoku.getPlayer2().getPiedrasLigeras() == 0) {
                    if (Gomoku.getPlayer2().getPiedrasPesadas() == 0) {
                        Gomoku.getInstance().mensaje("Te quedaste sin piedras");
                        Gomoku.getInstance().setSeEncontroGanador(true);
                        Gomoku.getInstance().setGanador("Blanca");
                        Gomoku.getInstance().setHayMensjae(true);
                        Gomoku.getInstance().setTurno("Negra");
                        return false;
                    } else {
                        Gomoku.getInstance().mensaje("Te quedaste sin piedras ligeras");
                        Gomoku.getInstance().setHayMensjae(true);
                        Gomoku.getInstance().setTurno("Negra");
                        return false;
                    }
                } else {
                    Gomoku.getPlayer2().ronda(tipoPiedra);
                    return true;
                }
            } else {
                return true;
            }
        }
    }

}
