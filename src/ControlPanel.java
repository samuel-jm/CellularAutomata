import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class ControlPanel extends JPanel implements ActionListener {
    private final Controller controller_;
    private Pattern regularPattern = Pattern.compile("B[0-9]{0,8}[/]S[0-9]{0,8}");
    private Pattern elementaryPattern = Pattern.compile("(25[0-5])|(2[0-4][0-9])|(1?[0-9]{1,2})");

    private GridBagConstraints constraints;
    private GridBagLayout layout;

    private JCheckBox wrap = new JCheckBox("Wrap");
    private JCheckBox elementary = new JCheckBox("Elementary");

    private JButton start = new JButton("Start");
    private JButton stop = new JButton("Stop");
    private JButton step = new JButton("Step");
    private JButton random = new JButton("Random");
    private JButton clear = new JButton("Clear");
    private JButton reverse = new JButton("Reverse");
    private JButton enter = new JButton("Enter");
    private JButton exit = new JButton("Exit");

    private JLabel label1 = new JLabel("Generation:");
    private JLabel label2 = new JLabel("Options:");
    private JLabel rule = new JLabel("Rule: B3/S23");

    private JTextField string = new JTextField(15);

    private JButton[] buttons = {start, stop, step, random, clear, reverse, enter, exit};
    private JToggleButton[] toggles = {wrap, elementary};

    public ControlPanel(int width, int height, Controller controller) {
        controller_ = controller;

        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setBackground(Color.yellow);

        constraints = new GridBagConstraints();
        layout = new GridBagLayout();

        setLayout(layout);

        for(int i = 0; i < buttons.length; i++) {
            buttons[i].addActionListener(this);
            buttons[i].setBackground(Color.yellow);
        }
        for(int i = 0; i < toggles.length; i++) {
            toggles[i].addActionListener(this);
            toggles[i].setBackground(Color.yellow);
        }

        //Generation
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.weightx = 1;
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(label1, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(start, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        add(step, constraints);

        constraints.gridx = 2;
        constraints.gridy = 1;
        add(stop, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(random, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        add(reverse, constraints);

        constraints.weighty = 1;
        constraints.gridx = 2;
        constraints.gridy = 2;
        add(clear, constraints);


        //Options
        constraints.fill = GridBagConstraints.NONE;
        constraints.weighty = 0;
        constraints.gridx = 1;
        constraints.gridy = 3;
        add(label2, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 3;
        constraints.ipady = 6;
        constraints.gridx = 0;
        constraints.gridy = 4;
        add(string, constraints);

        constraints.gridwidth = -3;
        constraints.ipady = 0;
        constraints.gridx = 0;
        constraints.gridy = 5;
        add(enter, constraints);

        constraints.fill = GridBagConstraints.NONE;
        constraints.gridwidth = 2;
        constraints.ipady = 10;
        constraints.gridx = 1;
        constraints.gridy = 5;
        add(rule, constraints);

        constraints.fill = GridBagConstraints.NONE;
        constraints.gridwidth = -2;
        constraints.ipady = 0;
        constraints.gridx = 0;
        constraints.gridy = 6;
        wrap.setSelected(true);
        add(wrap, constraints);

        constraints.fill = GridBagConstraints.NONE;
        constraints.weighty = 10;
        constraints.gridwidth = -2;
        constraints.gridx = 1;
        constraints.gridy = 6;
        add(elementary, constraints);


        //Exit
        constraints.anchor = GridBagConstraints.PAGE_END;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 7;
        add(exit, constraints);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == start) {
            controller_.start();
        } else if(e.getSource() == step) {
            controller_.step();
        } else if(e.getSource() == stop) {
            controller_.stop();
        } else if(e.getSource() == random) {
            controller_.random();
        } else if(e.getSource() == reverse) {
            controller_.reverse();
        } else if(e.getSource() == clear) {
            controller_.clear();
        } else if(e.getSource() == enter) {
            String ruleString = string.getText().toUpperCase();
            if(elementary.isSelected() && elementaryPattern.matcher(ruleString).matches() ||
                !elementary.isSelected() && regularPattern.matcher(ruleString).matches()) {
                controller_.setRule(ruleString);
                rule.setText("Rule: " + ruleString);
            }
        } else if(e.getSource() == wrap) {
            controller_.setWrap(wrap.isSelected());
        } else if(e.getSource() == elementary) {
            controller_.setElementary(elementary.isSelected());
            start.setEnabled(!elementary.isSelected());
            rule.setText("Rule: " + (elementary.isSelected() ? "1" : "B3/S23"));
        } else if(e.getSource() == exit) {
            System.exit(0);
        }
    }
}
