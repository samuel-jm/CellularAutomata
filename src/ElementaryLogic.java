public class ElementaryLogic extends Logic {
    public ElementaryLogic(String ruleString) {
        super(ruleString);
    }

    @Override
    public int neighbours(Cell[][] cells, int row, int col) {
        if(row == 0) return 0;

        Cell[] northRow = cells[row - 1];
        boolean nw = (col == 0 ? northRow[northRow.length - 1] : northRow[col - 1]).getAlive(); // North-east neighbour
        boolean n  = northRow[col].getAlive();                                                  // North      neighbour
        boolean ne = (col == northRow.length - 1 ? northRow[0] : northRow[col + 1]).getAlive(); // North-west neighbour

        if(!wrap_) {
            if(col == 0) nw = false;
            if(col == northRow.length - 1) ne = false;
        }

        return (nw ? 4 : 0) + (n ? 2 : 0) + (ne ? 1 : 0);
    }

    @Override
    public boolean updateCellState(Cell[][] cells, int row, int col, int neighbours) {
        if(row == 0) return cells[row][col].getAlive();

        //If neighbours = 0 then we want the least-significant bit of the binary rule string
        //And if neighbours = 7 we want the most-significant bit, hence (7 - neighbours)
        return ruleString_.charAt(7 - neighbours) == '1';
    }

    @Override
    public Cell[][] random(Cell[][] cells) {
        Utilities.initCells(cells, false);
        for(Cell cell : cells[0]) {
            cell.setAlive(random_.nextBoolean());
        }
        return cells;
    }

    @Override
    public Cell[][] clear(Cell[][] cells) {
        for(Cell[] row : cells) {
            for(Cell cell : row) {
                cell.setAlive(false);
            }
        }
        cells[0][cells[0].length / 2].setAlive(true);
        return cells;
    }

    @Override
    public void setRule(String ruleString) {
        ruleString_ = String.format("%8s", Integer.toBinaryString(Integer.parseInt(ruleString))).replace(" ", "0");
    }

    @Override
    public void endOfRowHook(Cell[][] cells, Cell[][] newCells, int row) {
        cells[row] = newCells[row];
    }
}
