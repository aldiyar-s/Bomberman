/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

/**
 *
 * @author Aldiyar
 */
class Bomberman {

    int r = 17;
    int x;
    int y;
    int fieldCurrentX;
    int fieldCurrentY;
    boolean alive = true;
    int bombsAllowed = 1;
    int bombsPlanted = 0;
    int speed;
    boolean canPush;
    int power;
    boolean mRight;
    boolean mLeft;
    boolean mUp;
    boolean mDown;
    boolean putBomb;

    Bomberman(int a, int b) {
        x = a;
        y = b;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void defineHimself(GameField field) {
        int fieldX = (this.x % 800) / 40;
        int fieldY = (this.y % 600) / 40;
        if (((fieldX != fieldCurrentX) || (fieldY != fieldCurrentY))) {
            field.field[fieldX][fieldY] = -1;
            if (field.field[fieldCurrentX][fieldCurrentY] == -1) {
                field.field[fieldCurrentX][fieldCurrentY] = 0;
            }
            fieldCurrentX = fieldX;
            fieldCurrentY = fieldY;

        }

    }

    public boolean moveInOwnArea(int x, int y) {
        boolean f = false;
        if (x >= 0 && x <= 800 && y >= 0 && y <= 600) {
            x = (x % 800) / 40;
            y = (y % 600) / 40;
            if ((x == fieldCurrentX) && (y == fieldCurrentY)) {
                f = true;
            }
        }
        return f;
    }

    public void move(GameField gameField) {
        defineHimself(gameField);

        if (alive) {
            if (mRight) {
                if ((gameField.checkSection(x + speed + r, y) == 0 || gameField.checkSection(x + speed + r, y) == 4) || moveInOwnArea(x + speed + r, y)) {
                    x += speed;
                }
            }
            if (mLeft) {
                if ((gameField.checkSection(x - speed - r, y) == 0 || gameField.checkSection(x + speed + r, y) == 4) || moveInOwnArea(x - speed - r, y)) {
                    x -= speed;
                }
            }
            if (mUp) {
                if ((gameField.checkSection(x, y - speed - r) == 0 || gameField.checkSection(x + speed + r, y) == 4) || moveInOwnArea(x, y - speed - r)) {
                    y -= speed;
                }
            }
            if (mDown) {
                if ((gameField.checkSection(x, y + speed + r) == 0 || gameField.checkSection(x + speed + r, y) == 4) || moveInOwnArea(x, y + speed + r)) {
                    y += speed;
                }
            }
        }
    }
}
