package dominio;

public class MaquinaMiedosaPlayer extends Player {

    public MaquinaMiedosaPlayer(String name, String color) {
        super(name, color);
    }

    public Celda[][] jugar(int row, int col, String tipoPiedra) {
        Gomoku.setTurno("Negra");
        // Obtener el tablero actual
        Celda[][] tablero = Gomoku.getBoard();

        // Encontrar la mejor jugada lejos del último movimiento del jugador
        int[] mejorJugada = encontrarMejorJugadaLejosDelUltimoMovimiento(tablero, getColor(), row, col);

        // Retornar el tablero actualizado
        return Gomoku.getInstance().getModoDeJuego().jugar(mejorJugada[0], mejorJugada[1], "Piedra Pesada");
    }

    private int[] encontrarMejorJugadaLejosDelUltimoMovimiento(Celda[][] tablero, String color, int lastMoveRow,
            int lastMoveCol) {
        int[] mejorJugada = new int[] { -1, -1, Integer.MIN_VALUE }; // [fila, columna, evaluacion]

        // Evaluar cada celda del tablero
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                // Verificar si la celda está vacía
                if (tablero[i][j] instanceof Vacia) {
                    // Evaluar la distancia al último movimiento del jugador
                    int distancia = calcularDistancia(i, j, lastMoveRow, lastMoveCol);

                    // Evaluar la jugada simulando poner una ficha en esa posición
                    tablero[i][j] = new Ocupada();
                    tablero[i][j].setPiedra(new PiedraPesada(i, j));
                    // Evaluar la bondad de la jugada
                    int evaluacion = distancia; // Cambiado a distancia para buscar la posición más lejana

                    // Si es la mejor jugada encontrada hasta ahora, actualizar la mejorJugada
                    if (evaluacion > mejorJugada[2]) {
                        mejorJugada[0] = i;
                        mejorJugada[1] = j;
                        mejorJugada[2] = evaluacion;
                    }

                    // Deshacer la jugada simulada para dejar el tablero en su estado original
                    tablero[i][j] = new Vacia();
                }
            }
        }

        return mejorJugada;
    }

    private int calcularDistancia(int row1, int col1, int row2, int col2) {
        return Math.max(Math.abs(row1 - row2), Math.abs(col1 - col2));
    }

}
