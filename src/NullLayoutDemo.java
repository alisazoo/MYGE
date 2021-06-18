import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NullLayoutDemo extends JPanel implements ActionListener {

    /**
     * This main routine allows this class to be run as an application.
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("Null Layout Demo");
        NullLayoutDemo content = new NullLayoutDemo();
        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(365,300);
        window.setVisible(true);
    }

    JPanel board;
    JButton newGameBtn, resignBtn;
    JLabel msg;
    int clickCount;

    public NullLayoutDemo(){
        setLayout(null);
        setBackground(new Color(0,120,0));  // A dark green background
        setBorder(BorderFactory.createEtchedBorder());
        setPreferredSize(new Dimension(350,240));

        // Create the components and add them to the content pane.
        board = new Checkboard();
        add(board);

        newGameBtn = new JButton("New Game");
        newGameBtn.addActionListener(this);
        add(newGameBtn);

        resignBtn = new JButton("Resign");
        resignBtn.addActionListener(this);
        add(resignBtn);

        msg = new JLabel("Click \"New Game\" to begin.");
        msg.setFont(new Font("Sans-serif", Font.BOLD, 14));
        msg.setForeground(new Color(100,255,100));  // Light green.
        add(msg);

        // Set the position and size of each component by calling its setBounds() method.
        board.setBounds(20,20,164,164);
        newGameBtn.setBounds(210,60,120,30);
        resignBtn.setBounds(210, 120, 120,30);
        msg.setBounds(20, 200, 330,30);
    }

    // Respond to a click on the button by changing the displayed message.
    // The message tells how many times the button has been clicked.
    @Override
    public void actionPerformed(ActionEvent evt) {
        String buttonText = evt.getActionCommand();
        clickCount++;
        if(clickCount == 1)
            msg.setText("First click: \"" + buttonText + "\" was clicked.");
        else
            msg.setText("Click no. " + clickCount + ": \"" + buttonText + "\" was clicked.");
    }

    private static class Checkboard extends JPanel{

        public Checkboard(){
            setPreferredSize(new Dimension(164,164));
        }

        public void paintComponent(Graphics g){

            // Draw a 2-pixel black border around the edges of the board.
            // (There is no need to call super.paintComponent() since
            // this method paints the entire surface of the component.)
            g.setColor(Color.BLACK);
            g.drawRect(0,0,getSize().width - 1, getSize().height - 1);
            g.drawRect(1,1,getSize().width - 3, getSize().height - 3);

            // Draw checkerbord pattern in gray and lightGray.
            for(int row = 0; row < 8; row++){
                for(int col = 0; col < 8; col++ ){
                    if(row % 2 == col % 2 )
                        g.setColor(Color.LIGHT_GRAY);
                    else
                        g.setColor(Color.GRAY);
                    g.fillRect(2 + col * 20, 2 + row * 20, 20, 20);
                }
            }
        }
    } // end: nested class Checkboard

}
