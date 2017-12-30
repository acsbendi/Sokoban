import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * The class that shows the various levels which the user can play, and each level can be selected to be played.
 */
public class LevelSelector extends JFrame {
    private Sokoban caller;
    private List<Level> levels;
    private JPanel middlePanel;
    private int currentLevelIndex;

    /**
     * The key-listener class that changes level when either the left or the right arrow button has been pressed.
     * After Enter has been pressed the game play starts on the currently selected level.
     */
    class ControlKeyListener extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getExtendedKeyCode()){
                case KeyEvent.VK_RIGHT:
                    if(currentLevelIndex == levels.size()-1)
                        currentLevelIndex = 0;
                    else
                        ++currentLevelIndex;
                    showCurrentLevel();
                    break;
                case KeyEvent.VK_LEFT:
                    if(currentLevelIndex == 0)
                        currentLevelIndex = levels.size()-1;
                    else
                        --currentLevelIndex;
                    showCurrentLevel();
                    break;
                case KeyEvent.VK_ENTER:
                    new Game(levels.get(currentLevelIndex),currentLevelIndex,caller);
                    setVisible(false);
                    dispose();
                    break;
                case KeyEvent.VK_BACK_SPACE:
                    if(levels.size() != 1){
                        levels.remove(currentLevelIndex);
                        if(currentLevelIndex != 0) currentLevelIndex--;
                        else currentLevelIndex = 0;
                        showCurrentLevel();
                    }
                    break;

            }
        }
    }

    /**
     * Loads the available levels from a file into a list. Uses object deserialization.
     */
    private void initializeLevels(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/levels.txt"));
            levels = (List<Level>)ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the currently selected level on the screen.
     */
    private void showCurrentLevel(){
        if(middlePanel != null) getContentPane().remove(middlePanel);
        middlePanel = new JPanel(new GridLayout(levels.get(currentLevelIndex).getHeight(),levels.get(currentLevelIndex).getWidth()));
        JLabel jLabel;
        for(int i = 0; i < levels.get(currentLevelIndex).getWidth()*levels.get(currentLevelIndex).getHeight(); ++i) {
            jLabel = new JLabel(new ImageIcon(levels.get(currentLevelIndex).getSquare(i % levels.get(currentLevelIndex).getWidth(), i / levels.get(currentLevelIndex).getWidth()).getImage()));
            middlePanel.add(jLabel);
        }
        getContentPane().add(middlePanel,BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    LevelSelector(Sokoban sokoban){
        caller = sokoban;
        caller.setVisible(false);
        initializeLevels();
        currentLevelIndex = 0;
        showCurrentLevel();
        addKeyListener(new ControlKeyListener());
    }
}
