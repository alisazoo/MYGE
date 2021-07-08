package MYGEwithGUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FloorPanel extends JPanel {

    public void paintComponent(Graphics g){
        int x = 50, y = 50;
        ArrayList<Furniture> itemArrayList = Furniture.getFurnitureArrayList();
        int red, green, blue;
        Color randomColor;

        super.paintComponent(g);
        g.setColor(Color.GRAY);
        g.drawRect(20,20,420,290);


        for(Furniture item: itemArrayList ){
            String itemName = item.getName();
            int itemW = item.getWidth();
            int itemH = item.getLength();

            // Set the current x & y coods into furnitureArrayList
            item.setCurX(x);
            item.setCurY(y);

            //For DEBUGGING
            System.out.println(itemName + ": " + "(" + item.getCurX() + ", " + item.getCurY() + ")");
            System.out.println("id of " + itemName + ": " + item.getId());

            red = (int) (Math.random() * 256);
            blue = (int) (Math.random() * 256);
            green = (int) (Math.random() * 256);
            randomColor = new Color(red, green, blue);

            g.setColor(randomColor);
            g.fillRect(x,y,itemW, itemH);
            g.setColor(Color.BLACK);
            g.drawString(itemName, x,y);

            x += 50;
            y += 50;
        }
    }

}