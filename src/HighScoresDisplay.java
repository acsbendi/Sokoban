import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * A window that shows a list of the previously saved high scores.
 */
public class HighScoresDisplay extends JFrame {


    /**
     * Event-handler class for reopening the main menu after this window has been closed.
     */
    class HighScoresDisplayWindowListener extends WindowAdapter{
        @Override
        public void windowClosed(WindowEvent e){
            caller.setVisible(true);
        }
    }

    private Sokoban caller;
    private List<Record> records;

    /**
     * Reads all the record stored in a file into a list of Record objects. Uses object deserialization.
     */
    private void readRecords(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("highscores.txt"));
            records = (ArrayList<Record>)ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            records = new ArrayList<>();
        }
    }

    HighScoresDisplay(Sokoban sokoban){
        caller = sokoban;
        caller.setVisible(false);
        readRecords();
        addWindowListener(new HighScoresDisplayWindowListener());
        List<String> stringList = new ArrayList<>(records.size());
        for(Record r : records)
            stringList.add(r.toString());
        getContentPane().add(new JList<>(stringList.toArray(new String[0])));
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
