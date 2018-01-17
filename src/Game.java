import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * The class responsible for one gameplay of a specified level.
 *
 * @author Bendeguz Acs
 */
public class Game extends JFrame{
    private Sokoban sokoban;
    private Level level;
    private JPanel middlePanel;
    private List<JLabel> squareImageLabels;
    private int numberOfMoves;


    /**
     * The method that should be called after every successful move.
     */
    public void onMove(){
        numberOfMoves++;
        if(level.isOver()) end();
    }

    /**
     * Creates the next window (HighScoreSaver) and closes this one after the games is over.
     */
    public void end(){
        new HighScoreSaver(level.getLevelID(),numberOfMoves,sokoban);
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

    /**
     * Constructs a new {@code Game} with the specified level to be played.
     * @param level The {@code Level} to be played.
     * @param sokoban The {@code Sokoban} object creating this new instance.
     */
    Game(Level level, Sokoban sokoban){
        this.sokoban = sokoban;
        numberOfMoves = 0;
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
