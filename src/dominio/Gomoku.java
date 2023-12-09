package dominio;

/**
 * La clase Gomoku representa el juego de Gomoku.
 * Contiene la lógica principal del juego y mantiene el estado del tablero y los
 * jugadores.
 * Autor: Chicuazuque-Sierra
 * version 2.0 - 2023/09/12
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
     * 
     * @param NombreJugador1     Nombre del primer jugador
     * @param tipo1              Tipo del primer jugador
     * @param NombreJugador2     Nombre del segundo jugador
     * @param tipo2              Tipo del segundo jugador
     * @param modoDeJuegoElegido Modo de juego elegido
     * 
     */
    public Gomoku(String NombreJugador1, String tipo1, String NombreJugador2, String tipo2, String modoDeJuegoElegido) {
        setPlayer1(tipo1, NombreJugador1);
        setPlayer2(tipo2, NombreJugador2);
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
    }

    /**
     * Realiza una jugada de algun jugador de pendiendo del turno.
     * Predefine el tipo de piedra como "PiedraPesada".
     * 
     * @param row Fila de la celda
     * @param col Columna de la celda
     * @return Tablero del juego
     */
    public Celda[][] jugada(int row, int col) {
        if (validarCantidadPiedras()) {
            Celda[][] temporal;

            if (turno.equals("Blanca")) {
                if (!(player2 instanceof HumanoPlayer)) {
                    temporal = player1.jugar(row, col, "PiedraPesada");
                    Gomoku.getInstance().setBoard(temporal); // Actualiza el tablero en Gomoku
                    turno = "Negra";
                    return temporal;
                } else {
                    temporal = player1.jugar(row, col, "PiedraPesada");
                    Gomoku.getInstance().setBoard(temporal);
                    turno = "Negra";
                    return temporal;
                }
            } else {
                temporal = player2.jugar(row, col, "Piedrapesada");
                Gomoku.getInstance().setBoard(temporal);
                turno = "Blanca";
                return temporal;
            }
        } else {
            return Gomoku.getBoard();
        }
    }

    /**
     * Realiza una jugada en el juego.
     * 
     * @param row        Fila de la celda
     * @param col        Columna de la celda
     * @param tipoPiedra Tipo de piedra
     * @return Tablero del juego
     */
    public Celda[][] jugada(int row, int col, String tipoPiedra) {
        if (validarCantidadPiedras()) {
            return modoJuego.jugar(row, col, tipoPiedra);
        } else {
            return board;
        }
    }

    /**
     * Limpia el tablero del juego.
     * 
     */
    public void clearBoard() {
        crearBoard(0, 0);
    }

    /**
     * Establece el tablero del juego.
     * 
     * @param board Tablero del juego
     */
    public void setBoard(Celda[][] board) {
        Gomoku.board = board;
    }

    /**
     * Devuelve el tablero del juego.
     * 
     * @return Tablero del juego
     */
    public static Celda[][] getBoard() {
        return board;
    }

    /**
     * Establece si el movimiento es correcto.
     * 
     * @param movimiento Movimiento correcto
     * 
     */
    public void setHayMensjae(Boolean movimiento) {
        this.hayMensaje = movimiento;
    }

    /**
     * Devuelve si el movimiento es correcto.
     * 
     * @return Movimiento correcto
     */
    public Boolean getHayMensaje() {
        return hayMensaje;
    }

    /**
     * Devuelve el mensaje de salida.
     * 
     * @return Mensaje de salida
     */
    public String getMensaje() {
        return mensajeSalida;
    }

    /**
     * Establece el mensaje de salida.
     * 
     * @param mensaje Mensaje de salida
     * 
     */
    public void setMensaje(String mensaje) {
        mensajeSalida = mensaje;
    }

    /**
     * Establece el mensaje de salida.
     * 
     * @param mensaje Mensaje de salida
     */
    public void mensaje(String mensaje) {
        mensajeSalida = mensaje;
    }

    /**
     * Devuelve el modo de juego actual.
     * 
     * @return Modo de juego actual
     */
    public ModoJuego getModoDeJuego() {
        return modoJuego;
    }

    /**
     * Establece el modo de juego.
     * 
     * @param modoDeJuegoElegido Modo de juego elegido
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
     * Devuelve el primer jugador.
     * 
     * @return Primer jugador
     */
    public static Player getPlayer1() {
        return player1;
    }

    /**
     * Devuelve el segundo jugador.
     * 
     * @return Segundo jugador
     */
    public static Player getPlayer2() {
        return player2;
    }

    /**
     * Establece el primer jugador.
     * 
     * @param tipo           Tipo de jugador
     * @param NombreJugador1 Nombre del jugador
     */
    public static void setPlayer1(String tipo, String NombreJugador1) {
        if (tipo.equals("HumanoPlayer")) {
            player1 = new HumanoPlayer(NombreJugador1, "Blanca");
        } else if (tipo.equals("MaquinaAgresivaPlayer")) {
            player1 = new MaquinaAgresivaPlayer(NombreJugador1, "Blanca");
        } else if (tipo.equals("MaquinaMiedosaPlayer")) {
            player1 = new MaquinaMiedosaPlayer(NombreJugador1, "Blanca");
        } else if (tipo.equals("MaquinaExpertaPlayer")) {
            player1 = new MaquinaExpertaPlayer(NombreJugador1, "Blanca");
        }
    }

    /**
     * Establece el segundo jugador.
     * 
     * @param tipo           Tipo de jugador
     * @param NombreJugador1 Nombre del jugador
     * 
     */
    public static void setPlayer2(String tipo, String NombreJugador1) {
        if (tipo.equals("HumanoPlayer")) {
            player2 = new HumanoPlayer(NombreJugador1, "Negra");
        } else if (tipo.equals("MaquinaAgresivaPlayer")) {
            player2 = new MaquinaAgresivaPlayer(NombreJugador1, "Negra");
        } else if (tipo.equals("MaquinaMiedosaPlayer")) {
            player2 = new MaquinaMiedosaPlayer(NombreJugador1, "Negra");
        } else if (tipo.equals("MaquinaExpertaPlayer")) {
            player2 = new MaquinaExpertaPlayer(NombreJugador1, "Negra");
        }
    }

    /**
     * Devuelve el turno actual.
     * 
     * @return Turno actual
     */
    public static String getTurno() {
        return turno;
    }

    /**
     * Establece el turno actual.
     * 
     * @param t Turno actual
     */
    public static void setTurno(String t) {
        turno = t;
    }

    /**
     * Devuelve la celda en la posición especificada.
     * 
     * @param row Fila de la celda
     * @param col Columna de la celda
     * @return Celda en la posición especificada
     */
    public static Celda getCelda(int row, int col) {
        Celda celda = board[row][col];
        if (celda instanceof Vacia) {
            Piedra nuevaPiedra = new PiedraVacia(); // Reemplaza con la clase de piedra que necesites
            celda.setPiedra(nuevaPiedra);
        }
        return celda;
    }

    /**
     * Establece la celda en la posición especificada.
     */
    public void setCelda(int row, int col, Celda celda) {
        // Crea una nueva instancia de la celda para evitar problemas con la referencia
        board[row][col] = celda.clonar();
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
                    player2.setPiedrasPesadas(10);
                    return validarCantidadPiedras();
                } else {
                    mensaje("No tienes piedras pesadas");
                    hayMensaje = true;
                    return false;
                }
            }
        }
    }

    public void imprimirTablero() {
        System.out.println("Tablero actual:");

        for (int i = 0; i < Board.HEIGHT; i++) {
            for (int j = 0; j < Board.WIDTH; j++) {
                System.out.print(board[i][j].toString() + " ");
            }
            System.out.println();
        }
    }
}