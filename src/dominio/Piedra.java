package dominio;

public abstract class Piedra {
    protected static int vida = -1;
    protected String nombre = "Nothing";
    protected static int row;
    protected static int col;

    public abstract void setVida(int a);

    public abstract void ronda();

    public void setName(String name) {
        nombre = name;

    }

    public String getName() {
        return nombre;
    }
}
