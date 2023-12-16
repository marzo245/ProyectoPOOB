package dominio;

import java.util.Random;

public class MaquinaAgresivaPlayer extends Player {
    private final Random random;

    public MaquinaAgresivaPlayer(String nombre, String color) {
        super(nombre, color);
        this.random = new Random();
    }

    public Celda[][] jugar(int row, int col, String tipoPiedra) {
        Gomoku.setTurno("Negra");
        // Obtener el tablero actual
        Celda[][] tablero = Gomoku.getBoard();

        // Verificar si el jugador ha realizado un movimiento
        if (row != -1 && col != -1) {
            // Intentar bloquear un lado de una posible línea de tres del jugador
            int[] bloqueo = bloquearLineaDeTres(tablero, getColor(), row, col);

            // Si hay una jugada de bloqueo, realizarla
            if (bloqueo != null) {
                return Gomoku.getInstance().getModoDeJuego().jugar(bloqueo[0], bloqueo[1], "Piedra Pesada");
            }
        }

        // Si no hay una jugada de bloqueo, realizar una jugada normal
        return jugarRandom(tablero);
    }

    private int[] bloquearLineaDeTres(Celda[][] tablero, String color, int jugadorRow, int jugadorCol) {
        // Definir posiciones relativas para buscar líneas de tres
        int[][] posicionesRelativas = { { -1, -1 }, { -1, 0 }, { -1, 1 } };

        // Evaluar cada celda del tablero
        for (int[] posicion : posicionesRelativas) {
            int offsetX = posicion[0];
            int offsetY = posicion[1];

            // Calcular las posiciones en la línea de tres
            int row1 = jugadorRow + offsetX;
            int col1 = jugadorCol + offsetY;
            int row2 = jugadorRow;
            int col2 = jugadorCol;
            int row3 = jugadorRow - offsetX;
            int col3 = jugadorCol - offsetY;

            // Verificar si las tres posiciones están dentro del tablero y ocupadas por
            // fichas del jugador
            if (esValido(row1, col1, tablero) && esValido(row2, col2, tablero) && esValido(row3, col3, tablero)
                    && tablero[row1][col1] instanceof Ocupada && tablero[row2][col2] instanceof Ocupada
                    && tablero[row3][col3] instanceof Ocupada
                    && tablero[row1][col1].getPiedra().getName().equals(color)
                    && tablero[row2][col2].getPiedra().getName().equals(color)
                    && tablero[row3][col3].getPiedra().getName().equals(color)) {
                // Bloquear el lado derecho o izquierdo, según la posición relativa
                int bloqueoRow = jugadorRow - offsetX;
                int bloqueoCol = jugadorCol - offsetY;
                return new int[] { bloqueoRow, bloqueoCol };
            }
        }

        // Si no se encuentra una jugada de bloqueo, devolver null
        return null;
    }

    private boolean esValido(int row, int col, Celda[][] tablero) {
        return row >= 0 && row < tablero.length && col >= 0 && col < tablero[0].length;
    }

    private Celda[][] jugarRandom(Celda[][] tablero) {
        // Buscar una posición aleatoria vacía en el tablero
        int row, col;
        do {
            row = random.nextInt(tablero.length);
            col = random.nextInt(tablero[0].length);
        } while (!(tablero[row][col] instanceof Vacia));

        // Realizar la jugada en la posición aleatoria
        return Gomoku.getInstance().getModoDeJuego().jugar(row, col, "Piedra Pesada");
    }
}
