package game.minesweeper;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class SaveAndLoad {
    //temporary fields used by other class FieldRelated
    private GridPane gridToSave;
    private int[] fieldToSave;
    private byte width;

    //constructor
    public SaveAndLoad(GridPane gameGrid, int[] fieldSize, byte inputWidth) {
        gridToSave = gameGrid;
        fieldToSave = fieldSize;
        width = inputWidth;
    }


    //saves the game based on parameters given above
    public String save(){
        //code is what will be given to user upon saving the game
        String code = "";
        code += fieldToSave.length + " ";
        code += width + " ";

        for(int i = 0; i < fieldToSave.length; i++){
                code += fieldToSave[i] + " ";
        }

        //after you copied the field data, check each button and record opened/closed ones
        for(int i = 0; i < gridToSave.getChildren().size();i++){
            Button tempButton = (Button)gridToSave.getChildren().get(i);
            if(tempButton.isDisable() == true){
                code += "1 ";
            }
            else{
                code += "0 ";
            }
        }
    return code;
    }


    //uses user input to create a field
    public void load(String inputSave){
        
        // try to load the game, if there is a problem, default to errorBackup

        try{
        //reforms the input into a different form
        String[] arr = inputSave.split( " ");
        width = Byte.parseByte( arr[1] );
        fieldToSave = new int[ Integer.parseInt( arr[0].trim() ) ];
        byte height = (byte)( fieldToSave.length/width );


        //starts looping over a new array
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){

                int currentPosition = (i * width) + j;
                fieldToSave[ currentPosition ] = Integer.parseInt(arr[currentPosition + 2].trim());

                //b2 is the button that will be shown and interacted with by the user
                Button b2 = new Button();
                int buttonLocation = currentPosition + 2;
                int buttonValue = Integer.parseInt( arr[buttonLocation + (width * height)] );

                //if the number in the array is 1 then the button has been opened
                if(buttonValue == 1){
                    //if the number is 0, its a blank... hide it for better user experience
                    if(fieldToSave[ currentPosition ] == 0){
                        b2.setText("   ");
                    }
                    //if the button's value is between 1-9 set its number as hints
                    else{
                    b2.setText(fieldToSave[ currentPosition ] + " ");
                    }
                    b2.setDisable(true);
                }else{
                    b2.setText(" . ");
                }
                //set 1d array ID from 2d array (i and j)
                b2.setId( (i*width) + j + "");
                gridToSave.add(b2, j, i);
            }
        }
        }catch( Exception e){
            errorBackup();
            System.out.println("Error ocured while loading the save.");
        }
    }

    //gets the grid pane into FieldRelated class
    public GridPane getGrid() {
        return gridToSave;
    }

    //gets the field into FieldRelated class
    public int[] getField() {
        return fieldToSave;
    }
    
    public byte getWidth(){
        return width;
    }
    private void errorBackup(){
        // if input code is modified e.g. spaces inserted or with characters it will break and default here
        int[] temp = {1, 1, 0, 0, 0, 1, 9, 1, 9, 1, 0, 0, 0, 1, 1, 1, 2, 2, 1, 0, 1, 1, 2, 1, 1, 9, 1, 1, 2, 9, 2, 9, 1, 2, 2, 2, 9, 3, 3, 1, 0, 1, 9, 2, 2, 9, 1, 0, 1, 2, 2, 2, 2, 2, 1, 0, 1, 9, 1, 1, 9, 1, 0, 0};
        fieldToSave = temp;
        //check size
        width = 8;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Button b2 = new Button();
                b2.setText(" . ");
                b2.setId( (i * width) + j +"");
                gridToSave.add(b2, j, i);
            }
        }
    }
}
//Author OlegKov33