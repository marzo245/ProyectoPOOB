package dominio;

public class Ocupada extends Celda {
    private static Piedra piedra = null;

    public void actuando(int row, int col) {
    }

    public static Piedra getPiedra() {
        return piedra;
    }

    public static void setPiedra(Piedra Newpiedra) {
        piedra = Newpiedra;
        if (Gomoku.getInstance().getTurno().equals("Blanca")) {
            piedra.setName("WHITE");
        } else {
            piedra.setName("BLACK");
        }
    }
}