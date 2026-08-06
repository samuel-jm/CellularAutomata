import javax.swing.*;

public class Window extends JFrame {
    private final GridPanel _gridPanel;

    public Window(int width, int height, int cellSize, String title, CellularAutomata application) {
        JFrame frame = new JFrame(title);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

        ControlPanel controlPanel = new ControlPanel(width - height, height, application);
        _gridPanel = new GridPanel(height, cellSize);

        frame.getContentPane().add(controlPanel);
        frame.getContentPane().add(_gridPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    public void render(Cell[][] cells) {
        _gridPanel.render(cells);
    }
}
