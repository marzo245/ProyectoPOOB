package dominio;

public abstract class ModoJuego {
    protected static String modo;

    public ModoJuego() {

    }

    public String getModo() {
        return modo;
    }

    public abstract Celda[][] jugar(int row, int col, String tipoCelda);

}