package MYGEwithGUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FurnitureDialog {

    static ArrayList<Furniture> itemList = Furniture.getFurnitureArrayList();
    static FurnitureTempData inputData;
    static boolean isDuplicateFurniture = false;
    static boolean isInputValid = true;

    /**
     * This inputFurnitureData method aims to control the flow around input furniture dialog.
     * This calls showInputFurnitureDialog method to get the result array, then
     * check whether the data is duplicate or not.
     * If the data is not duplicate, send the result array to setFurnitureInputData method
     * to set the input data to DefaultListModel and ArrayList of furniture.
     */
    public static void inputFurniture(DefaultListModel<String> listModel, FurnitureTempData tempResult){

        do{
            inputData = showInputFurnitureDialog(tempResult);
            checkIsDuplicate(inputData, itemList);

        } while (isDuplicateFurniture);

        setFurnitureInputData( inputData, itemList, listModel );

        if(!isInputValid){
            // show dialog again
        }

    }

    /**
     * Check whether input data is duplicated comparing to the existing data.
     * If the name is duplicated, the input data is invalid in this case
     * because the name should be identical in this program.
     * @param tempResult temporary object containing the result of furniture item input
     * @param itemList ArrayList contains the data of Furniture items.
     */
    private static void checkIsDuplicate(
            FurnitureTempData tempResult, ArrayList<Furniture> itemList){

        String inputFurnitureName = tempResult.getFurnitureName();
        for (Furniture newItem: itemList){
            if(newItem.getName().equals(inputFurnitureName)){
                isDuplicateFurniture = true;
                break;
            } else {
                isDuplicateFurniture = false;
            }
        }
    }

    /**
     * This static method inputFurnitureDialog create the input dialog
     * for user to input the data of new furniture item.
     * If the input name of the new item is same as the existing one,
     * this program requires to input again.
     * If the input item is valid, this set the isDuplicatedFurniture of the item true and
     * return the result as a String array.
     * @return array with String value of name, width, and length of the new item.
     */
    public static FurnitureTempData showInputFurnitureDialog( FurnitureTempData tempResult ){

        JPanel panel = new JPanel(new BorderLayout(5,5));

        JPanel label = new JPanel(new GridLayout(0,1,2,2));
        label.add( new JLabel("Name", SwingConstants.RIGHT) );
        label.add( new JLabel("Width (cm)", SwingConstants.RIGHT) );
        label.add( new JLabel("Length (cm)", SwingConstants.RIGHT) );
        panel.add( label, BorderLayout.WEST );

        JPanel control = new JPanel(new GridLayout(0,1,2,2));
        JTextField furnitureNameField;
        JTextField furnitureWidthField;
        JTextField furnitureLengthField;
        if(MoyogaeDemo.isDefaultData()) {
            furnitureNameField = new JTextField("Desk");
            furnitureWidthField = new JTextField("120");
            furnitureLengthField = new JTextField("45");
        } else {
            furnitureNameField = new JTextField();
            furnitureWidthField = new JTextField();
            furnitureLengthField = new JTextField();

        }
        control.add( furnitureNameField );
        control.add( furnitureWidthField );
        control.add( furnitureLengthField );

        if(isDuplicateFurniture) {
            String message = "You cannot add duplicated item. " +
                    "Please make the new one with different name.";
            JOptionPane.showMessageDialog( BuildGui.frame, message );

        } else {
            panel.add( new JLabel("Enter the data of the new item."), BorderLayout.NORTH);
        }
        panel.add(control, BorderLayout.CENTER);
        JOptionPane.showMessageDialog( BuildGui.frame, panel,
                "Add new furniture", JOptionPane.QUESTION_MESSAGE);

        //TODO: Add function to exit from the dialog

        try {
            String inputName = furnitureNameField.getText();
            int inputWidth = Integer.parseInt( furnitureWidthField.getText() );
            int inputLength = Integer.parseInt( furnitureLengthField.getText() );
            tempResult.setFurnitureName( inputName );
            tempResult.setFurnitureWidth( inputWidth );
            tempResult.setFurnitureLength( inputLength );

        } catch (NumberFormatException ex){
            isInputValid = false;
            System.out.println("please input anything");
            ex.printStackTrace();
        }

        return tempResult;
    }

    public static void setFurnitureInputData( FurnitureTempData inputData,
                                             ArrayList<Furniture> itemList, DefaultListModel<String> listModel){

        String inputName = inputData.getFurnitureName();
        int inputWidth = inputData.getFurnitureWidth();
        int inputLength = inputData.getFurnitureLength();

        Furniture inputItem = new Furniture(inputName, inputWidth, inputLength);
        itemList.add(inputItem);

        String newItemTxt = inputName + ": " + inputWidth + " cm x " + inputLength + " cm";
        listModel.addElement(newItemTxt);

    }




}
