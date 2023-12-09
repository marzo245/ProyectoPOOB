package dominio;

public abstract class Piedra {
    protected static int vida = -1;
    protected String nombre = "Nothing";
    protected static int row;
    protected static int col;

    public void setVida(int a) {
        vida = a;
    }

    public abstract void ronda();

    public void setName(String name) {
        nombre = name;

    }

    public int getVida() {
        return vida;
    }

    public String getName() {
        return nombre;
    }
}
