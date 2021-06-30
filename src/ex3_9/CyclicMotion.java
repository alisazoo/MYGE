package ex3_9;

// This is the code to draw animation that demonstrates cyclic and oscillating motions at various speeds.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CyclicMotion extends JPanel implements ActionListener {
//public class CyclicMotion extends JPanel {

    int x1 = 10, y1 = 10;  // the coords of top-left corner of the square with cyclic motion
    int x2 = 10, y2 = 50;
    int x3 = 10, y3 = 90;  // the coords of top-left corner of the square with oscillating motion
    int x4 = 10, y4 = 130;

    public void drawFrame(Graphics g, int frameNumber, int width, int height) {
        g.drawString("Frame number " + frameNumber, 50,50); // for debugging.

        int cyclicFrameNumber1 = frameNumber % 300;
        int cyclicFrameNumber2 = frameNumber % 150;

        int x = 2*300;
        int oscilationFrameNumber1 = frameNumber % (x);
        if(oscilationFrameNumber1 > 300)
            oscilationFrameNumber1 = (x) - oscilationFrameNumber1;
        int oscilationFrameNumber2 = frameNumber % (2*x);
        if(oscilationFrameNumber2 > 100)
            oscilationFrameNumber2 = (200) - oscilationFrameNumber2;

        x1 = cyclicFrameNumber1;
        x2 = cyclicFrameNumber2*2;
        x3 = oscilationFrameNumber1;
        x4 = oscilationFrameNumber2*3;

        g.setColor(Color.BLUE);
        g.fillRect(x1,y1,30,30);

        g.setColor(Color.RED);
        g.fillRect(x2,y2,30,30);

        g.setColor(Color.CYAN);
        g.fillRect(x3,y3,30,30);

        g.setColor(Color.MAGENTA);
        g.fillRect(x4,y4,30,30);

    }

    public static void main(String[] args) {

        JFrame window = new JFrame("Simple Animation");
        CyclicMotion drawingArea = new CyclicMotion();

        drawingArea.setBackground(Color.WHITE);
        window.setContentPane(drawingArea);
        drawingArea.setPreferredSize(new Dimension(300,250));
        window.pack();
        window.setLocation(100,50);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        Timer frameTimer = new Timer(20,drawingArea);
        window.setVisible(true);
        frameTimer.start();
    } // end main

    private int frameNum;
    public void actionPerformed(ActionEvent evt) {
        frameNum++;
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawFrame(g, frameNum, getWidth(), getHeight());
    }

}