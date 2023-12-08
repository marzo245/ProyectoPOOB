package dominio;

/**
 * La clase Gomoku representa el juego de Gomoku.
 * Contiene la lógica principal del juego y mantiene el estado del tablero y los
 * jugadores.
 */
public class Gomoku {
    // Tablero del juego
    private static Celda[][] board = new Celda[Board.HEIGHT][Board.WIDTH];
    // Jugadores del juego
    private static Player player1;
    private static Player player2;
    // Instancia única del juego (patrón Singleton)
    private static Gomoku instance;
    // Turno actual
    private static String turno;
    // Indica si se ha encontrado un ganador
    private boolean winnerFound;
    // Modo de juego actual
    private ModoJuego modoJuego;
    // Mensaje de salida
    private String mensajeSalida;
    // Indica si el movimiento es correcto
    private Boolean hayMensaje;

    /**
     * Devuelve la instancia única del juego.
     * Si la instancia no existe, se crea una nueva.
     */
    public static Gomoku getInstance() {
        if (instance == null) {
            instance = new Gomoku("player1", "HumanoPlayer", "player2", "HumanoPlayer", "Normal");
        }
        return instance;
    }

    /**
     * Constructor de Gomoku.
     * Inicializa los jugadores, el turno, el modo de juego y el estado del ganador.
     */
    public Gomoku(String NombreJugador1, String tipo1, String NombreJugador2, String tipo2, String modoDeJuegoElegido) {
        setPlayer1(tipo1, NombreJugador1);
        setPlayer2(tipo2, NombreJugador2);
        turno = player1.getColor();

        turno = "Blanca";
        if (modoDeJuegoElegido.equals("Normal")) {
            modoJuego = new ModoNormal();
            crearBoard(3, 3);
        } else if (modoDeJuegoElegido.equals("LimiteTiempo")) {
            modoJuego = new ModoLimiteTiempo();
            crearBoard(0, 0);
        } else if (modoDeJuegoElegido.equals("LimiteFichas")) {
            modoJuego = new ModoLimiteFichas();
            crearBoard(0, 0);
        } else {
            modoJuego = new ModoNormal();
        }
        winnerFound = false;
    }

    /**
     * Realiza una jugada en el juego.
     */
    public Celda[][] jugada(int row, int col) {
        if (validarCantidadPiedras()) {
            return modoJuego.jugar(row, col, "PiedraPesada");
        } else {
            return board;
        }
    }

    /**
     * Realiza una jugada en el juego.
     */
    public Celda[][] jugada(int row, int col, String tipoPiedra) {
        if (validarCantidadPiedras()) {
            return modoJuego.jugar(row, col, tipoPiedra);
        } else {
            return board;
        }
    }

    /**
     * Establece si el movimiento es correcto.
     */
    public void setHayMensjae(Boolean movimiento) {
        this.hayMensaje = movimiento;
    }

    /**
     * Devuelve si el movimiento es correcto.
     */
    public Boolean getHayMensaje() {
        return hayMensaje;
    }

    /**
     * Limpia el tablero del juego.
     */
    public void clearBoard() {
        crearBoard(0, 0);
    }

    /**
     * Devuelve el tablero del juego.
     */
    public static Celda[][] getBoard() {
        return board;
    }

    /**
     * Devuelve el primer jugador.
     */
    public static Player getPlayer1() {
        return player1;
    }

    /**
     * Devuelve el modo de juego actual.
     */
    public String getModoDeJuego() {
        return modoJuego.getModo();
    }

    public void setMensaje(String mensaje) {
        mensajeSalida = mensaje;
    }

    /**
     * Establece el modo de juego.
     */
    public void setModoDeJuego(String modoDeJuegoElegido) {
        if (modoDeJuegoElegido.equals("Normal")) {
            modoJuego = new ModoNormal();
        } else if (modoDeJuegoElegido.equals("LimiteTiempo")) {
            modoJuego = new ModoLimiteTiempo();
        } else if (modoDeJuegoElegido.equals("LimiteFichas")) {
            modoJuego = new ModoLimiteFichas();
        } else {
            modoJuego = new ModoNormal();
        }
    }

    /**
     * Devuelve el segundo jugador.
     */
    public static Player getPlayer2() {
        return player2;
    }

    /**
     * Devuelve el turno actual.
     */
    public static String getTurno() {
        return turno;
    }

    /**
     * Establece el turno actual.
     */
    public static void setTurno(String t) {
        turno = t;
    }

    /**
     * Establece el primer jugador.
     */
    public static void setPlayer1(String tipo, String NombreJugador1) {
        if (tipo.equals("HumanoPlayer")) {
            player1 = new HumanoPlayer(NombreJugador1, "WHITE");
        } else if (tipo.equals("MaquinaAgresivaPlayer")) {
            player1 = new MaquinaAgresivaPlayer(NombreJugador1, "WHITE");
        } else if (tipo.equals("MaquinaMiedosaPlayer")) {
            player1 = new MaquinaMiedosaPlayer(NombreJugador1, "WHITE");
        } else if (tipo.equals("MaquinaExpertaPlayer")) {
            player1 = new MaquinaExpertaPlayer(NombreJugador1, "WHITE");
        }
    }

    /**
     * Establece el segundo jugador.
     */
    public static void setPlayer2(String tipo, String NombreJugador1) {
        if (tipo.equals("HumanoPlayer")) {
            player2 = new HumanoPlayer(NombreJugador1, "BLACK");
        } else if (tipo.equals("MaquinaAgresivaPlayer")) {
            player2 = new MaquinaAgresivaPlayer(NombreJugador1, "BLACK");
        } else if (tipo.equals("MaquinaMiedosaPlayer")) {
            player2 = new MaquinaMiedosaPlayer(NombreJugador1, "BLACK");
        } else if (tipo.equals("MaquinaExpertaPlayer")) {
            player2 = new MaquinaExpertaPlayer(NombreJugador1, "BLACK");
        }
    }

    /**
     * Devuelve la celda en la posición especificada.
     */
    public static Celda getCelda(int row, int col) {
        return board[row][col];
    }

    /**
     * Establece la celda en la posición especificada.
     */
    public void setCelda(int row, int col, Celda celda) {
        board[row][col] = celda;
    }

    /**
     * Crea el tablero del juego.
     * Inicialmente, todas las celdas están vacías.
     * Luego, se coloca una mina y un teletransportador en posiciones aleatorias.
     */
    public static void crearBoard(int minasColoar, int teleportColocar) {
        for (int i = 0; i < Board.HEIGHT; i++) {
            for (int j = 0; j < Board.WIDTH; j++) {
                board[i][j] = new Vacia();
            }
        }
        for (int i = 0; i < minasColoar; i++) {
            int mineRow, mineCol;
            do {
                mineRow = (int) (Math.random() * Board.HEIGHT);
                mineCol = (int) (Math.random() * Board.WIDTH);
            } while (board[mineRow][mineCol] instanceof Ocupada);
            board[mineRow][mineCol] = new Mina();
        }
        for (int j = 0; j < teleportColocar; j++) {
            int telepRow, telepCol;
            do {
                telepRow = (int) (Math.random() * Board.HEIGHT);
                telepCol = (int) (Math.random() * Board.WIDTH);
            } while (board[telepRow][telepCol] instanceof Ocupada);
            board[telepRow][telepCol] = new Teleport();
        }
    }

    /**
     * Establece el mensaje de salida.
     */
    public void mensaje(String mensaje) {
        mensajeSalida = mensaje;
    }

    private boolean validarCantidadPiedras() {
        if (turno.equals("Blanca")) {
            if (player1.getPiedrasPesadas() > 0) {
                player1.ronda("Pesada");
                return true;
            } else {
                if (modoJuego instanceof ModoNormal) {
                    player1.setPiedrasPesadas(10);
                    return validarCantidadPiedras();
                } else {

                    mensaje("No tienes piedras pesadas");
                    hayMensaje = true;
                    return false;
                }
            }
        } else {
            if (player2.getPiedrasPesadas() > 0) {
                player2.ronda("Pesada");
                return true;
            } else {
                if (modoJuego instanceof ModoNormal) {
                    player1.setPiedrasPesadas(10);
                    return validarCantidadPiedras();
                } else {

                    mensaje("No tienes piedras pesadas");
                    hayMensaje = true;
                    return false;
                }
            }
        }
    }
}