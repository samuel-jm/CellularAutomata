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
