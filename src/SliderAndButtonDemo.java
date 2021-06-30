import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SliderAndButtonDemo extends JPanel implements ActionListener, ChangeListener {

    /**
     * This main routine allows this class to be run as an application.
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("My Slider and Button Demo");
        SliderAndButtonDemo content = new SliderAndButtonDemo();
        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800,600);
        window.setVisible(true);
    }

    JLabel displayedText;
    JSlider bgColor, fgColor;
    JButton plainFont, italicFont, boldFont;

    // Constructor
    public SliderAndButtonDemo(){
        setBackground(Color.GRAY);

        // Create displayed label, with properties to match the values of the sliders
        // and the setting of the combo box.
        displayedText = new JLabel("Hello World", JLabel.CENTER);
        displayedText.setOpaque(true);
        displayedText.setBackground(new Color(100,100,100));
        displayedText.setForeground(Color.CYAN);
        displayedText.setFont(new Font("Sans-serif", Font.PLAIN, 25));
        displayedText.setBorder(BorderFactory.createEmptyBorder(0,8,0,8));

        // Create the sliders, and set up the panel to listen for
        // ChangeEvents that are generated by the sliders.
        bgColor = new JSlider(0,255, 100);
//        bgColor.setLabelTable(bgColor.createStandardLabels(51));
        bgColor.setMajorTickSpacing(45);
        bgColor.setMinorTickSpacing(15);
//        bgColor.setPaintLabels(true);
        bgColor.setPaintTicks(true);
        bgColor.addChangeListener(this);

        fgColor = new JSlider(0,100,0);
        fgColor.setLabelTable(bgColor.createStandardLabels(25));
        fgColor.setPaintLabels(true);
        fgColor.addChangeListener(this);

        // Create the buttons to control the font style,
        // and set up the panel to listen for
        // ActionEvents that are generated by each button.
        plainFont = new JButton("Plain Font");
        plainFont.addActionListener(this);
        italicFont = new JButton("Italic Font");
        italicFont.addActionListener(this);
        boldFont = new JButton("Bold Font");
        boldFont.addActionListener(this);

        // Set the layout for the panel, and add the six components.
        // Use Grid layout with 3 rows and 2 colums, and with 5 pixels between components.
        setLayout(new GridLayout(3,2,10,10));
        add(displayedText);
        add(plainFont);
        add(fgColor);
        add(italicFont);
        add(bgColor);
        add(boldFont);
    }


    @Override
    public void stateChanged(ChangeEvent e) {
        Object cmd = e.getSource();
        if(cmd == bgColor){
            int bgVal = bgColor.getValue();
            displayedText.setBackground(new Color(bgVal,bgVal,bgVal));
        } else {
            float hue = fgColor.getValue()/100.0f;
            displayedText.setForeground(Color.getHSBColor(hue, 1.0F, 1.0F));
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if(cmd.equals("Plain Font"))
            displayedText.setFont(new Font("Sans-serif", Font.PLAIN, 30));
        else if(cmd.equals("Italic Font"))
            displayedText.setFont(new Font("Sans-serif", Font.ITALIC, 30));
        else if(cmd.equals("Bold Font"))
            displayedText.setFont(new Font("Sans-serif", Font.BOLD, 30));
    }
}