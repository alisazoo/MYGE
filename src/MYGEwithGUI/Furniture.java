package MYGEwithGUI;

import java.awt.*;
import java.util.ArrayList;

/**
 * Represents a furniture.
 * Premise:
 * The size, both the length and the width, should be same or less than floor's
 * to fit the floor.
 */
public class Furniture {

    private int id;
    private String name;
    private int length;
    private int width;
    private Color uniqueColor;

    private int curX;   // current x-coordinates of top-left corner
    private int curY;   // current y-coordinates of top-left corner
    private int preX;   // previous x-coordinates of top-left corner
    private int preY;   // previous y-coordinates of top-left corner

    //For GUI
    private int offsetX;
    private int offsetY;
    private boolean isSelected = false;

    private static final ArrayList<Furniture> furnitureArrayList = new ArrayList<>();

    // Constructor
    public Furniture(){
    }

    public Furniture(String name, int width, int length){
        this.length = length;
        this.width = width;
        this.name = name;
        this.id = IdProvider.getInstance().getUniqueId();
        this.curX = -1;
        this.curY = -1;
    }

    public static ArrayList<Furniture> getFurnitureArrayList(){
        return furnitureArrayList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Color getUniqueColor() {
        return uniqueColor;
    }

    public void setUniqueColor(Color uniqueColor) {
        this.uniqueColor = uniqueColor;
    }

    public int getCurX() {
        return curX;
    }

    public void setCurX(int curX) {
        this.curX = curX;
    }

    public int getCurY() {
        return curY;
    }

    public void setCurY(int curY) {
        this.curY = curY;
    }

    public int getPreX() {
        return preX;
    }

    public void setPreX(int preX) {
        this.preX = preX;
    }

    public int getPreY() {
        return preY;
    }

    public void setPreY(int preY) {
        this.preY = preY;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
