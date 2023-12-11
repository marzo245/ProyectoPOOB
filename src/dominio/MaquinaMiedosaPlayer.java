package dominio;

public class MaquinaMiedosaPlayer extends Player {

    public MaquinaMiedosaPlayer(String nombre, String color) {
        super(nombre, color);
    }
    public Celda[][] jugar(int row, int col, String tipoPiedra) {
        return Gomoku.getInstance().getModoDeJuego().jugar(row, col, "PiedraPesada");
    }
}