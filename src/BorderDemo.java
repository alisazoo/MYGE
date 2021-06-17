import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class BorderDemo extends JPanel {

    /**
     * This main routine allows this class to be run as an application.
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("Border Demo");
        BorderDemo content = new BorderDemo();
        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        window.setVisible(true);
    }

    public BorderDemo(){
        setBackground(Color.GRAY);
        setLayout(new GridLayout(0,2, 5,5));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        make(BorderFactory.createLineBorder(Color.RED, 2), "BorderFactory.createLineBorder(Color.RED, 2)");
        make(BorderFactory.createMatteBorder(2,2,5,5,Color.BLUE), "BorderFactory.createMatteBorder(2,2,5,5,Color.BLUE)");
        make(BorderFactory.createEtchedBorder(), "BorderFactory.createEtchedBorder()");
        make(BorderFactory.createLoweredBevelBorder(),"BorderFactory.createLoweredBevelBorder()");
        make(BorderFactory.createRaisedBevelBorder(),"BorderFactory.createRaisedBevelBorder()");
        make(BorderFactory.createTitledBorder("Title"),"BorderFactory.createTitledBorder(\"Title\")");
    }

    void make(Border border,String cmnd){
        JLabel label = new JLabel(cmnd, JLabel.CENTER);
        label.setBorder(border);
        add(label);
    }
}
