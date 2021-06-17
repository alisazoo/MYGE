import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This program simply demonstrates using a JTextArea in a JScrollPane.
 *
 * Pending: adding actionListener (17/6/2021)
 */
public class TextAreaDemo extends JPanel {
//public class TextAreaDemo extends JPanel implements ActionListener {

    /**
     * A main routine allows this class to be run as an application.
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("Text Area Demo");

        TextAreaDemo content = new TextAreaDemo();
//        content.addActionListener(this);

        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(400, 100);
        window.setVisible(true);
    }

    public TextAreaDemo(){
        String text = "Hello, this is Demo, not Memo." +
                "\n Enjoy silly talk and please tell me your thinking." +
                "\n Shall we shing?" +
                "\n No???" +
                "\n Why?" +
                "\n I do never care of your skill." +
                "\n Just enjoy together!";
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        textArea.setText(text);
        textArea.setFont(new Font("Sans-serif", Font.PLAIN, 12));
        textArea.setMargin(new Insets(5,5,5,5));

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        add(scrollPane, BorderLayout.CENTER);
    }


//    @Override
//    public void actionPerformed(ActionEvent e) {
//        String text = content.getText();
//        System.out.println(text);
//    }


}
