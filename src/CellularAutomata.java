import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CellularAutomata extends JPanel implements Controller {
    private State state_;
    private Logic logic_;
    private Cell[][] cells_;
    private final Window _window;

    public CellularAutomata(int width, int height, int cellSize) {
        logic_ = new RegularLogic("B3/S23");
        cells_ = new Cell[height / cellSize][height / cellSize];
        Utilities.initCells(cells_, false);

        state_ = new StoppedState(this, cells_, logic_);
        _window = new Window(width, height, cellSize, "Cellular Automata", this);

        Timer timer = new Timer(80, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cells_ = state_.tick();
                _window.render(cells_);
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CellularAutomata(1000, 750, 5);
            }
        });
    }

    public void setState(State state) {
        state_ = state;
    }

    @Override
    public void start() {
        setState(new PlayingState(this, cells_, logic_));
    }

    @Override
    public void step() {
        setState(new SteppingState(this, cells_, logic_));
    }

    @Override
    public void stop() {
        setState(new StoppedState(this, cells_, logic_));
    }

    @Override
    public void random() {
        cells_ = logic_.random(cells_);
        setState(new StoppedState(this, cells_, logic_));
    }

    @Override
    public void reverse() {
        cells_ = logic_.reverse(cells_);
    }

    @Override
    public void clear() {
        cells_ = logic_.clear(cells_);
    }

    @Override
    public void setRule(String ruleString) {
        logic_.setRule(ruleString);
    }

    @Override
    public void setWrap(boolean wrap) {
        logic_.setWrap(wrap);
    }

    @Override
    public void setElementary(boolean elementary) {
        logic_ = elementary ? new ElementaryLogic("00000001") : new RegularLogic("B3/S23");
        setState(new StoppedState(this, cells_, logic_));
        clear();
    }
}
