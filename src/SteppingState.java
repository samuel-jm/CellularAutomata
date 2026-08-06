/**
 * This class is a concrete implementation of <c>State</c> and defines the simulation's
 * ticking behaviour when the user has pressed "Step"
 */
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
