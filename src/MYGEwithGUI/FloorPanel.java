package MYGEwithGUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FloorPanel extends JPanel {

    double adjustRatioWidth, adjustRatioLength;

    public FloorPanel(double adjustRatioWidth, double adjustRatioLength){
        super();
        this.adjustRatioWidth = adjustRatioWidth;
        this.adjustRatioLength = adjustRatioLength;
    }

    public void paintComponent(Graphics g){

        int x, y;
        ArrayList<Furniture> itemArrayList = Furniture.getFurnitureArrayList();
        int red, green, blue;
        Color randomColor;

        super.paintComponent(g);
        g.setColor(Color.GRAY);
        g.drawRect(20,20,420,290);


        System.out.println("\nNew Attempt:");
        for(Furniture item: itemArrayList ){
            String itemName = item.getName();
            int itemW = (int)(item.getWidth() * adjustRatioWidth);
            int itemH = (int)(item.getLength() * adjustRatioLength);

//            //For DEBUGGING
//            System.out.println(itemName + ": " + "(" + item.getCurX() + ", " + item.getCurY() + ")");
//            System.out.println("id of " + itemName + ": " + item.getId());
            if( item.getUniqueColor() == null) {
                red = (int) (Math.random() * 256);
                blue = (int) (Math.random() * 256);
                green = (int) (Math.random() * 256);
                randomColor = new Color(red, green, blue);
                item.setUniqueColor(randomColor);
            } else {
                randomColor = item.getUniqueColor();
            }
            g.setColor(randomColor);

            if( item.getCurX() == -1 && item.getCurY() == -1 ) {
                x = 20;
                y = 20;
                // Set the current x & y coods into furnitureArrayList
                item.setCurX(x);
                item.setCurY(y);
            }

            System.out.println("current X & Y: " + item.getCurX() + ", " + item.getCurY());

            x = item.getCurX();
            y = item.getCurY();

            g.fillRect(x,y,itemW, itemH); // after the 2nd: curX, curY
            g.setColor(Color.BLACK);
            g.drawString(itemName, x,y); // after the 2nd: curX, curY

//            x += 50;// after the 2nd: no need this?
//            y += 50;// after the 2nd: no need this?
        }
    }

}