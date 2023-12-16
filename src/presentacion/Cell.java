/**
 * 
 * Esta enumeración restringe los colores que pueden ser almacenados en una celda,
 * ya sea cuando la celda está vacía o contiene los colores negro o blanco.
 * 
 * Los valores adicionales representan características especiales de la celda, como MINA (MiINE),
 * TELETRANSPORTE (TELEPORT) o DORADO (GOLDEN).
 * 
 * @author Chicuazuque-Sierra
 * @version 1.0 25/11/2023
 */
package presentacion;

public enum Cell {

	WHITE, BLACK, EMPTY, MINE, TELEPORT, GOLDEN;

}
