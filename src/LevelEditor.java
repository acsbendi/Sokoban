import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;


/**
 * The class for adding new levels that can be played later.
 */
class LevelEditor extends JFrame{

        /**
        * Event-handler class that sets the currently selected square based on user input.
        */
        private class SquareSelectionListener extends MouseAdapter{
            @Override
            public void mouseClicked(MouseEvent e) {
                if(selectedLabel != null) selectedLabel.setBorder(BorderFactory.createEmptyBorder());
                selectedLabel = (JLabel)e.getSource();
                selectedLabel.setBorder(BorderFactory.createLineBorder(Color.RED,3));
                createSelectedSquare();
            }
        }

        /**
        * Event-handler class that saves the level after the Save Level button has been pressed
        */
        private class SaveButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            acceptLevel();
        }
    }

    /**
     * Event-handler class that sets one square of the currently created level to the currently selected square.
     */
        private class SquareClickListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            int index = squareImageLabels.indexOf(e.getSource());
            try {
                level.setSquare(index % level.getWidth(), index / level.getWidth(), selectedSquare);
            }catch (InvalidParameterException ipe){
                return;
            }
            if(selectedLabel == playerLabel) {
                Square previousPlayerSquare = (level.getPlayer().getSquare());
                level.setPlayerSquare((FlatSquare) selectedSquare);
                refreshSquare(level.getIndexOf(previousPlayerSquare));
            }
            refreshSquare(index);
            createSelectedSquare();
        }
    }

        private Sokoban sokoban;
        private Level level;
        private JLabel wallLabel;
        private JLabel floorLabel;
        private JLabel destinationFloorLabel;
        private JLabel playerLabel;
        private JLabel crateLabel;
        private JLabel selectedLabel;
        private Square selectedSquare;
        private List<JLabel> squareImageLabels;


    /**
     * Creates a new instance of the currently selected Square. This will be added to the level.
     */
    private void createSelectedSquare(){
            if(selectedLabel == wallLabel){
                selectedSquare = new Wall();
            } else if(selectedLabel == floorLabel){
                selectedSquare = new Floor(null);
            } else if(selectedLabel == destinationFloorLabel){
                selectedSquare = new DestinationFloor(null);
            }else if(selectedLabel == playerLabel){
                selectedSquare = new Floor(new Player(level));
            }else if(selectedLabel == crateLabel){
                selectedSquare = new Floor(new Crate());
            }
        }


    /**
     * Method for initializing the top panel on this window.
     */
        private void initializeTopPanel(){
            JPanel topPanel;
            topPanel = new JPanel(new GridLayout(1,5));
            wallLabel = new JLabel(new ImageIcon(Wall.getStaticImage()));
            floorLabel = new JLabel(new ImageIcon(Floor.getStaticImage()));
            destinationFloorLabel = new JLabel(new ImageIcon(DestinationFloor.getStaticImage()));
            playerLabel = new JLabel(new ImageIcon(Displayable.combineImages(Floor.getStaticImage(),Player.getStaticImage())));
            crateLabel = new JLabel(new ImageIcon(Displayable.combineImages(Floor.getStaticImage(),Crate.getStaticImage())));
            wallLabel.setBorder(BorderFactory.createEmptyBorder(3,3,3,3)); //set one empty border on a label containing a square image, to avoid resize after the first selection
            topPanel.add(wallLabel);
            topPanel.add(floorLabel);
            topPanel.add(destinationFloorLabel);
            topPanel.add(playerLabel);
            topPanel.add(crateLabel);
            SquareSelectionListener squareSelectionListener = new SquareSelectionListener();
            for(Component c : topPanel.getComponents())
                c.addMouseListener(squareSelectionListener);
            getContentPane().add(topPanel,BorderLayout.NORTH);
        }

    /**
     * Method for initializing the middle panel on this window.
     */
        private void initializeMiddlePanel(){
            squareImageLabels = new ArrayList<>();
            JPanel middlePanel = new JPanel(new GridLayout(level.getHeight(),level.getWidth()));
            SquareClickListener squareClickListener = new SquareClickListener();
            JLabel jLabel;
            for(int i = 0; i < level.getWidth()* level.getHeight(); ++i) {
                jLabel = new JLabel(new ImageIcon(level.getSquare(i % level.getWidth(), i / level.getWidth()).getImage()));
                jLabel.setBorder(BorderFactory.createLineBorder(Color.RED,1));
                jLabel.addMouseListener(squareClickListener);
                squareImageLabels.add(jLabel);
                middlePanel.add(jLabel);
            }
            getContentPane().add(middlePanel,BorderLayout.CENTER);
        }

    /**
     * Method for initializing the bottom panel on this window.
     */
        private void initializeBottomPanel(){
            JPanel bottomPanel;
            bottomPanel = new JPanel(new FlowLayout());
            JButton saveButton = new JButton(Language.selectedLanguage.getSaveLevelText());
            saveButton.addActionListener(new SaveButtonListener());
            bottomPanel.add(saveButton);
            getContentPane().add(bottomPanel,BorderLayout.SOUTH);
        }

    /**
     * Refreshes a square's image. The square is referenced by an index.
     * @param index The index of a JLabel in the list of JLabels that show the images of squares.
     */
        private void refreshSquare(int index){
            squareImageLabels.get(index).setIcon(new ImageIcon(level.getSquare(index % level.getWidth(), index / level.getWidth()).getImage()));
        }

        /**
        * Saves the level into a file. Uses object serialization.
        */
        private void acceptLevel(){
            //loading previous levels
            List<Level> levels;
            try {
                ObjectInputStream fis = new ObjectInputStream(new FileInputStream("src/levels.txt"));
                levels = (List<Level>)fis.readObject();
                fis.close();
            } catch (IOException | ClassNotFoundException e) {
                levels = new ArrayList<>();
            }
            levels.add(level);
            LevelEditor.saveLevels(levels);
            setVisible(false);
            sokoban.setVisible(true);
            dispose();
        }

    /**
     * Saves the specified list of levels using object serialization.
     * @param levels The list of levels to be saved.
     */
    public static void saveLevels(List<Level> levels){
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/levels.txt"));
                oos.writeObject(levels);
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    /**
     * Constructs a new LevelEditor.
     * @param sokoban The {@code Sokoban} object that is creating this object.
     */
    LevelEditor(Sokoban sokoban){
            this.sokoban = sokoban;
            this.sokoban.setVisible(false);
            new LevelDimensionsDialog(this);
            initializeTopPanel();
            initializeBottomPanel();
            getContentPane().add(new JPanel(), BorderLayout.WEST);
            getContentPane().add(new JPanel(), BorderLayout.EAST);
            setResizable(false);
            setTitle(Language.selectedLanguage.getAddNewLevelText());
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }


    /**
     * This method is called by the LevelDimensionsDialog, it creates a level of the given dimensions,
     * and initializes the middle panel. Until this method is called, the level editor window is not visible.
     * @param dimensions The dimensions of the level to be created.
     */
    void notifyOnSelection(int[] dimensions){
            Player player = new Player(null);
            level = new Level(dimensions[0],dimensions[1], player);
            player.setLevel(level);
            initializeMiddlePanel();
            pack();
            setVisible(true);
        }
}
