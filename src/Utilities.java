/**
 * This class is used to hold various utility methods that will be used throughout the codebase
 */
public class Utilities {
    public static void initCells(Cell[][] cells, boolean alive) {
        for(int row = 0; row < cells.length; row++) {
            for(int col = 0; col < cells[0].length; col++) {
                cells[row][col] = new Cell(alive);
            }
        }
    }
}
