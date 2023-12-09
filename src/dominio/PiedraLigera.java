package dominio;

public class PiedraLigera extends Piedra {

    public PiedraLigera() {
        super();
        super.vida = 3;
    }

    public void ronda() {
        if (super.vida == 0) {
            Gomoku.getCelda(row, col).setPiedra("MURIO");
        }
        super.vida -= 1;
    }

}