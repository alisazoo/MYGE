import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawPanel_not_used extends JPanel implements ActionListener {

    /**
     * This main routine allows this class to be run as an application.
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("MYGE");
        DrawPanel_not_used content = new DrawPanel_not_used();
        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(400, 250);
        window.setVisible(true);
    }

    private JTextField width, length;
    private JPanel widthPanel, lengthPanel, displayPanel;
    private JLabel displaySizeLabel, displaySize;
    private JButton setButton;

    public DrawPanel_not_used(){
        setBackground(Color.DARK_GRAY);
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        // Create text input boxes where the user input the size of the floor
        width = new JTextField("0", 10);
        width.setBackground(Color.WHITE);
        length = new JTextField("0", 10);
        length.setBackground(Color.WHITE);

        // Create panels to hold input boxes with label to show unit (mm)
        // These panels use the default FlowLayout layout manager.
        widthPanel = new JPanel();
        widthPanel.add(new JLabel("The width: "));
        widthPanel.add(width);
        widthPanel.add(new JLabel("(mm)"));

        lengthPanel = new JPanel();
        lengthPanel.add(new JLabel("The length: "));
        lengthPanel.add(length);
        lengthPanel.add(new JLabel("(mm)"));

        // Create a label to display the size of floor
        displaySizeLabel = new JLabel("Floor size: ");
        displaySize = new JLabel("0 mm x 0 mm");
        displayPanel = new JPanel();
        displayPanel.setBackground(Color.WHITE);
        displayPanel.setForeground(Color.MAGENTA);
        displayPanel.add(displaySizeLabel);
        displayPanel.add(displaySize);

        // Create a button to reflect user's input to display
        setButton = new JButton("Set the floor size");
        setButton.addActionListener(this);

        setLayout(new GridLayout(0,1,5,5));
        //TODO arrange components in more elegant way!!!
        add(widthPanel);
        add(lengthPanel);
        add(setButton);
        add(displayPanel);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        int wVal, lVal;

        try {
            String wStr = width.getText();
            wVal = Integer.parseInt(wStr);
        } catch(NumberFormatException e){
            displaySize.setText("Illegal data for width.");
            width.requestFocusInWindow();
            return;
        }

        try{
            String lStr = length.getText();
            lVal = Integer.parseInt(lStr);
        } catch(NumberFormatException e){
            displaySize.setText("Illegal data for length.");
            length.requestFocusInWindow();
            return;
        }

        // Display the size of floor
        displaySize.setText(wVal + "mm x " + lVal + "mm");
    }
}
