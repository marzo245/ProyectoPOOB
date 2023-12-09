package dominio;

public abstract class ModoJuego {
    protected static String modo;

    public ModoJuego() {

    }

    public String getModo() {
        return modo;
    }

    public abstract Celda[][] jugar(int row, int col, String tipoCelda);

    public Celda[][] ponerPiedra(int row, int col, String tipoPiedra) {
        if (validarPosicion(row, col)) {

            Gomoku.getInstance().setCelda(row, col, new Ocupada());

            // Obtén la celda actual y la piedra asociada
            Celda celda = Gomoku.getCelda(row, col);
            Piedra piedraActual = celda.getPiedra();

            // Verifica si la piedra actual es una instancia de PiedraVacia
            if (piedraActual instanceof PiedraVacia) {
                // Si es PiedraVacia, crea una nueva instancia de Piedra y asígnale el nombre
                Piedra nuevaPiedra;
                if (tipoPiedra.equals("PiedraPesada")) {
                    nuevaPiedra = new PiedraPesada();
                } else {
                    // Agrega condiciones para otros tipos de piedra si es necesario
                    nuevaPiedra = new PiedraLigera();
                }
                nuevaPiedra.setName(Gomoku.getTurno());

                // Asigna la nueva piedra a la celda
                celda.setPiedra(nuevaPiedra);
            } else {
                Gomoku.getInstance().setMensaje("Celda ocupada");
                Gomoku.getInstance().setHayMensjae(true);
            }
        } else {
            Gomoku.getInstance().setMensaje("Celda ocupada");
            Gomoku.getInstance().setHayMensjae(true);
        }
        return Gomoku.getBoard();
    }

    public boolean validarPosicion(int row, int col) {
        if (Gomoku.getCelda(row, col) instanceof Vacia)
            return true;
        else if (Gomoku.getCelda(row, col) instanceof Mina) {
            Gomoku.getCelda(row, col).actuando(row, col);
            Gomoku.getInstance().setMensaje("Has pisado una mina");
            Gomoku.getInstance().setHayMensjae(true);
            return true;
        } else if (Gomoku.getCelda(row, col) instanceof Teleport) {
            Gomoku.getCelda(row, col).actuando(row, col);
            Gomoku.getInstance().setMensaje("Has pisado un teleport");
            Gomoku.getInstance().setHayMensjae(true);
            return false;
        }

        return false;
    }
}