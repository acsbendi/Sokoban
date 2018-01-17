import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * The window for saving a highscore.
 * The user can type their nickname in the textbox, then after the OK button is pressed,
 * the highscore is saved.
 *
 * @author Bendeguz Acs
 */
public class HighScoreSaver extends JFrame{
    private Sokoban sokoban;
    private long levelID;
    private int numberOfMoves;
    private String nameOfPlayer;
    private TextField textField;

    /**
     * The action-handler class for the Save button.
     */
    private class SaveButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            nameOfPlayer = textField.getText();
            saveRecord();
        }
    }


    /**
     * This method saves the record into the database, and closes the window.
     */
    private void saveRecord(){
        List<Record> records;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("highscores.txt"));
            records = (ArrayList)ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            records = new ArrayList<>();
        }

        records.add(new Record(levelID,numberOfMoves,nameOfPlayer));

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("highscores.txt"));
            oos.writeObject(records);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sokoban.setVisible(true);
        dispose();
        setVisible(false);
    }


    HighScoreSaver(long levelID, int numberOfMoves, Sokoban sokoban){
        this.sokoban = sokoban;
        this.levelID = levelID;
        this.numberOfMoves = numberOfMoves;
        setTitle(Language.selectedLanguage.getSavingHighScoreText());
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        JLabel nameLabel = new JLabel(Language.selectedLanguage.getTypeYourNameText());
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        getContentPane().add(nameLabel);
        textField = new TextField();
        getContentPane().add(textField);
        JButton saveButton = new JButton(Language.selectedLanguage.getSaveHighScoreText());
        saveButton.addActionListener(new SaveButtonListener());
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        getContentPane().add(saveButton);
        setMinimumSize(new Dimension(350,100));
        setResizable(false);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
