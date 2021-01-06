/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Rectangle;
import java.io.File;
import java.util.Scanner;
import bomberman.Bonus;
import bomberman.GameHelper;
import bomberman.Bomberman;
import bomberman.ImageHandler;
import bomberman.SoundHandler;
import bomberman.Bomb;
/**
 *
 * @author Aldiyar
 */
class GameField {

    int[][] field = new int[20][15];
    Bonus[][] bonusField = new Bonus[20][15];
    Rectangle fire = new Rectangle();
    Rectangle target = new Rectangle();

    public void detectCollision(GameHelper gameHelper, Bomberman player1, Bomberman player2, SoundHandler soundHandler) {
        for (int j = 0; j < 15; j++) {
            for (int i = 0; i < 20; i++) {
                if (field[i][j] == 4) {
                    fire.setBounds(i * 40 + 2, j * 40 + 2, 35, 35);
                    target.setBounds(player1.x - player1.r + 2, player1.y - player1.r - 2, 2 * player1.r - 9, 2 * player1.r + 2);
                    if (fire.intersects(target) && player1.alive) {
                        gameHelper.kill(player1);
                        soundHandler.die();
                    }
                    target.setBounds(player2.x - player2.r + 2, player2.y - player2.r - 2, 2 * player2.r - 9, 2 * player2.r + 2);
                    if (fire.intersects(target) && player2.alive) {
                        gameHelper.kill(player2);
                        soundHandler.die();
                    }
                }
            }
        }
    }

    public void loadLevel(String fname, Bomberman p1, Bomberman p2) {
        try {
            Scanner input = new Scanner(new File(fname));
            for (int j = 0; j < 15; j++) {
                for (int i = 0; i < 20; i++) {
                    field[i][j] = input.nextInt();
                    if (field[i][j] == 7) {
                        p1.setPosition(i * 40 + 20, j * 40 + 20);
                    }
                    if (field[i][j] == 8) {
                        p2.setPosition(i * 40 + 20, j * 40 + 20);
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int checkSection(int x, int y) {
        // CELL VALUES:
        // 0 - empty cell
        // 1 - wall
        // 2 - box
        // 3 - planted bomb
        // 4 - explosion wave
        //-1 - bomberman 
        int value = 0;
        if ((x <= 0) || (x >= 800) || (y <= 0) || (y >= 600)) {
            value = 1;
        } else {
            int divX = (x % 800) / 40;
            int divY = (y % 600) / 40;
            if (this.field[divX][divY] == 1) {
                value = 1;
            } else if (this.field[divX][divY] == 2) {
                value = 2;
            } else if (this.field[divX][divY] == 3) {
                value = 3;
            } else if (this.field[divX][divY] == 4) {
                value = 4;
            } else if (this.field[divX][divY] == -1) {
                value = -1;
            }
        }
        return value;
    }

    void bonusAdd(Bonus bonus) {
        bonusField[bonus.x][bonus.y] = bonus;
    }

    void bonusRemove(Bonus bonus) {
        bonusField[bonus.x][bonus.y] = null;
    }

    void bonusDefine(Bomberman bomberman) {
        int xx = (bomberman.x % 800) / 40;
        int yy = (bomberman.y % 600) / 40;
        if (bonusField[xx][yy] != null) {
            if (bonusField[xx][yy].isNotTaken) {
                bonusField[xx][yy].upgrade(bomberman);
                bonusField[xx][yy].isNotTaken = false;
                bonusField[xx][yy] = null;
            }
        }
    }

    void bonusUpgrPlr(Bomberman player1, Bomberman player2) {
        bonusDefine(player1);
        bonusDefine(player2);
    }
}
