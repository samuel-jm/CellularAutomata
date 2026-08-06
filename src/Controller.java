/**
 * This class defines an interface with a method for each action in the control panel.
 * ControlPanel has a reference to a <c>Controller</c> and the main application implements it
 */
public interface Controller {
    void start();
    void step();
    void stop();
    void random();
    void reverse();
    void clear();
    void setRule(String ruleString);
    void setWrap(boolean selected);
    void setElementary(boolean selected);
}
