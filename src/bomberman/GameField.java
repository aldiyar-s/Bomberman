/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Rectangle;
import java.io.File;
import java.io.PrintWriter;
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
    int[][] fieldGate = new int[20][15];
    int level;
    final int LEVELTOTAL = 3;
    boolean gameWon=false;

    Bonus[][] bonusField = new Bonus[20][15];
    Rectangle fire = new Rectangle();
    Rectangle target = new Rectangle();

    Switch tempSwitch1, tempSwitch2;

    public void detectCollision(GameHelper gameHelper, Bomberman player1, Bomberman player2, SoundHandler soundHandler) {
        for (int j = 0; j < 15; j++) {
            for (int i = 0; i < 20; i++) {

                boolean check = false;
                boolean check1 = false;
                boolean check2 = false;

                boolean checkB = false;
                boolean checkB1 = false;
                boolean checkB2 = false;

                boolean winlvl = false;
                boolean winlvl1 = false;
                boolean winlvl2 = false;

                if (field[i][j] == 4) {
                    fire.setBounds(i * 40, j * 40, 40, 40);
                    target.setBounds(player1.x - player1.r, player1.y - 20, 2 * player1.r-4, 43);
                    if (fire.intersects(target) && player1.alive) {
                        gameHelper.kill(player1);
                        soundHandler.die();
                    }

                    target.setBounds(player2.x - player2.r, player2.y - 20, 2 * player2.r-6, 43);
                    if (fire.intersects(target) && player2.alive) {
                        gameHelper.kill(player2);
                        soundHandler.die();
                    }
                }
                if (field[i][j] == 5) {
                    fire.setBounds(i * 40, j * 40, 40, 40);
                    target.setBounds(player2.x - player2.r, player2.y - 20, 2 * player2.r-6, 43);
                    if (fire.intersects(target) && player2.alive) {
                        gameHelper.kill(player2);
                        soundHandler.die();
                    }
                }
                if (field[i][j] == 6) {
                    fire.setBounds(i * 40, j * 40, 40, 40);
                    target.setBounds(player1.x - player1.r, player1.y - 20, 2 * player1.r-6, 43);
                    if (fire.intersects(target) && player1.alive) {
                        gameHelper.kill(player1);
                        soundHandler.die();
                    }
                }
                if (field[i][j] == -2) {

                    fire.setBounds(i * 40, j * 40, 40, 40);
                    target.setBounds(player1.x - 20, player1.y - 20, 43, 43);
                    if (fire.intersects(target) && player1.alive) {
                        check1 = true;
                    }
                    target.setBounds(player2.x - 20, player2.y - 20, 43, 43);
                    if (fire.intersects(target) && player2.alive) {
                        check2 = true;
                    }
                    check = (check1 || check2);
                    if (check) {
                        if(!tempSwitch1.open) {
                            soundHandler.plant();
                            tempSwitch1.setOpen();
                            for (int n = 0; n < 15; n++) {
                                for (int m = 0; m < 20; m++) {
                                    if (fieldGate[m][n]==9) field[m][n]=0;
                                }
                            }
                        }
                    } else {
                        if(tempSwitch1.open) {
                            soundHandler.plant();
                            tempSwitch1.setClose();

                            if (!tempSwitch2.open) {
                                for (int n = 0; n < 15; n++) {
                                    for (int m = 0; m < 20; m++) {
                                        if (fieldGate[m][n] == 9) field[m][n] = 9;
                                    }
                                }
                            }

                        }
                    }
                }
                if (field[i][j] == -3) {


                    fire.setBounds(i * 40, j * 40, 40, 40);
                    target.setBounds(player1.x - 20, player1.y - 20, 43, 43);
                    if (fire.intersects(target) && player1.alive) {
                        checkB1 = true;
                    }
                    target.setBounds(player2.x - 20, player2.y - 20, 43, 43);
                    if (fire.intersects(target) && player2.alive) {
                        checkB2 = true;
                    }
                    checkB = (checkB1 || checkB2);
                    if (checkB) {
                        if(!tempSwitch2.open) {
                            soundHandler.plant();
                            tempSwitch2.setOpen();
                            for (int n = 0; n < 15; n++) {
                                for (int m = 0; m < 20; m++) {
                                    if (fieldGate[m][n]==9) field[m][n]=0;
                                }
                            }
                        }
                    } else {
                        if(tempSwitch2.open) {
                            soundHandler.plant();
                            tempSwitch2.setClose();

                            if (!tempSwitch1.open) {
                                for (int n = 0; n < 15; n++) {
                                    for (int m = 0; m < 20; m++) {
                                        if (fieldGate[m][n] == 9) field[m][n] = 9;
                                    }
                                }
                            }
                        }
                    }
                }
                if (field[i][j] == -9) {

                    fire.setBounds(i * 40, j * 40, 40, 40);
                    target.setBounds(player1.x - 20, player1.y - 20, 43, 43);
                    if (fire.intersects(target) && player1.alive) {
                        winlvl1 = true;
                    }
                    target.setBounds(player2.x - 20, player2.y - 20, 43, 43);
                    if (fire.intersects(target) && player2.alive) {
                        winlvl2 = true;
                    }
                    winlvl = (winlvl1 && winlvl2);
                    if (winlvl) {
                        soundHandler.win();
                        int lvltemp = level;
                        if (lvltemp<3) {
                            lvltemp++;
                            gameWon = false;
                        }
                        else {
                            lvltemp=1;
                            gameWon = true;
                        }
                        try {
                            loadLevel("level"+lvltemp+".txt", player1,player2,tempSwitch1,tempSwitch2);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }
    }

    public void loadLevel(String fname, Bomberman p1, Bomberman p2, Switch s1, Switch s2) {
        try {
            Scanner input = new Scanner(new File(fname));
            level = input.nextInt();
            for (int j = 0; j < 15; j++) {
                for (int i = 0; i < 20; i++) {
                    field[i][j] = input.nextInt();
                    fieldGate[i][j] = field[i][j];
                    if (field[i][j] == 7) {
                        p1.setPosition(i * 40 + 20, j * 40 + 20);
                        p1.alive=true;
                        field[i][j] = 0;
                    }
                    if (field[i][j] == 8) {
                        p2.setPosition(i * 40 + 20, j * 40 + 20);
                        p2.alive=true;
                        field[i][j] = 0;
                    }
                    if (field[i][j] == -2) {
                        s1.setPosition(i * 40 + 20, j * 40 + 20);
                        tempSwitch1=s1;
                    }
                    if (field[i][j] == -3) {
                        s2.setPosition(i * 40 + 20, j * 40 + 20);
                        tempSwitch2=s2;
                    }
                }
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int loadLevelCheck(String fname) {
        int lvl=0;
        try {
            Scanner input = new Scanner(new File(fname));
            lvl = input.nextInt();
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lvl;
    }

    public void saveLevel(String fname, Bomberman p1, Bomberman p2, Switch s1, Switch s2) {
        try {

            PrintWriter output = new PrintWriter(new File(fname));
            output.print(level);
            output.println();
            for (int j = 0; j < 15; j++) {
                for (int i = 0; i < 20; i++) {

                    if (convertCoord(p1.x)==i && convertCoord(p1.y)==j) output.print("7");
                    else if (convertCoord(p2.x)==i && convertCoord(p2.y)==j) output.print("8");
                    else if (convertCoord(s1.x)==i && convertCoord(s1.y)==j) output.print("-2");
                    else if (convertCoord(s2.x)==i && convertCoord(s2.y)==j) output.print("-3");
                    else {
                        if (fieldGate[i][j]==9) { output.print(fieldGate[i][j]);

                        } else output.print(field[i][j]);
                    }

                    output.print(" ");
                }
                output.println();
            }
            output.close();
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
        // 4 - explosion wave, acid
        // 5 - lava
        // 6 - water
        // 7 8 - players
        // 9 - gate
        //
        //-1 - bomberman
        //-2-3 - trigger1-2
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
            } else if (this.field[divX][divY] == 5) {
                value = 5;
            } else if (this.field[divX][divY] == 6) {
                value = 6;
            } else if (this.field[divX][divY] == 9) {
                value = 9;
            } else if (this.field[divX][divY] == -1) {
                value = -1;
            } else if (this.field[divX][divY] == -2) {
                value = -2;
            } else if (this.field[divX][divY] == -3) {
                value = -3;
            } else if (this.field[divX][divY] == -9) {
                value = -9;
            }
        }
        return value;
    }

    public int convertCoord (int x) {
        return (x % 800) / 40;
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
