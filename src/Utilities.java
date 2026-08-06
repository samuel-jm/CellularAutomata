public class Utilities {
    public static void initCells(Cell[][] cells, boolean alive) {
        for(int row = 0; row < cells.length; row++) {
            for(int col = 0; col < cells.length; col++) {
                cells[row][col] = new Cell(alive);
                cells[row][col].setAlive(alive);
            }
        }
    }
}
