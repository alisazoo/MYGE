package Ex6_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RollDice2 extends JPanel {

    //---------------------------------------------------------------
    private int randomNum1 = 3;
    private int randomNum2 = 4;

    private Timer timer;

    /**
     * The constructor adds a mouse listener to the panel.
     * The listener will roll the dice when the user clicks the panel.
     * Also, the background colour and preferred size of the panel are set.
     */
    public RollDice2(){
        setLayout(new BorderLayout(3,3));
        setBackground(Color.BLUE); // will show thorough the gap in the BorderLayout.
        setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));

        JPanel dicePanel = new JPanel(){    // the drawing surface, where dice are shown
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                drawDie(g2, randomNum1, 10,10);
                drawDie(g2, randomNum2, 65,65);
            }
        };
        dicePanel.setPreferredSize(new Dimension(125,125));
        dicePanel.setBackground(new Color(200,200,255)); // Light Blue
        add(dicePanel, BorderLayout.CENTER);

        JButton rollBtn = new JButton("Roll");
        rollBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roll();
            }
        });
        add(rollBtn, BorderLayout.SOUTH);
    } // end constructor

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

    public void roll(){
        if(timer != null)
            return;
        timer = new Timer(100, new ActionListener() {
            int frames = 1;
            @Override
            public void actionPerformed(ActionEvent e) {
                randomNum1 = (int)(Math.random()*6) + 1;
                randomNum2 = (int)(Math.random()*6) + 1;
                repaint();
                frames++;
                if(frames == 10){
                    timer.stop();
                    timer = null;
                }
            }
        });
        timer.start();
    }

}
