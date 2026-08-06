/**
 * This class defines the simulation's logic when the "elementary" mode is active. The elementary mode works as follows:
 * <p>
 *     Given a grid of cells, each row is processed one at a time, starting with the second row. For every cell, we
 *     consider its NW, N, and NE cells, this represents the most-to-least significant bits of a three-bit binary
 *     number which we use as a left-shift amount. The rule string represents an eight-bit number giving us 256 possible
 *     rules. When deciding the state of our current cell, we use the three-bit value we calculated to determine which
 *     bit in our rule string to check, if the correspsonding bit is 1 the cell is alive, if it is 0 the cell is dead.
 * </p>
 * <p>
 *     For example, if we have the rule string 0b00010011 (rule 19) and our current cell has NW, N, and NE cell states
 *     of dead, dead, and alive respectively, then it has a value of 1 for its neighbours. Bit 1 of our rule string is 1 so
 *     the current cell is alive.
 * </p>
 * @see <a href="https://en.wikipedia.org/wiki/Elementary_cellular_automaton">Elementary Cellular Automaton</a>
 */
public class ElementaryLogic extends Logic {

    public ElementaryLogic(String ruleString) {
        super(ruleString);
    }

    /**
     * This method calculates the neighbours of a cell at a given row and column
     * @param cells The current grid configuration
     * @param row The row used to determine the current cell
     * @param col The column used to determine the current cell
     * @return The neighbours of <c>cells[row][col]</c>, a value in the range [0,7]
     */
    @Override
    public int neighbours(Cell[][] cells, int row, int col) {
        if(row == 0) return 0;

        Cell[] northRow = cells[row - 1];
        boolean nw = (col == 0 ? northRow[northRow.length - 1] : northRow[col - 1]).getAlive(); // North-west neighbour
        boolean n  = northRow[col].getAlive();                                                  // North      neighbour
        boolean ne = (col == northRow.length - 1 ? northRow[0] : northRow[col + 1]).getAlive(); // North-east neighbour

        if(!wrap_) {
            if(col == 0) nw = false;
            if(col == northRow.length - 1) ne = false;
        }

        return (nw ? 4 : 0) + (n ? 2 : 0) + (ne ? 1 : 0);
    }

    /**
     * This method updates the state of <c>cells[row][col]</c>
     * @param cells The current grid configuration
     * @param row The row used to determine the current cell
     * @param col The column used to determine the current cell
     * @param neighbours The current cell's neighbours, in the range [0,7]
     * @return The new state of the cell at <c>cells[row][col]</c>
     */
    @Override
    public boolean updateCellState(Cell[][] cells, int row, int col, int neighbours) {
        if(row == 0) return cells[row][col].getAlive();

        //If neighbours = 0 then we want the least-significant bit of the binary rule string
        //And if neighbours = 7 we want the most-significant bit, hence (7 - neighbours)
        return ruleString_.charAt(7 - neighbours) == '1';
    }

    /**
     * This method randomises the state of the first row of cells.
     * With elementary cellular automata, each row is updated according to the configuration of the previous row,
     * therefore, only the first row needs to be randomised
     * @param cells The current cell configuration
     * @return The randomised cell configuration
     */
    @Override
    public Cell[][] random(Cell[][] cells) {
        Utilities.initCells(cells, false);
        for(Cell cell : cells[0]) {
            cell.setAlive(random_.nextBoolean());
        }
        return cells;
    }

    /**
     * This method clears the grid and sets the middle cell of the top row to alive.
     * This is because the elementary mode has interesting results for both a random initial state and when a single
     * cell is alive
     * @param cells The current grid configuration
     * @return The new grid configuration
     */
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

    /**
     * This method sets the rule string to an eight-bit binary representation of <c>ruleString</c>
     * @param ruleString The rule to set, it must represent a value in the range [0,255]
     */
    @Override
    public void setRule(String ruleString) {
        ruleString_ = String.format("%8s", Integer.toBinaryString(Integer.parseInt(ruleString))).replace(" ", "0");
    }

    /**
     * This method is called after each row has been processed in {@link Logic}
     * and updates the current grid row with that completed row
     * @param cells The current grid configuration
     * @param newCells The grid configuration after the given row has been processed
     * @param row The row that has been processed
     */
    @Override
    public void endOfRowHook(Cell[][] cells, Cell[][] newCells, int row) {
        cells[row] = newCells[row];
    }
}
