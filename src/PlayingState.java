/**
 * This class is a concrete implementation of <c>State</c> and defines the simulation's
 * ticking behaviour when the user has pressed "Start"
 */
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
