package dominio;

/**
 * Esta es la clase encargada de definir el comportamiento de las piedras
 * Autor: Chicuazuque-Sierra
 * Version: 1.2 09/12/2023
 */
public abstract class Piedra {
    protected int vida;
    protected String nombre = "Nothing";
    protected int row;
    protected int col;

    /**
     * Constructor de la clase
     */
    public Piedra(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Define una nueva vida para la piedra.
     * 
     * @param a nueva vida de la piedra.
     */
    public void setVida(int a) {
        vida = a;
    }

    /**
     * Este metodo define cuando pasa una ronda en el juego
     */
    public abstract void ronda();

    /**
     * Este metodo define el nombre de la piedra
     * 
     * @param name
     */
    public void setName(String name) {
        nombre = name;

    }

    /**
     * Este metodo devuelve la vida de la piedra
     * 
     * @return vida de la piedra
     */
    public int getVida() {
        return vida;
    }

    /**
     * Este metodo devuelve el nombre de la piedra
     * 
     * @return nombre de la piedra
     */
    public String getName() {
        return nombre;
    }
}
