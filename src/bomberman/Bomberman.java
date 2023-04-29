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

    int gravity = 1;
    int jump_speed = 13;
    boolean gravity_smoother;
    boolean jumping = false;
    int r = 17;
    int x;
    int y;
    int fieldCurrentX;
    int fieldCurrentY;
    boolean alive = true;
    int bombsAllowed = 1;
    int bombsPlanted = 0;
    int xSpeed = 2;
    int ySpeed = 0;
    boolean canPush;
    int power;
    boolean mRight;
    boolean mLeft;
    boolean mUp;
    boolean mDown;
    boolean putBomb;
    String playerType;

    Bomberman(int a, int b, String pType) {
        x = a;
        y = b;
        playerType = pType;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        this.mRight=false;
        this.mLeft=false;
        this.mUp=false;
        this.mDown=false;
    }

    public void defineHimself(GameField field) {
        int fieldX = ((this.x) % 800) / 40;
        int fieldY = ((this.y) % 600) / 40;
        if (((fieldX != fieldCurrentX) || (fieldY != fieldCurrentY)) && field.field[fieldX][fieldY] == 0) {
            field.field[fieldCurrentX][fieldCurrentY] = 0;
            field.field[fieldX][fieldY] = -1;
            fieldCurrentX = fieldX;
            fieldCurrentY = fieldY;
        }
        if (!alive) {
            field.field[fieldCurrentX][fieldCurrentY] = 0;
            field.field[fieldX][fieldY] = 0;
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

        if (alive) {

            gravity_smoother = !gravity_smoother;

            if (mUp && !jumping) {
//
                jumping = true;

                if ((gameField.checkSection(x, y - ySpeed - r) == 0) || moveInOwnArea(x, y - ySpeed - r)) {
                    ySpeed = -jump_speed;
                }
            }


            // gameField.checkSection(x + xSpeed + r, y) == 4) ||

            // move horizontally
            if (mRight) {
                if (gameField.checkSection(x + xSpeed + r, y) == 0 || moveInOwnArea(x + xSpeed + r, y)) {
                    x += xSpeed;
                }
//                if (gameField.checkSection(x + xSpeed + r, y) == -2 || moveInOwnArea(x + xSpeed + r, y)) {
//                    x += 1;
//                }
            }
            if (mLeft) {
                if (gameField.checkSection(x - xSpeed - r, y) == 0 || moveInOwnArea(x - xSpeed - r, y)) {
                    x -= xSpeed;
                }
//                if (gameField.checkSection(x - xSpeed - r, y) == -2 || moveInOwnArea(x - xSpeed - r, y)) {
//                    x -= 1;
//                }
            }


            // process jump and landing, gravity


            if (gameField.checkSection(x, y + ySpeed + r) == 0 || moveInOwnArea(x, y + ySpeed + r)) {
                ySpeed += gravity * ((gravity_smoother)?1:0);

            } else {
                ySpeed = 0;
                if (gravity_smoother) jumping = false;
            }

            y += ySpeed;


        } else {
            if (gameField.checkSection(x, y + 3 + r) == 0 || moveInOwnArea(x, y + 3 + r)) {
                y += 3 + gravity;
            } else {
                ySpeed = 0;
            }
        }

        defineHimself(gameField);
    }
}
