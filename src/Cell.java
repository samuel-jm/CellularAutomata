/**
 * This class is responsible for individual grid cells and their current state (alive or dead)
 */
public class Cell {
    private boolean alive_;

    public Cell(boolean alive) {
        alive_ = alive;
    }

    public boolean getAlive() {
        return alive_;
    }

    public void setAlive(boolean alive) {
        alive_ = alive;
    }
}
