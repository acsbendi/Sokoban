import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * The class that displays the main menu of the application, from where each functionality can be accessed.
 *
 * @author Bendeguz Acs
 */
public class Sokoban extends JFrame{

    /**
     * A JComponent that has a picture as its background.
     */
    class BackgroundPanel extends JComponent {
        private BufferedImage image;
        public BackgroundPanel() {
            try {
                this.image = ImageIO.read(getClass().getResourceAsStream("/background.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }


    /**
     * Event-handler class that sets the selected language in the application,
     * whenever the user changes it in the ComboBox containing the available languages.
     */
    private class LanguageActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Language.selectedLanguage = (Language)languageComboBox.getSelectedItem();
            buttonPanel.refreshLanguage();
            languageLabel.setText(Language.selectedLanguage.getLanguageSelectionText());
        }
    }

    private JPanel languagePanel;
    private ButtonPanel buttonPanel;
    private BorderLayout outerLayout;
    private JComboBox languageComboBox;
    private JLabel languageLabel;


    /**
     * Initializes the various graphical components of this window.
     */
    private void initializeComponents(){
        buttonPanel = new ButtonPanel(this);
        languagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        languageComboBox = new JComboBox(Language.values());
        languageComboBox.addActionListener(new LanguageActionListener());
        languageLabel = new JLabel(Language.selectedLanguage.getLanguageSelectionText());
        languageLabel.setForeground(Color.white);
        languagePanel.add(languageLabel);
        languagePanel.add(languageComboBox);
        languagePanel.setBackground(new Color(0,0,0,0));
        outerLayout = new BorderLayout(50,100);
        outerLayout.addLayoutComponent(new JPanel(),BorderLayout.NORTH);
        outerLayout.addLayoutComponent(new JPanel(),BorderLayout.EAST);
        outerLayout.addLayoutComponent(new JPanel(),BorderLayout.WEST);
        outerLayout.addLayoutComponent(new JPanel(),BorderLayout.SOUTH);
    }

    /**
     * Constructs a new Sokoban application.
     */
    Sokoban(){
        Language.selectedLanguage = Language.English; //the application's default language is English
        setContentPane(new BackgroundPanel());
        initializeComponents();
        setLayout(outerLayout);
        setResizable(true);
        add(buttonPanel,BorderLayout.CENTER);
        add(languagePanel,BorderLayout.NORTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new Sokoban();
    }
}


