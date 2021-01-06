/*
 * GamePanel.java
 *
 * Created on 11 Апрель 2008 г., 9:09
 */
package bomberman;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.*;
import javax.swing.Timer;
import bomberman.GameHelper;
import bomberman.Bomberman;
import bomberman.GameField;
import bomberman.ImageHandler;
import bomberman.SoundHandler;



/**
 *
 * @author  Aldiyar
 */
public class GamePanel extends javax.swing.JPanel {
    //// creating game objects
    GameHelper gameHelper = new GameHelper();
    Bomberman player1 = new Bomberman(220, 300);
    Bomberman player2 = new Bomberman(540, 300);
    GameField gameField = new GameField();
    ImageHandler imageHandler = new ImageHandler();
    SoundHandler soundHandler = new SoundHandler();
    

    /** Creates new form GamePanel */
    public GamePanel(String fname) {
        initComponents();
        imageHandler.load();
        soundHandler.load();
        gameHelper.restartGame(gameField, player1, player2, fname);
        Timer timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameHelper.updateBombs(gameField, player1, player2, soundHandler);
                gameField.detectCollision(gameHelper, player1, player2, soundHandler);
                gameField.bonusUpgrPlr(player1, player2);
                player1.move(gameField);
                player2.move(gameField);
                imageHandler.updateAnimCounter(gameField);
                repaint();
            }
        });
        timer.setRepeats(true);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform t = g2d.getTransform();
        double w = getWidth();
        double h = getHeight();
        g2d.scale(w / 800, h / 600);
        g2d.drawImage(imageHandler.level, 0, 0, null);
        g2d.drawImage(imageHandler.manImage(player1), player1.x - player1.r - 3, player1.y - player1.r - 3, null);
        g2d.drawImage(imageHandler.manImage(player2), player2.x - player2.r - 3, player2.y - player2.r - 3, null);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("SansSerif", Font.BOLD, 18));
        g2d.drawString("" + (player1.power), 97, 585);
        g2d.drawString("" + (player2.power), 697, 585);
        g2d.drawString("" + player1.speed, 57, 585);
        g2d.drawString("" + player2.speed, 737, 585);
        g2d.drawString("" + player1.bombsAllowed, 17, 585);
        g2d.drawString("" + player2.bombsAllowed, 777, 585);
        g2d.setTransform(t);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(0, 0, 0));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_W) {
            player1.mUp = true;
        } else if (key == KeyEvent.VK_S) {
            player1.mDown = true;
        } else if (key == KeyEvent.VK_A) {
            player1.mLeft = true;
        } else if (key == KeyEvent.VK_D) {
            player1.mRight = true;
        } else if (key == KeyEvent.VK_1) {
            player1.putBomb = true;
        } else if (key == KeyEvent.VK_UP) {
            player2.mUp = true;
        } else if (key == KeyEvent.VK_DOWN) {
            player2.mDown = true;
        } else if (key == KeyEvent.VK_LEFT) {
            player2.mLeft = true;
        } else if (key == KeyEvent.VK_RIGHT) {
            player2.mRight = true;
        } else if (key == KeyEvent.VK_CONTROL) {
            player2.putBomb = true;
        }
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_W) {
            player1.mUp = false;
        } else if (key == KeyEvent.VK_S) {
            player1.mDown = false;
        } else if (key == KeyEvent.VK_A) {
            player1.mLeft = false;
        } else if (key == KeyEvent.VK_D) {
            player1.mRight = false;
        } else if (key == KeyEvent.VK_1) {
            player1.putBomb = false;
        } else if (key == KeyEvent.VK_UP) {
            player2.mUp = false;
        } else if (key == KeyEvent.VK_DOWN) {
            player2.mDown = false;
        } else if (key == KeyEvent.VK_LEFT) {
            player2.mLeft = false;
        } else if (key == KeyEvent.VK_RIGHT) {
            player2.mRight = false;
        } else if (key == KeyEvent.VK_CONTROL) {
            player2.putBomb = false;
        }
    }//GEN-LAST:event_formKeyReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}








