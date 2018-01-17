import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.IntStream;


/**
 * A simple dialog on which the user can set the dimensions of the new level.
 */
public class LevelDimensionsDialog extends JDialog {
    private JComboBox widthComboBox;
    private JComboBox heightComboBox;
    private LevelEditor caller;


    /**
     * Event-handler class for notifying (returning the required values to) the caller of this dialog after the OK button has been pressed.
     */
    class OKButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] dimensions = new int[2];
            dimensions[0] = (int)widthComboBox.getSelectedItem();
            dimensions[1] = (int)heightComboBox.getSelectedItem();
            setVisible(false);
            caller.notifyOnSelection(dimensions);
        }
    }

    LevelDimensionsDialog(LevelEditor caller){
        this.caller = caller;
        JButton okButton;
        okButton = new JButton(Language.selectedLanguage.getOKText());
        okButton.addActionListener(new OKButtonListener());
        widthComboBox = new JComboBox(IntStream.rangeClosed(Level.MIN_WIDTH,Level.MAX_WIDTH).boxed().toArray());
        heightComboBox = new JComboBox(IntStream.rangeClosed(Level.MIN_HEIGHT,Level.MAX_HEIGHT).boxed().toArray());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(3,3,3,3);
        c.gridx = 0;
        c.gridy = 0;
        panel.add(new JLabel(Language.selectedLanguage.getWidthSelectionText()),c);

        c.gridx = 1;
        panel.add(widthComboBox,c);

        c.gridy = 1;
        c.gridx = 0;
        panel.add(new JLabel(Language.selectedLanguage.getHeightSelectionText()),c);

        c.gridx = 1;
        panel.add(heightComboBox,c);

        c.gridy = 2;
        c.ipadx = 40;
        panel.add(okButton,c);

        getContentPane().add(panel,BorderLayout.CENTER);
        getContentPane().add(new JPanel(),BorderLayout.SOUTH);
        getContentPane().add(new JPanel(),BorderLayout.NORTH);
        getContentPane().add(new JPanel(),BorderLayout.EAST);
        getContentPane().add(new JPanel(),BorderLayout.WEST);
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

}
