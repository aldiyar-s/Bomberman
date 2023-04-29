package bomberman;

public class Switch {
    int x;
    int y;
    int xField;
    int yField;
    boolean open;

    Switch (int newX, int newY) {
        x = newX;
        y = newY;
        xField = (newX % 800) / 40;
        yField = (newY % 800) / 40;
        open = false;
    }
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setOpen() {
        this.open = true;
    }
    public void setClose() {
        this.open = false;
    }
}
