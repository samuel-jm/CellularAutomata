import java.util.Random;

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

    @Override
    public boolean updateCellState(Cell[][] cells, int row, int col, int neighbours) {
        if(cells[row][col].getAlive()) {
            return surviveOn_.contains(String.valueOf(neighbours));
        } else {
            return bornOn_.contains(String.valueOf(neighbours));
        }
    }

    @Override
    public Cell[][] random(Cell[][] cells) {
        for(Cell[] row : cells) {
            for(Cell cell : row) {
                cell.setAlive(random_.nextBoolean());
            }
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
        return cells;
    }

    @Override
    public void setRule(String ruleString) {
        String[] rules = ruleString.split("/");
        bornOn_ = rules[0].substring(1);
        surviveOn_ = rules[1].substring(1);
    }
}
