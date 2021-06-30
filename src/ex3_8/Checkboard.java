package ex3_8;

import java.awt.*;
import javax.swing.*;

public class Checkboard extends JPanel {

    public void drawFrame(Graphics g, int width, int height) {
        setSize(400,400);
        int x, y;
        for (int i=0;  i< 8; i++){          // row
            for (int j = 0; j < 8 ; j++) {  // column
                x = i * 50;
                y = j * 50;
                if( (i + j)%2 == 0){        // Set colour for red square
                    g.setColor(Color.RED);
                }
                else{                       // Set colour for blue square
                    g.setColor(Color.BLACK);
                }
                g.fillRect(x,y,50,50);
            }
        }
    }

    public static void main(String[] args) {

        JFrame window = new JFrame("Simple Animation");
        Checkboard drawingArea = new Checkboard();

        drawingArea.setBackground(Color.WHITE);
        window.setContentPane(drawingArea);
        drawingArea.setPreferredSize(new Dimension(400,400));
        window.pack();
        window.setLocation(100,50);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setVisible(true);
    } // end main

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawFrame(g, getWidth(), getHeight());
    }

}