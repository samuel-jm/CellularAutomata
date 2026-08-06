/**
 * This abstract class is a base class for the State Pattern and is used to determine how each simulation tick
 * is handled under different circumstances (playing, stepping, stopped)
 */
public abstract class State {
    public CellularAutomata application;
    public Cell[][] cells;
    public Logic logic;
    public State(CellularAutomata application, Cell[][] cells, Logic logic) {
        this.application = application;
        this.cells = cells;
        this.logic = logic;
    }

    public abstract Cell[][] tick();
}
