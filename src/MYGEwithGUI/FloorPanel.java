package MYGEwithGUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FloorPanel extends JPanel {

    double adjustRatioWidth, adjustRatioLength;

    public void setFloorRatio(double adjustRatioWidth, double adjustRatioLength){
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
        g.drawRect(10,10,440,300);

        for(Furniture item: itemArrayList ){
            String itemName = item.getName();
            int itemW = (int)(item.getWidth() * adjustRatioWidth);
            int itemH = (int)(item.getLength() * adjustRatioLength);

            if( item.getUniqueColor() == null) {
                red = (int) (Math.random() * 256);
                blue = (int) (Math.random() * 256);
                green = (int) (Math.random() * 256);
                randomColor = new Color(red, green, blue);
                item.setUniqueColor(randomColor);
            } else {
                randomColor = item.getUniqueColor();
            }

            if( item.getCurX() == -1 && item.getCurY() == -1 ) {
                x = 20;
                y = 20;
                // Set the current x & y coods into furnitureArrayList
                item.setCurX(x);
                item.setCurY(y);
            }
//todo
//            System.out.println("<paintComponent()>\ncurrent X & Y: " + item.getCurX() + ", " + item.getCurY());

            x = item.getCurX();
            y = item.getCurY();

            g.setColor(randomColor);
            g.fillRect(x,y,itemW, itemH); // after the 2nd: curX, curY
            if( item.isSelected() ){//TODO: this does not work so fix it!
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.WHITE);
            }
            g.drawRect(x,y,itemW, itemH);
            g.setColor(Color.BLACK);
            g.drawString(itemName + ": (" + x + ", " + y + ")", x,y); // after the 2nd: curX, curY

        }
    }

}