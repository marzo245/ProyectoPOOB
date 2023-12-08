package dominio;

public class PiedraLigera extends Piedra {

    public PiedraLigera() {
        super();
        super.vida = 3;
    }

    public void setVida(int a) {
        super.vida = a;
    }

    public int getVida() {
        return super.vida;
    }

    public void ronda() {
        if (super.vida == 0) {
            Gomoku.getCelda(row, col).setPiedra("MURIO");
        }
        super.vida -= 1;
    }

}