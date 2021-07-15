package practice.headFirstJava.ch12;

import javax.swing.*;
import java.awt.*;

public class SimpleAnimation {

    int x = 70;
    int y = 70;

    public static void main(String[] args) {
        SimpleAnimation gui = new SimpleAnimation();
        gui.go();
    }

    public void go(){
        JFrame frame = new JFrame("Simple Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MyDrawPanel drawPanel = new MyDrawPanel();

        frame.getContentPane().add(drawPanel);
        frame.setBackground(Color.WHITE);
        frame.setSize(300,300);
        frame.setVisible(true);

        for(int i = 0; i < 100; i++){
            x++;
            y++;
            drawPanel.repaint();

            try{
                Thread.sleep(50);
            } catch (Exception e){

            }
        }
    } // end go() method

    class MyDrawPanel extends JPanel{
        public void paintComponent(Graphics g){
            g.setColor(Color.white);
            g.fillRect(0,0,this.getWidth(),this.getHeight());

            g.setColor(Color.CYAN);
            g.fillOval(x,y,50,50);
        }
    } // end inner class MyDrawPanel
}
