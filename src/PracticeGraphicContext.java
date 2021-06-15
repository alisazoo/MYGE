import javax.swing.*;
import java.awt.*;

public class PracticeGraphicContext {
    public static void main(String[] args) {
        JFrame window = new JFrame("MyPractice");

        JPanel content = new JPanel(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.drawString("Hello World!", 20, 30);
                g.drawRoundRect(10,10,200,300,10,20);
                g.drawLine(0,0,100,150);
                g.drawOval(50,50,150,120);
                g.draw3DRect(200,200,100,50,true);
                g.drawArc(250,250,100,75,20,45);
                g.setColor(Color.CYAN);
                g.fill3DRect(350,300, 100, 150, true);
                g.setColor(new Color(50, 20, 135));
                g.fillRect(10, 400, 150, 50);
            }
        };
        content.setLayout(new BorderLayout());

        window.setContentPane(content);
        window.setSize(500,500);
        window.setVisible(true);
    }
}
