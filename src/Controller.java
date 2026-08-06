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
