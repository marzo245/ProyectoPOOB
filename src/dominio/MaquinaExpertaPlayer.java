package dominio;

public class MaquinaExpertaPlayer extends Player {
    public MaquinaExpertaPlayer(String name, String color) {
        super(name, color);
    }

    public Celda[][] jugar(int row, int col, String tipoPiedra) {
        return Gomoku.getInstance().getModoDeJuego().jugar(row, col, "PiedraPesada");
    }
}