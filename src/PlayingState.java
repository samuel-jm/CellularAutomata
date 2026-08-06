public class PlayingState extends State {
    public PlayingState(CellularAutomata application, Cell[][] cells, Logic logic) {
        super(application, cells, logic);
    }

    @Override
    public Cell[][] tick() {
        cells = logic.tick(cells);
        return cells;
    }
}
