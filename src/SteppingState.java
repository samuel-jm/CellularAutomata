public class SteppingState extends State {
    public SteppingState(CellularAutomata application, Cell[][] cells, Logic logic) {
        super(application, cells, logic);
    }

    @Override
    public Cell[][] tick() {
        cells = logic.tick(cells);
        application.setState(new StoppedState(application, cells, logic));
        return cells;
    }
}
