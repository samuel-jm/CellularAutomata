/**
 * This class defines the simulation's logic when the "elementary" mode is NOT active and is the mode to use to simulate
 * <a href="https://en.wikipedia.org/wiki/Conway's_Game_of_Life">Conway's Game of Life</a>, it works as follows:
 * <p>
 *     Given a grid of cells, each cell is processed one at a time. For every cell, we
 *     consider its eight neighbouring cells and count the number of cells which are alive.
 *     The rule string is of the form "Bx/Sy", where "x" and "y" are a series of 0-8 digits in the range [1,8].
 *     "x" represents the number of neighbours a cell must have in order to become alive
 *     "y" represents the number of neighbours a cell must have in order to remain alive
 * </p>
 * <p>
 *     For example, consider the rule string B3/S23 (Conway's Game of Life) and a cell with 2 neighbours:
 *     If the cell is dead then it remains dead since "B3" does not contain "2", whereas if the cell
 *     is alive it will remain alive since "S23" does contain "2".
 * </p>
 * @see <a href="https://en.wikipedia.org/wiki/Cellular_automaton">Cellular Automaton</a>
 */
public class RegularLogic extends Logic{
    private int radius_ = 1;
    private String bornOn_;
    private String surviveOn_;

    public RegularLogic(String ruleString) {
        super(ruleString);

        String[] rules = ruleString.split("/");
        bornOn_ = rules[0].substring(1);
        surviveOn_ = rules[1].substring(1);
    }

    /**
     * This method calculates the neighbours of a cell at a given row and column
     * @param cells The current grid configuration
     * @param row The row used to determine the current cell
     * @param col The column used to determine the current cell
     * @return The neighbours of <c>cells[row][col]</c>, a value in the range [0,8]
     */
    @Override
    public int neighbours(Cell[][] cells, int row, int col) {
        int neighbors = 0;

        for(int i = row - radius_; i <= row + radius_; i++) {
            for(int j = col - radius_; j <= col + radius_; j++) {
                if(i == row && j == col) continue; // A cell cannot be considered its own neighbour

                int tmpI = i;
                int tmpJ = j;
                if(wrap_) {
                    if(i < 0) i = cells.length + i;
                    else i = i % cells.length;

                    if(j < 0) j = cells.length + j;
                    else j = j % cells.length;
                } else {
                    if(i < 0 || i >= cells[0].length || j < 0 || j >= cells.length) continue;
                }
                if(cells[i][j].getAlive()) {
                    neighbors++;
                }
                i = tmpI;
                j = tmpJ;
            }
        }
        return neighbors;
    }

    /**
     * This method updates the state of <c>cells[row][col]</c>
     * @param cells The current grid configuration
     * @param row The row used to determine the current cell
     * @param col The column used to determine the current cell
     * @param neighbours The current cell's neighbours, in the range [0,8]
     * @return The new state of the cell at <c>cells[row][col]</c>
     */
    @Override
    public boolean updateCellState(Cell[][] cells, int row, int col, int neighbours) {
        if(cells[row][col].getAlive()) {
            return surviveOn_.contains(String.valueOf(neighbours));
        } else {
            return bornOn_.contains(String.valueOf(neighbours));
        }
    }

    /**
     * This method randomises each cell's state
     * @param cells The current cell configuration
     * @return The randomised cell configuration
     */
    @Override
    public Cell[][] random(Cell[][] cells) {
        for(Cell[] row : cells) {
            for(Cell cell : row) {
                cell.setAlive(random_.nextBoolean());
            }
        }
        return cells;
    }

    /**
     * This method clears the grid
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
        return cells;
    }

    /**
     * This method sets the rule string
     * @param ruleString The rule to set, it must be of the form "Bx/Sy" where "x" and "y" are a series of 0-8 digits in the range [1,8]
     */
    @Override
    public void setRule(String ruleString) {
        String[] rules = ruleString.split("/");
        bornOn_ = rules[0].substring(1);
        surviveOn_ = rules[1].substring(1);
    }
}