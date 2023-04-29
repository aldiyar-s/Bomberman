package bomberman;
import javax.swing.*;
import java.awt.*;

public class PauseDialog extends JDialog {

    GamePanel gamePanel;
    JButton loadButton;
    int lvl;

    public PauseDialog(JFrame parent, boolean modal, GamePanel gamePanel) {
        super(parent, modal);
        this.gamePanel = gamePanel;

        initComponents();
    }

    public void initComponents() {

        //System.out.println("Components refreshed");

        JLabel label = new JLabel("Game menu", SwingConstants.CENTER);
        JButton resumeButton;
        if (gamePanel.gameOver){
            resumeButton = new JButton("Start");
        } else {
            resumeButton = new JButton("Resume");
        }

        resumeButton.addActionListener((e) -> setVisible(false));

        JButton saveButton = new JButton("Save game");
        saveButton.addActionListener((e) -> {
            gamePanel.gameField.saveLevel("save.txt", gamePanel.player1,gamePanel.player2,gamePanel.switch1, gamePanel.switch2);
            //System.out.println("Game saved to save.txt!");
            setVisible(false);
        });


        try {
            lvl = gamePanel.gameField.loadLevelCheck("save.txt");
        } catch (Exception e) {
            System.out.println("save.txt does not exist");
        }

        String txt = "No saved games";

        if (lvl>0) {
            loadButton = new JButton("Load saved game (Level "+lvl+")");
            loadButton.setEnabled(true);
        } else {
            loadButton = new JButton(txt);
            loadButton.setEnabled(false);

        }



        loadButton.addActionListener((e) -> {
            gamePanel.gameField.loadLevel("save.txt", gamePanel.player1,gamePanel.player2,gamePanel.switch1, gamePanel.switch2);
            //System.out.println("Game loaded from save.txt!");
            setVisible(false);
        });

        JButton quitButton = new JButton("Quit game");
        quitButton.addActionListener((e) -> System.exit(0));

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));



        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(label);
        panel.add(resumeButton);
        panel.add(saveButton);
        panel.add(loadButton);
        panel.add(quitButton);

        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
    }

    public void setButtonLabel() {
        loadButton.setText("Load Saved Game (Level "+lvl+")");
    }

}