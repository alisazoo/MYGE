/**
 * 30/6/2021
 * I tried but could not nail it!
 * Issue1: graphic context = null, so compile time error occurs before nothing is drawn.
 *  >>> solved. Graphics context is obtained automatically by the system.
 * Issye2: drawDie() method is repeatedly invoked (almost forever).
 *  >>> solved. Delete unnecessary repaint() method.
 */

package ex6_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RollDice extends JPanel {

    public static void main(String[] args) {
        JFrame window = new JFrame("Rolling dice sounds fun");

        RollDice content = new RollDice();
        content.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
//        content.setBackground(Color.PINK);

        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(120,70);
//        window.setSize(new Dimension(145,170));
        window.pack(); // need to set the preferred size of the size (see below)
        window.setVisible(true);
    }

    /**
     * The constructor adds a mouse listener to the panel.
     * The listener will roll the dice when the user clicks the panel.
     * Also, the background colour and preferred size of the panel are set.
     */
    public RollDice(){
        setPreferredSize(new Dimension(125,125));
        setBackground(new Color(200,200,255)); // Light Blue
//        addMouseListener(this);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                Graphics g = getGraphics();
                int randomNum1 = (int)(Math.random()*6) + 1;
                int randomNum2 = (int)(Math.random()*6) + 1;
                int x1 = 10, y1 = 10, x2 = 65, y2 = 65;
                drawDie(g, randomNum1, x1,y1);
                drawDie(g, randomNum2, x2,y2);
                g.dispose();
            }
        });
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
        g.drawRect(x-2,y-2, 52,52);

        switch(value){
            case 1:
                g.fillOval(x + 20,y + 20,10,10);
                break;
            case 2:
                g.fillOval(x + 10,y + 10,10,10);
                g.fillOval(x + 30,y + 30,10,10);
                break;
            case 3:
                g.fillOval(x + 10,y + 10,10,10);
                g.fillOval(x + 20,y + 20,10,10);
                g.fillOval(x + 30,y + 30,10,10);
                break;
            case 4:
                g.fillOval(x + 10,y + 10,10,10);
                g.fillOval(x + 10,y + 30,10,10);
                g.fillOval(x + 30,y + 10,10,10);
                g.fillOval(x + 30,y + 30,10,10);
                break;
            case 5:
                g.fillOval(x + 10,y + 10,10,10);
                g.fillOval(x + 30,y + 10,10,10);
                g.fillOval(x + 20,y + 20,10,10);
                g.fillOval(x + 10,y + 30,10,10);
                g.fillOval(x + 30,y + 30,10,10);
                break;
            case 6:
                g.fillOval(x + 10,y+10,10,10);
                g.fillOval(x + 10,y+20,10,10);
                g.fillOval(x + 10,y+30,10,10);
                g.fillOval(x + 30,y+10,10,10);
                g.fillOval(x + 30,y+20,10,10);
                g.fillOval(x + 30,y+30,10,10);
                break;
        }
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        int randomNum1 = (int)(Math.random()*6) + 1;
        int randomNum2 = (int)(Math.random()*6) + 1;
        int x1 = 10, y1 = 10, x2 = 65, y2 = 65;
        drawDie(g2, randomNum1, x1,y1);
        drawDie(g2, randomNum2, x2,y2);
    }

//    @Override
//    public void mousePressed(MouseEvent e) {
//        Graphics g = getGraphics();
//        int randomNum1 = (int)(Math.random()*6) + 1;
//        int randomNum2 = (int)(Math.random()*6) + 1;
//        int x1 = 10, y1 = 10, x2 = 65, y2 = 65;
//        drawDie(g, randomNum1, x1,y1);
//        drawDie(g, randomNum2, x2,y2);
//        g.dispose();
//    }

//    public void mouseClicked(MouseEvent e) { }
//    public void mouseReleased(MouseEvent e) { }
//    public void mouseEntered(MouseEvent e) { }
//    public void mouseExited(MouseEvent e) { }

}
