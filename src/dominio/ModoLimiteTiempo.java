package dominio;

public class ModoLimiteTiempo extends ModoJuego {
    public ModoLimiteTiempo() {
        super();
    }

    public Celda[][] jugar(int row, int col, String tipoCelda) {
        return ponerPiedra(row, col, tipoCelda);
    }

}
