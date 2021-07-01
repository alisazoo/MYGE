package Ex6_4;

import javax.swing.*;

/**
 * main program that just opens a window that shows a JPanel called RollDice2.
 */
public class RollDice2Demo {

    public static void main(String[] args) {
        JFrame window = new JFrame("Rolling dice sounds fun");
        RollDice2 content = new RollDice2();

        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(120,70);
        window.pack(); // need to set the preferred size of the size (see below)
        window.setVisible(true);
    }

}
