import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Displays a window that shows 25 copies of a message.
 * The colour and position of each message is selected at random.
 * The font of each message is randomly chosen from among five possible fonts.
 * The messages are displayed on a black background.
 * When the user clicks the window, the content of the window is repainted,
 * with the strings in newly selected random colours, fonts, and positions.
 */
public class ClickableRandomStrings2 {
    public static void main(String[] args) {
        JFrame window = new JFrame("Random Strings");
        RandomStringsPanel content = new RandomStringsPanel("Bye!");
//        content.addMouseListener(new RepaintOnClick());
        content.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //super.mousePressed(e);
                Component source = (Component)e.getSource();
                source.repaint();
            }
        });
        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(120, 70);
        window.setSize(350, 250);
        window.setVisible(true);
    }
}
