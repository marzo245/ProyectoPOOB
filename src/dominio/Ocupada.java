package dominio;

public class Ocupada extends Celda {

    public Ocupada() {

        super();
    }

    public void actuando(int row, int col) {
    }

    public Piedra getPiedra() {
        return piedra;
    }

    public void setPiedra(Piedra piedra) {
        super.setPiedra(piedra);
        if (Gomoku.getTurno().equals("Blanca")) {
            piedra.setName("Blanca");
        } else {
            piedra.setName("Negra");
        }
    }

    @Override
    public Celda clonar() {
        return new Ocupada();
    }
}