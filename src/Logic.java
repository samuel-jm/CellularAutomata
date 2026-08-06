import java.util.Random;

public abstract class Logic {
    protected String ruleString_;
    protected boolean wrap_;
    protected static Random random_ = new Random();

    public Logic(String ruleString) {
        ruleString_ = ruleString;
        wrap_ = true;
    }

    public Cell[][] tick(Cell[][] cells) {
        Cell[][] newCells = new Cell[cells.length][cells[0].length];
        Utilities.initCells(newCells, false);
        for(int row = 0; row < cells.length; row++) {
            for(int col = 0; col < cells.length; col++) {
                int neighbours = neighbours(cells, row, col);
                boolean newCellState = updateCellState(cells, row, col, neighbours);
                newCells[row][col].setAlive(newCellState);
            }
            endOfRowHook(cells, newCells, row);
        }
        return newCells;
    }

    public Cell[][] reverse(Cell[][] cells) {
        for(Cell[] row : cells) {
            for(Cell cell : row) {
                cell.setAlive(!cell.getAlive());
            }
        }
        return cells;
    }

    public void setWrap(boolean wrap) { wrap_ = wrap; }
    public abstract Cell[][] clear(Cell[][] cells);
    public abstract int neighbours(Cell[][] cells, int row, int col);
    public abstract boolean updateCellState(Cell[][] cells, int row, int col, int neighbours);
    public abstract Cell[][] random(Cell[][] cells);
    public abstract void setRule(String ruleString);
    public void endOfRowHook(Cell[][] cells, Cell[][] newCells, int row) {}
}
