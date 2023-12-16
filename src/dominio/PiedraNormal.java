package dominio;

public class PiedraNormal extends Piedra {
    /**
     * Constructor de la clase
     */
    public PiedraNormal(int row, int col) {
        super(row, col);
        vida = 3;
    }

    /**
     * Este metodo define cuando pasa una ronda en el juego
     */
    public void ronda() {
    }
}
