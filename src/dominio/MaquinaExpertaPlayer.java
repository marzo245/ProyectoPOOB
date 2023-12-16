package dominio;

public class MaquinaExpertaPlayer extends Player {

    public MaquinaExpertaPlayer(String name, String color) {
        super(name, color);
    }

    public Celda[][] jugar(int row, int col, String tipoPiedra) {
        Gomoku.setTurno("Negra");
        // Obtener el tablero actual
        Celda[][] tablero = Gomoku.getBoard();

        // Encontrar la mejor jugada
        int[] mejorJugada = encontrarMejorJugada(tablero, getColor());

        // Retornar el tablero actualizado
        return Gomoku.getInstance().getModoDeJuego().jugar(mejorJugada[0], mejorJugada[1], "Piedra Normal");
    }

    private int[] encontrarMejorJugada(Celda[][] tablero, String color) {
        int[] mejorJugada = new int[] { -1, -1, Integer.MIN_VALUE }; // [fila, columna, evaluacion]

        // Evaluar cada celda del tablero
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                // Verificar si la celda está vacía
                if (tablero[i][j] instanceof Vacia) {
                    // Simular la jugada
                    tablero[i][j] = new Ocupada();
                    tablero[i][j].setPiedra(new PiedraPesada(i, j));
                    // Evaluar la bondad de la jugada
                    int evaluacion = evaluarTablero(tablero, color);

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

    private int evaluarTablero(Celda[][] tablero, String color) {
        int evaluacion = 0;

        // Evaluar filas
        for (int i = 0; i < tablero.length; i++) {
            evaluacion += contarFichasEnLinea(tablero, i, 0, 0, 1, color);
        }

        // Evaluar columnas
        for (int j = 0; j < tablero[0].length; j++) {
            evaluacion += contarFichasEnLinea(tablero, 0, j, 1, 0, color);
        }

        // Evaluar diagonales
        evaluacion += contarFichasEnLinea(tablero, 0, 0, 1, 1, color);
        evaluacion += contarFichasEnLinea(tablero, 0, tablero[0].length - 1, 1, -1, color);

        return evaluacion;
    }

    private int contarFichasEnLinea(Celda[][] tablero, int startRow, int startCol, int deltaRow, int deltaCol,
            String color) {
        int count = 0;
        for (int i = 0; i < 5; i++) {
            int row = startRow + i * deltaRow;
            int col = startCol + i * deltaCol;
            if (row >= 0 && row < tablero.length && col >= 0 && col < tablero[0].length) {
                if (tablero[row][col] instanceof Ocupada && tablero[row][col].getPiedra().getName().equals(color)) {
                    count++;
                }
            }
        }
        return count;
    }
}
