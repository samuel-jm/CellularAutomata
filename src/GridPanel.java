import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GridPanel extends JPanel implements MouseListener {
    private Cell[][] cells_;
    private int cellSize_;

    public GridPanel(int panelSize, int cellSize) {
        setPreferredSize(new Dimension(panelSize, panelSize));
        setMinimumSize(new Dimension(100, panelSize));
        setBackground(Color.white);

        addMouseListener(this);

        cells_ = new Cell[panelSize / cellSize][panelSize / cellSize];
        cellSize_ = cellSize;
        setArrayState(cells_, false);
    }

    private void setArrayState(Cell[][] cells, boolean alive) {
        for(int row = 0; row < cells.length; row++) {
            for(int col = 0; col < cells.length; col++) {
                cells[row][col] = new Cell(alive);
                cells[row][col].setAlive(alive);
            }
        }
    }

    public void render(Cell[][] cells) {
        cells_ = cells;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.black);
        for(int row = 0; row < cells_.length; row++) {
            for(int col = 0; col < cells_[row].length; col++) {
                if(cells_[row][col].getAlive()) {
                    g.setColor(Color.black);
                    g.fillRect(col * cellSize_, row * cellSize_, cellSize_, cellSize_);
                };
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
//        life.setState(GameOfLife.STATE.Stop);
//
//        int mx = e.getX();
//        int my = e.getY();
//
//        if(cells[my / size][mx / size].getState() == 1) cells[my / size][mx / size].setState(0);
//        else {
//            cells[my / size][mx / size].setState(1);
//            cells[my / size][mx / size].setVisited(true);
//        }
    }

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
}
