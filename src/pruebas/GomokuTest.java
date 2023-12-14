package pruebas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dominio.*;

public class GomokuTest {

    @Test
    public void testJugada() {
        Gomoku juego = new Gomoku("Jugador1", "HumanoPlayer", "Jugador2", "HumanoPlayer", "Normal");
        Celda[][] boardBefore = juego.getBoard();

        // Realiza una jugada
        juego.jugada(0, 0);

        // Asegúrate de que el turno cambia después de la jugada
        assertNotEquals("El turno debe cambiar después de la jugada", "Blanca", Gomoku.getTurno());
    }

    @Test
    public void testClearBoard() {
        Gomoku juego = new Gomoku("Jugador1", "HumanoPlayer", "Jugador2", "HumanoPlayer", "Normal");

        // Realiza una jugada
        juego.jugada(0, 0);

        // Limpia el tablero
        juego.clearBoard();

        // Asegúrate de que el tablero está vacío después de limpiar
        for (int i = 0; i < Board.HEIGHT; i++) {
            for (int j = 0; j < Board.WIDTH; j++) {
                assertTrue(juego.getCelda(i, j) instanceof Vacia || juego.getCelda(i, j) instanceof Mina
                        || juego.getCelda(i, j) instanceof Teleport);
            }
        }
    }

    @Test
    public void testCheckWinner() {
        Gomoku juego = new Gomoku("Jugador1", "HumanoPlayer", "Jugador2", "HumanoPlayer", "Normal");
        // En esta prueba debería ganar el jugador1 ya que es forma 5 en línea
        // Realiza una jugada para ganar
        juego.jugada(0, 0); // j1
        juego.jugada(0, 1); // j2
        juego.jugada(1, 0); // j1
        juego.jugada(0, 2); // j2
        juego.jugada(2, 0); // j1
        juego.jugada(0, 3); // j2
        juego.jugada(3, 0); // j1
        juego.jugada(0, 4); // j2
        juego.jugada(4, 0); // j1

        // Comprueba si se ha encontrado un ganador
        assertTrue(juego.getSeEncontroGanador());
        assertEquals("Blanca", juego.getGanador());
    }

    @Test
    public void testJugadaAlternante() {
        Gomoku juego = new Gomoku("Jugador1", "HumanoPlayer", "Jugador2", "HumanoPlayer", "Normal");

        // Realiza una serie de jugadas alternantes
        juego.jugada(0, 0); // j1
        assertEquals("Negra", Gomoku.getTurno()); // Asegúrate de que el turno cambie correctamente
        juego.jugada(0, 1); // j2
        assertEquals("Blanca", Gomoku.getTurno()); // Asegúrate de que el turno cambie correctamente
        juego.jugada(1, 0); // j1
        assertEquals("Negra", Gomoku.getTurno()); // Asegúrate de que el turno cambie correctamente
    }

    @Test
    public void testSetAndGetMensaje() {
        Gomoku juego = new Gomoku("Jugador1", "HumanoPlayer", "Jugador2", "HumanoPlayer", "Normal");

        // Establece un mensaje
        juego.setMensaje("Mensaje de prueba");

        // Asegúrate de que el mensaje se establezca correctamente
        assertEquals("Mensaje de prueba", juego.getMensaje());
    }

    @Test
    public void testSetAndGetPosicionesGanadoras() {
        Gomoku juego = new Gomoku("Jugador1", "HumanoPlayer", "Jugador2", "HumanoPlayer", "Normal");

        // Establece posiciones ganadoras
        int[][] posicionesGanadoras = { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 3, 0 }, { 4, 0 } };
        juego.setPosicionesGanadoras(posicionesGanadoras);

        // Asegúrate de que las posiciones ganadoras se establezcan correctamente
        assertArrayEquals(posicionesGanadoras, juego.getPosicionesGanadoras());
    }
}
