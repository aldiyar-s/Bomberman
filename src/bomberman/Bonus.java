/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

/**
 *
 * @author Aldiyar
 */
public class Bonus {

    int x;
    int y;
    int type;
    boolean isNotTaken;

    Bonus(GameField gameField, int xx, int yy) {
        x = xx;
        y = yy;
        type = (int)(4-Math.random()*3);
        isNotTaken = true;
        gameField.bonusAdd(this);
    }

    void upgrade(Bomberman bomberman) {
        if (type == 1) {
            bomberman.xSpeed++;
        }
        if (type == 2) {
            bomberman.bombsAllowed++;
        }
        if (type == 3) {
            bomberman.power++;
        }
    }
}
