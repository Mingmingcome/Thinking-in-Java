// 2017-11-13 11:05:04

public class Switch {
    private boolean state = false;
    public boolean read() {
        return state;
    }
    public void on() {
        state = true;
        System.out.println(this);
    }
    public void off() {
        state = false;
        System.out.print(this);
    }
    public String toString() {
        return state ? "on" : "off";
    }
}