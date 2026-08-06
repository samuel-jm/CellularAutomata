public class StoppedState extends State {
    public StoppedState(CellularAutomata application, Cell[][] cells, Logic logic) {
        super(application, cells, logic);
    }

    @Override
    public Cell[][] tick() {
        return cells;
    }
}

