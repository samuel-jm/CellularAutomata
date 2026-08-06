/**
 * This class is a concrete implementation of <c>State</c> and defines the simulation's
 *  ticking behaviour when the user has pressed "Stop"
 */
public class StoppedState extends State {
    public StoppedState(CellularAutomata application, Cell[][] cells, Logic logic) {
        super(application, cells, logic);
    }

    @Override
    public Cell[][] tick() {
        return cells;
    }
}

