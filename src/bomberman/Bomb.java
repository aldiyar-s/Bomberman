/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

/**
 *
 * @author Aldiyar
 */
class Bomb {

    int xField;
    int yField;
    int power;
    int existTime;
    int disappearTime;
    int detonateTime = 1300;
    int expandSpeed = 100;
    boolean hit1, hit2, hit3, hit4;
    boolean noBonusYet = true;
    int random;
    Bomberman whoPlanted;

    Bomb(Bomberman bomberman) {
        existTime = 0;
        power = bomberman.power;
        xField = (bomberman.x % 800) / 40;
        yField = (bomberman.y % 800) / 40;
        disappearTime = detonateTime + power * expandSpeed;
        hit1 = true;
        hit2 = true;
        hit3 = true;
        hit4 = true;
        whoPlanted = bomberman;
        random = (int) (Math.random() * 100);
    }

    void tryBonus(GameField gameField, int xx, int yy) {
        if (noBonusYet) {
            if (random % 7 == 0 || random % 13 == 0 || random % 11 == 0 || random % 19 == 0 || random % 17 == 0 || random % 12 == 0 || random % 23 ==  0 || random % 27 == 0) {
                Bonus bonus = new Bonus(gameField, xx, yy);
                noBonusYet = false;
            }
        }
    }

    public void updateExistTime(GameField f, SoundHandler soundHandler) {
        existTime += 10;
        if (existTime < detonateTime) {
            f.field[xField][yField] = 3;
        }
        for (int i = 0; i < power; i++) {
            if (existTime == detonateTime + expandSpeed * i) {
                f.field[xField][yField] = 4;

                soundHandler.explode();
                if (xField + i < 20) {
                    if ((f.field[xField + i][yField] == 0 || f.field[xField + i][yField] == -1) && hit1) {
                        f.field[xField + i][yField] = 4;
                        soundHandler.explode();
                    }
                    if (f.field[xField + i][yField] == 2 && hit1) {
                        f.field[xField + i][yField] = 4;
                        tryBonus(f, xField + i, yField);
                        soundHandler.explode();
                        hit1 = false;
                    }
                    if (f.field[xField + i][yField] == 1) {
                        hit1 = false;
                    }
                }
                if (xField - i >= 0) {
                    if ((f.field[xField - i][yField] == 0 || f.field[xField - i][yField] == -1) && hit2) {
                        f.field[xField - i][yField] = 4;
                        soundHandler.explode();
                    }
                    if (f.field[xField - i][yField] == 2 && hit2) {
                        f.field[xField - i][yField] = 4;
                        tryBonus(f, xField - i, yField);
                        soundHandler.explode();
                        hit2 = false;
                    }
                    if (f.field[xField - i][yField] == 1) {
                        hit2 = false;
                    }
                }
                if (yField + i < 15) {
                    if ((f.field[xField][yField + i] == 0 || f.field[xField][yField + i] == -1) && hit3) {
                        f.field[xField][yField + i] = 4;
                        soundHandler.explode();
                    }
                    if (f.field[xField][yField + i] == 2 && hit3) {
                        f.field[xField][yField + i] = 4;
                        tryBonus(f, xField, yField + i);
                        soundHandler.explode();
                        hit3 = false;
                    }
                    if (f.field[xField][yField + i] == 1) {
                        hit3 = false;
                    }
                }
                if (yField - i >= 0) {
                    if ((f.field[xField][yField - i] == 0 || f.field[xField][yField - i] == -1) && hit4) {
                        f.field[xField][yField - i] = 4;
                        soundHandler.explode();
                    }
                    if (f.field[xField][yField - i] == 2 && hit4) {
                        f.field[xField][yField - i] = 4;
                        tryBonus(f, xField, yField - i);
                        soundHandler.explode();
                        hit4 = false;
                    }
                    if (f.field[xField][yField - i] == 1) {
                        hit4 = false;
                    }
                }
            }
        }

        if (existTime >= disappearTime) {
            f.field[xField][yField] = 0;
            for (int i = 0; i < power; i++) {
                if (xField + i < 20) {
                    if (f.field[xField + i][yField] == 4) {
                        f.field[xField + i][yField] = 0;
                    }
                }
                if (xField - i >= 0) {
                    if (f.field[xField - i][yField] == 4) {
                        f.field[xField - i][yField] = 0;
                    }
                }
                if (yField + i < 15) {
                    if (f.field[xField][yField + i] == 4) {
                        f.field[xField][yField + i] = 0;
                    }
                }
                if (yField - i >= 0) {
                    if (f.field[xField][yField - i] == 4) {
                        f.field[xField][yField - i] = 0;
                    }
                }
            }
        }
    }
}
