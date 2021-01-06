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

    BufferedImage brick, box, boom, bomb1, bomb2;
    BufferedImage manUp1, manUp2, manDown1, manDown2, manRight1, manRight2, manLeft1, manLeft2, manDead, buffer;
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
            manUp1 = ImageIO.read(new File("manUp1.png"));
            manUp2 = ImageIO.read(new File("manUp2.png"));
            manDown1 = ImageIO.read(new File("manDown1.png"));
            manDown2 = ImageIO.read(new File("manDown2.png"));
            manLeft1 = ImageIO.read(new File("manLeft1.png"));
            manLeft2 = ImageIO.read(new File("manLeft2.png"));
            manRight1 = ImageIO.read(new File("manRight1.png"));
            manRight2 = ImageIO.read(new File("manRight2.png"));
            manDead = ImageIO.read(new File("manDead.png"));
            bonus1 = ImageIO.read(new File("bonus1.png"));
            bonus2 = ImageIO.read(new File("bonus2.png"));
            bonus3 = ImageIO.read(new File("bonus3.png"));
            bonus4 = ImageIO.read(new File("bonus4.png"));



        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    BufferedImage manImage(Bomberman bomberman) {
        buffer = manDown1;
        if (bomberman.alive) {
            if (animCounter == 1) {
                if (bomberman.mUp) {
                    buffer = manUp1;
                }
                if (bomberman.mDown) {
                    buffer = manDown1;
                }
                if (bomberman.mRight) {
                    buffer = manRight1;
                }
                if (bomberman.mLeft) {
                    buffer = manLeft1;
                }
            } else {
                if (bomberman.mUp) {
                    buffer = manUp2;
                }
                if (bomberman.mDown) {
                    buffer = manDown2;
                }
                if (bomberman.mRight) {
                    buffer = manRight2;
                }
                if (bomberman.mLeft) {
                    buffer = manLeft2;
                }
            }
        } else {
            buffer = manDead;
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

