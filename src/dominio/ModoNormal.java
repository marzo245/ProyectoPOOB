package dominio;

public class ModoNormal extends ModoJuego {
    public ModoNormal() {
        super();
    }

    public Celda[][] jugar(int row, int col, String tipoPiedra) {
        return ponerPiedra(row, col, tipoPiedra);
    }

}
