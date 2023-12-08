package dominio;

public class MaquinaAgresivaPlayer extends Player {
    public MaquinaAgresivaPlayer(String nombre, String color) {
        super(nombre, color);
    }

    public Celda[][] jugar(int row, int col, String tipoPiedra) {
        return Gomoku.getInstance().getModoDeJuego().jugar(row, col, "PiedraPesada");
    }
}