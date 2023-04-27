/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import bomberman.GameHelper;
import bomberman.Bomberman;
import bomberman.GameField;
import bomberman.GamePanel;
import bomberman.SoundHandler;

/**
 *
 * @author Aldiyar
 */
public class ImageHandler {

    BufferedImage brick, box, boom, bomb1, bomb2, buffer;
    BufferedImage[] man = new BufferedImage[9];
    BufferedImage[] fireboy = new BufferedImage[9];
    BufferedImage[] watergirl= new BufferedImage[9];
    BufferedImage waterBoom, water, lava;
    BufferedImage bonus1, bonus2, bonus3, bonus4;
    int animCounter = 1;
    int animTimer = 0;
    final int animTimerDelta = 90;
    BufferedImage level;

    void updateAnimCounter(GameField gameField) {
        animTimer += 10;
        if (animTimer == animTimerDelta) {
            animCounter = -animCounter;
            animTimer = 0;
            redrawLevel(gameField);
        }
    }

    public void load() {
        try {
            brick = ImageIO.read(new File("brick.png"));
            box = ImageIO.read(new File("box.png"));
            boom = ImageIO.read(new File("fire.png"));
            bomb1 = ImageIO.read(new File("bomb1.png"));
            bomb2 = ImageIO.read(new File("bomb2.png"));

            man[0] = ImageIO.read(new File("manDead.png"));
            man[1] = ImageIO.read(new File("manUp1.png"));
            man[2] = ImageIO.read(new File("manUp2.png"));
            man[3] = ImageIO.read(new File("manDown1.png"));
            man[4] = ImageIO.read(new File("manDown2.png"));
            man[5] = ImageIO.read(new File("manLeft1.png"));
            man[6] = ImageIO.read(new File("manLeft2.png"));
            man[7] = ImageIO.read(new File("manRight1.png"));
            man[8] = ImageIO.read(new File("manRight2.png"));

            bonus1 = ImageIO.read(new File("bonus1.png"));
            bonus2 = ImageIO.read(new File("bonus2.png"));
            bonus3 = ImageIO.read(new File("bonus3.png"));
            bonus4 = ImageIO.read(new File("bonus4.png"));

            fireboy[0] = ImageIO.read(new File("fireboyDead.png"));
            fireboy[1] = ImageIO.read(new File("fireboyUp1.png"));
            fireboy[2] = ImageIO.read(new File("fireboyUp2.png"));
            fireboy[3] = ImageIO.read(new File("fireboyDown1.png"));
            fireboy[4] = ImageIO.read(new File("fireboyDown2.png"));
            fireboy[5] = ImageIO.read(new File("fireboyLeft1.png"));
            fireboy[6] = ImageIO.read(new File("fireboyLeft2.png"));
            fireboy[7] = ImageIO.read(new File("fireboyRight1.png"));
            fireboy[8] = ImageIO.read(new File("fireboyRight2.png"));


            watergirl[0] = ImageIO.read(new File("watergirlDead.png"));
            watergirl[1] = ImageIO.read(new File("watergirlUp1.png"));
            watergirl[2] = ImageIO.read(new File("watergirlUp2.png"));
            watergirl[3] = ImageIO.read(new File("watergirlDown1.png"));
            watergirl[4] = ImageIO.read(new File("watergirlDown2.png"));
            watergirl[5] = ImageIO.read(new File("watergirlLeft1.png"));
            watergirl[6] = ImageIO.read(new File("watergirlLeft2.png"));
            watergirl[7] = ImageIO.read(new File("watergirlRight1.png"));
            watergirl[8] = ImageIO.read(new File("watergirlRight2.png"));


            waterBoom = ImageIO.read(new File("waterBoom.png"));
            water = ImageIO.read(new File("water.png"));
            lava = ImageIO.read(new File("lava.png"));

        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    BufferedImage manImage(Bomberman bomberman) {
        BufferedImage[] skin;

        if (bomberman.playerType=="fireboy"){
            skin = fireboy;
        } else if (bomberman.playerType=="watergirl"){
            skin = watergirl;
        } else {
            skin = man;
        }
        buffer = skin[3];

        if (bomberman.alive) {
            if (animCounter == 1) {
                if (bomberman.mUp) {
                    buffer = skin[1];
                }
                if (bomberman.mDown) {
                    buffer = skin[3];
                }
                if (bomberman.mLeft) {
                    buffer = skin[5];
                }
                if (bomberman.mRight) {
                    buffer = skin[7];
                }
            } else {
                if (bomberman.mUp) {
                    buffer = skin[2];
                }
                if (bomberman.mDown) {
                    buffer = skin[4];
                }
                if (bomberman.mLeft) {
                    buffer = skin[6];
                }
                if (bomberman.mRight) {
                    buffer = skin[8];
                }
            }
        } else {
            buffer = skin[0];
        }
        return buffer;
    }

    BufferedImage redrawLevel(GameField gameField) {

        level = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        Graphics2D gg = level.createGraphics();
        for (int j = 0; j < 15; j++) {
            for (int i = 0; i < 20; i++) {
                if (gameField.field[i][j] == 1) {
                    gg.drawImage(brick, i * 40, j * 40, null);
                }
                if (gameField.field[i][j] == 2) {
                    gg.drawImage(box, i * 40, j * 40, null);
                }
                if (gameField.field[i][j] == 3) {
                    if (animCounter == 1) {
                        gg.drawImage(bomb1, i * 40, j * 40, null);
                    } else {
                        gg.drawImage(bomb2, i * 40, j * 40, null);
                    }
                }
                if (gameField.field[i][j] == 4) {
                    gg.drawImage(boom, i * 40, j * 40, null);
                }
                if (gameField.bonusField[i][j] != null) {
                    gg.drawImage(bonusImage(gameField.bonusField[i][j]), i * 40, j * 40, null);
                }
            }
        }
        gg.setColor(Color.WHITE);
        gg.drawImage(bonus4, 0, 559, null);
        gg.drawImage(bonus4, 760, 559, null);
        gg.drawImage(bonus1, 40, 560, null);
        gg.drawImage(bonus1, 720, 560, null);
        gg.drawImage(bonus3, 80, 560, null);
        gg.drawImage(bonus3, 680, 560, null);

        return level;
    }

    BufferedImage bonusImage(bomberman.Bonus bonus) {
        if (bonus.type == 1) {
            return bonus1;
        } else if (bonus.type == 2) {
            return bonus4;
        } else {
            return bonus3;
        }
    }
}

