package MYGEwithGUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FurnitureDialog {

    static boolean isDuplicateFurniture = false;
    static String[] result = new String[3];
    static ArrayList<Furniture> itemList = Furniture.getFurnitureArrayList();

    /**
     * This static inputFurnitureData method aims to set the new item in the ArrayList<Furniture>.
     * The data of the new item is sent from input dialog.
     */
    public static void inputFurnitureData(DefaultListModel<String> listModel){

        do{
            result = inputFurnitureDialog();
        } while (isDuplicateFurniture);

        String inputName;
        int inputWidth, inputLength;

        try {
            inputName = result[0];
            inputWidth = Integer.parseInt(result[1]);
            inputLength = Integer.parseInt(result[2]);

            Furniture inputItem = new Furniture(inputName, inputWidth, inputLength);
            itemList.add(inputItem);

            String newItemTxt = inputName + ": " + inputWidth + " cm x " + inputLength + " cm";
            listModel.addElement(newItemTxt);
        }
        catch (NumberFormatException numEx){
            numEx.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Please enter the valid data. Width and length of the furniture should be number.");
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
    public static String[] inputFurnitureDialog(){

        if( isDuplicateFurniture ){
            //TODO: find more sophisticated way!.
            result = new String[3];
        }

        JPanel panel = new JPanel(new BorderLayout(5,5));

        JPanel label = new JPanel(new GridLayout(0,1,2,2));
        label.add( new JLabel("Name", SwingConstants.RIGHT) );
        label.add( new JLabel("Width (cm)", SwingConstants.RIGHT) );
        label.add( new JLabel("Length (cm)", SwingConstants.RIGHT) );
        panel.add( label, BorderLayout.WEST );

        JPanel control = new JPanel(new GridLayout(0,1,2,2));
        JTextField furnitureNameField = new JTextField();
        control.add( furnitureNameField );
        JTextField furnitureWidthField = new JTextField();
        control.add(furnitureWidthField);
        JTextField furnitureLengthField = new JTextField();
        control.add(furnitureLengthField);

        if(isDuplicateFurniture) {
            panel.add( new JLabel("You cannot add duplicated item. " +
                    "Please make the new one with different name."), BorderLayout.NORTH );
        } else {
            panel.add( new JLabel("Enter the data of the new item."), BorderLayout.NORTH);
        }
        panel.add(control, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(null, panel,
                "Add new furniture", JOptionPane.QUESTION_MESSAGE);

        String inputFurnitureName = furnitureNameField.getText();
        String[] resultArray = new String[3];
        resultArray[0] = inputFurnitureName;
        resultArray[1] = furnitureWidthField.getText();
        resultArray[2] = furnitureLengthField.getText();

        for (Furniture newItem: itemList){
            if(newItem.getName().equals(inputFurnitureName)){
                isDuplicateFurniture = true;
                break;
            } else {
                isDuplicateFurniture = false;
            }
        }
        return resultArray;
    }

}
