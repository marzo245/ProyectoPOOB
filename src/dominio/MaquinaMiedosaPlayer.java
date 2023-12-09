package dominio;

public class MaquinaMiedosaPlayer extends Player {

    public MaquinaMiedosaPlayer(String nombre, String color) {
        super(nombre, color);
    }
    public Celda[][] getBoard() {

        return Gomoku.getInstance().getBoard();

    }

    public Celda[][] jugar(int row, int col, String tipoPiedra) {
        Celda[][] celdasAdyacentes = new Celda[3][3];
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i != 0 || j != 0) {
                    celdasAdyacentes[i + 1][j + 1] = getBoard()[row + i][col + j];
                }
            }
        }
        for (Celda[] fila : celdasAdyacentes) {
            for (Celda celda : fila) {
                if (celda instanceof Ocupada) {
                    return null;
                }
            }
        }

        return getBoard();

    }


}