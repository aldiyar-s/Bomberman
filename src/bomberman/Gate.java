package bomberman;

public class Gate {
    int x;
    int y;
    boolean open;

    Gate (int x, int y) {
        this.x = x;
        this.y = y;
        this.open=false;
    }

    public void setOpen() {
        this.open = true;
    }
    public void setClose() {
        this.open = false;
    }
}
