/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import javax.swing.JFrame;
import bomberman.GamePanel;
import javafx.scene.paint.Color;


/**
 *
 * @author Aldiyar
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800, 600);
        GamePanel gamePanel = new GamePanel("level1.txt");
        gamePanel.setVisible(true);
        f.add(gamePanel);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        gamePanel.requestFocusInWindow();

    }
}