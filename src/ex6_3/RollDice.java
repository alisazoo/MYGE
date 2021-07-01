package ex6_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RollDice extends JPanel {

    public static void main(String[] args) {
        JFrame window = new JFrame("Rolling dice sounds fun");
        RollDice content = new RollDice();
        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(120,70);
        window.pack(); // need to set the preferred size of the size (see below)
        window.setVisible(true);
    }

    //----------------------------------------------------------------

    private int die1 = 3;
    private int die2 = 4;

    /**
     * The constructor adds a mouse listener to the panel.
     * The listener will roll the dice when the user clicks the panel.
     * Also, the background colour and preferred size of the panel are set.
     */
    public RollDice(){
        setPreferredSize(new Dimension(125,125));
        setBackground(new Color(200,200,255)); // Light Blue
        setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                roll();
            }
//                Graphics g = getGraphics();
//                int randomNum1 = (int)(Math.random()*6) + 1;
//                int randomNum2 = (int)(Math.random()*6) + 1;
//                int x1 = 10, y1 = 10, x2 = 65, y2 = 65;
//                drawDie(g, randomNum1, x1,y1);
//                drawDie(g, randomNum2, x2,y2);
//                g.dispose();
//            }
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

    /**
     * Roll the dice by randomising that their values.
     * Tell the system to repaint the panel, to show the new value.
     */
    public void roll(){
        die1 = (int)(Math.random()*6) + 1;
        die2 = (int)(Math.random()*6) + 1;
        repaint();  // = call the paintComponent()
                    //  = No need to handle Graphics context in this method
                    //   = No need to use getGraphic() in this method!!!
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
//        int randomNum1 = (int)(Math.random()*6) + 1;
//        int randomNum2 = (int)(Math.random()*6) + 1;
        int x1 = 10, y1 = 10, x2 = 65, y2 = 65;
        drawDie(g2, die1, x1,y1);
        drawDie(g2, die2, x2,y2);
    }
}
