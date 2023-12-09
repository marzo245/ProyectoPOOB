package dominio;

public class ModoLimiteFichas extends ModoJuego {
    public ModoLimiteFichas() {
        super();
    }

    public Celda[][] jugar(int row, int col, String tipoCelda) {
        return ponerPiedra(row, col, tipoCelda);
    }

}
