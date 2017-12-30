import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * The singleton class responsible for one gameplay of a specified level.
 */
public class Game extends JFrame{
    private Sokoban caller;
    private Level level;
    private JPanel middlePanel;
    private List<JLabel> squareImageLabels;
    private Record record;


    public void onMove(){
        record.numberOfMoves++;
        if(level.isOver()) end();
    }

    /**
     * Creates the next window (HighScoreSaver) and closes this one after the games is over.
     */
    public void end(){
        new HighScoreSaver(record,caller);
        setVisible(false);
        dispose();
    }


    /**
     * Refreshes the image of specified square.
     * @param square The square to be refreshed.
     */
    public void refreshSquare(Square square){
        squareImageLabels.get(level.getIndexOf(square)).setIcon(new ImageIcon(square.getImage()));
    }

    Game(Level level, int levelIndex, Sokoban sokoban){
        caller = sokoban;
        record = new Record();
        record.levelIndex = levelIndex;
        record.numberOfMoves = 0;
        this.level = level;
        level.getPlayer().setGame(this);
        middlePanel = new JPanel(new GridLayout(level.getHeight(),level.getWidth()));
        squareImageLabels = new ArrayList<>();
        JLabel jLabel;
        for(int i = 0; i < level.getWidth()*level.getHeight(); ++i) {
            jLabel = new JLabel(new ImageIcon(level.getSquare(i % level.getWidth(), i / level.getWidth()).getImage()));
            squareImageLabels.add(jLabel);
            middlePanel.add(jLabel);
        }
        getContentPane().add(middlePanel);
        addKeyListener(level.getPlayer());
        setResizable(false);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
