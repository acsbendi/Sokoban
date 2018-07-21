import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * The panel on the main menu that contains the buttons that start the functions of the application.
 */
public class ButtonPanel extends JPanel{
    private Sokoban sokoban;
    private JButton startNewGameButton;
    private JButton addNewLevelButton;
    private JButton showHighScoresButton;
    private JButton exitButton;
    private Image buttonBackground;

    /**
     * Event-handler class that creates a new level editor window, after the Add New Level button has been pressed.
     */
    private class ClickOnAddNewLevelListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            new LevelEditor(sokoban);
        }
    }

    /**
     * Event-handler class that creates a window on which a new game can be played,
     * after the Start New Game button has been pressed.
     */
    private class ClickOnStartNewGameListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            new LevelSelector(sokoban);
        }
    }
    /**
     * Event-handler class that creates a window showing the previous high scores,
     * after the Show Highscores button has been pressed.
     */
    private class ClickOnShowHighScoresListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            new HighScoresDisplay(sokoban);
        }
    }

    /**
     * Event-handler class that closes the application,
     * after the Exit button has been pressed.
     */
    private class ClickOnExitListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            sokoban.dispatchEvent(new WindowEvent(sokoban,WindowEvent.WINDOW_CLOSING));
        }
    }


    /**
     * Reloads the language-specific resources (text) on this panel.
     */
    public void refreshLanguage(){
        startNewGameButton.setText(Language.selectedLanguage.getStartNewGameText());
        addNewLevelButton.setText(Language.selectedLanguage.getAddNewLevelText());
        showHighScoresButton.setText(Language.selectedLanguage.getShowHighScoreText());
        exitButton.setText(Language.selectedLanguage.getExitText());
    }


    /**
     * Constructs a new {@code ButtonPanel}.
     * @param sokoban The {@code Sokoban} object that is creating (and using) this object.
     */
    ButtonPanel(Sokoban sokoban){
        this.sokoban = sokoban;
        GridLayout layout = new GridLayout(4,1);
        layout.setVgap(20);
        setLayout(layout);
        startNewGameButton = new JButton(Language.selectedLanguage.getStartNewGameText());
        startNewGameButton.addActionListener(new ClickOnStartNewGameListener());
        addNewLevelButton = new JButton(Language.selectedLanguage.getAddNewLevelText());
        addNewLevelButton.addActionListener(new ClickOnAddNewLevelListener());
        showHighScoresButton = new JButton(Language.selectedLanguage.getShowHighScoreText());
        showHighScoresButton.addActionListener(new ClickOnShowHighScoresListener());
        exitButton = new JButton(Language.selectedLanguage.getExitText());
        exitButton.addActionListener(new ClickOnExitListener());
        add(startNewGameButton);
        add(addNewLevelButton);
        add(showHighScoresButton);
        add(exitButton);

        try {
            buttonBackground = ImageIO.read(getClass().getResource("/button.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(Component c : getComponents()) {
            ((JButton) c).setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            ((JButton) c).setIcon(new ImageIcon(buttonBackground));
            ((JButton) c).setHorizontalTextPosition(SwingConstants.CENTER);
            ((JButton) c).setMinimumSize( new Dimension(100,20));
            ((JButton) c).setForeground( Color.white);
        }

        setBackground(new Color(0,0,0,0));
    }
}
