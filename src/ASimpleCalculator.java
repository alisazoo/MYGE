import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ASimpleCalculator extends JPanel implements ActionListener {

    /**
     * This main routine allows this class to be run as an application.
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("A Simple Calculator");
        ASimpleCalculator content = new ASimpleCalculator();
        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(200,200);
        window.setVisible(true);
    }

    private JTextField xInput, yInput;
    private JButton addButton, subtractButton, multiplyButton, divideButton;
        //TODO check the validity: should JButoon object be declared inside constructor?
    private JLabel displayAns;

    public ASimpleCalculator(){
        // Assign a background colour to the panel and its content panel.
        // This colour will show through in the gaps between components.
        setBackground(Color.GRAY);

        // Add an empty border around the panel,
        // which will also appear in the gray background color.
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        // Create input boxes.
        // Make sure the background colour is white.
        xInput = new JTextField("0", 10);
        xInput.setBackground(Color.WHITE);
        yInput = new JTextField("0", 10);
        yInput.setBackground(Color.WHITE);

        // Create a panel to hold the input boxes and labels for each text field. (e.g. "x = ")
        // These panels use the default FlowLayout layout manager.
        JPanel xPanel = new JPanel();           // Create the subpanel
        xPanel.add(new JLabel("x = "));     // Add a label to the subpanel
        xPanel.add(xInput);                     // Add the text field to the subpanel

        JPanel yPanel = new JPanel();
        yPanel.add(new JLabel("y = "));
        yPanel.add(yInput);

        // Crate four buttons to execute add, subtract, multiply, and divide the two numbers.
//        JPanel signArea = new JPanel();
            //TODO refactor the name.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,4));

        addButton = new JButton("+");
        addButton.addActionListener(this);
        buttonPanel.add(addButton);

        subtractButton = new JButton("-");
        subtractButton.addActionListener(this);
        buttonPanel.add(subtractButton);

        multiplyButton = new JButton("x");
        multiplyButton.addActionListener(this);
        buttonPanel.add(multiplyButton);

        divideButton = new JButton("/");
        divideButton.addActionListener(this);
        buttonPanel.add(divideButton);

        // Create the label to display the calculation, including both expression and the answer.
        displayAns = new JLabel("result shown here.", JLabel.CENTER);
        displayAns.setBackground(Color.WHITE);
        displayAns.setForeground(Color.MAGENTA);
        displayAns.setOpaque(true);

        // layout the components according to the Grid Layout
        // with one rows
        setLayout(new GridLayout(0,1,10,10));
        add(xPanel);                            // Add the subpanel to the main panel
        add(yPanel);
        add(buttonPanel);
        add(displayAns);
        //TODO put one place "add(component)" after setting layout for the whole window.

    }

    /**
     * When the user clicks a button, get the number from the input boxes
     * and perform the operation indicated by the button.
     * Put the result in the answer label.
     * If an error occurs, an error message is put in the label.
     * @param event Action Events generated when the user pushes the button.
     */
    @Override
    public void actionPerformed(ActionEvent event) {

        double x,y;

        try{
            String xStr = xInput.getText();
            x = Double.parseDouble(xStr);
        } catch (NumberFormatException e){
            displayAns.setText("Illegal data for x.");
            xInput.requestFocusInWindow();
            return;
        }
        try{
            String yStr = yInput.getText();
            y = Double.parseDouble(yStr);
        } catch (NumberFormatException e){
            displayAns.setText("Illegal data for y.");
            yInput.requestFocusInWindow();
            return;
        }

        String op = event.getActionCommand();
        if(op.equals("+"))
            displayAns.setText("x + Y = " + (x+y));
        else if(op.equals("-"))
            displayAns.setText("x - Y = " + (x-y));
        else if(op.equals("x"))
            displayAns.setText("x * Y = " + (x*y));
        else if(op.equals("/"))
            if(y == 0)
                displayAns.setText("Cannot divide by zero!");
            else
                displayAns.setText("x / Y = " + (x/y));
    }
}
