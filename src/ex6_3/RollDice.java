/**
 * 30/6/2021
 * I tried but could not nail it!
 * Issue1: graphic context = null, so compile time error occurs before nothing is drawn.
 *  >>> solved. Graphics context is obtained automatically by the system.
 * Issye2: drawDie() method is repeatedly invoked (almost forever).
 */

package ex6_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RollDice extends JPanel implements ActionListener {

    public static void main(String[] args) {
        JFrame window = new JFrame("Rolling dice sounds fun");
        RollDice content = new RollDice();
        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(120,60);
        content.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        content.setBackground(Color.PINK);
        window.setSize(new Dimension(120,180));
//        window.pack();
        window.setVisible(true);
    }

    public RollDice(){
    }

    /**
     * This method is used to draw a dice corresponding to the value passed.
     * @param g Graphic context
     * @param value the value of the die: from 1 to 6
     * @param x the x-coords of top-left corner of the dice clicked.
     * @param y the y-coords of top-left corner of the dice clicked.
     */
    private void drawDie(Graphics g, int value, int x, int y){
        g.setColor(Color.white);
        g.fillRect(x,y, 50,50);
        g.setColor(Color.BLACK);
        g.drawString(String.valueOf(value), x+25, y+25);
        repaint();
    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        int randomNum1 = (int)(Math.random()*6);
        int randomNum2 = (int)(Math.random()*6);
        System.out.println("hello");
        int x1 = 10, y1 = 10, x2 = 60, y2 = 60;
        drawDie(g, randomNum1, x1,y1);
        drawDie(g, randomNum2, x2,y2);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("click");

    }
}
