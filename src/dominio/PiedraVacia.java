package dominio;

public class PiedraVacia extends Piedra {
    public PiedraVacia() {
        super();
        super.vida = -1;
    }

    public void setVida(int a) {
        super.vida = -1;
    }

    public void ronda() {
    }
}