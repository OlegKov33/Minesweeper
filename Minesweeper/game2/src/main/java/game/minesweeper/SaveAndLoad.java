package game.minesweeper;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class SaveAndLoad {
    //temporary fields used by other class FieldRelated
    GridPane gridToSave = null;
    int[][] fieldToSave = null;

    //constructor
    public SaveAndLoad(GridPane gridForGame, int[][] fieldSize) {
        gridToSave = gridForGame;
        fieldToSave = fieldSize;
    }


    //saves the game based on parameters given above
    public String Save(){
        //code is what will be given to user upon saving the game
        String code = "";

        //checks the field size. if its less than 9, add 0. more? use that number instead.
        if(fieldToSave.length<10){
            code+="0"+fieldToSave.length;
            }
        else{
            code+=fieldToSave.length;
        }
        code+=" ";

        if(fieldToSave[0].length<10){
            code+="0"+fieldToSave[0].length;
        }
        else{
            code+=fieldToSave[0].length;
        }
        code+=" ";


        for(int i = 0;i<fieldToSave.length;i++){
            for(int j = 0;j< fieldToSave[0].length;j++){
                code+=fieldToSave[i][j];
                code+=" ";
            }
        }
    
        //after you copied the field data, check each button and record opened/closed ones
        for(int i = 0; i<gridToSave.getChildren().size();i++){
            Button tempButton = (Button)gridToSave.getChildren().get(i);
            if(tempButton.isDisable()==true){
                code+="1 ";
            }
            else{
                code+="0 ";
            }
        }
    return code;
//method of getting a code. END
    }


    //uses user input to create a field
    public void Load(String inputSave){
        
    //reforms the input into a different form
    String input = inputSave;
    String[] arr = input.split( " ");
    this.fieldToSave = new int[Integer.parseInt(arr[0])][Integer.parseInt(arr[1])];

    //starts looping over a new array
    for(int i=0;i<fieldToSave.length;i++){
        for(int j = 0; j<fieldToSave[0].length;j++){

            //uses math to find 1d coordinate using 2d points
            fieldToSave[i][j] = Integer.parseInt((String)arr[(fieldToSave[0].length*i+j)+2].trim());
            int calcualtion = fieldToSave[0].length*i+j;// easier to read //somehow in expert goes: 16,17, 32...
            Button b2 = new Button();
            int test5 = Integer.parseInt(arr[((arr.length-2)/2)+calcualtion+2].trim());

            //if the number in the array is 1 then the button has been opened
            if(test5 ==1){
                //if the number is 0, its a blank... hide it for better user experience
                if(fieldToSave[i][j]==0){
                    b2.setText("   ");
                }
                
                //if the button is between 1-9 set its number as hints
                else{
                b2.setText(fieldToSave[i][j]+" ");
                }
                b2.setDisable(true);
            }


            //if the condition is not 1, then the button hasn't been opend
            else{
                b2.setText(" . ");
            }
            //set 1d array ID from 2d array (i and j)
            b2.setId(calcualtion+"");
            gridToSave.add(b2, j, i);
        }
    }

    //used for testing, remove it if you wish FROM HERE.
    String output = "";
    for(int i = 0; i<fieldToSave.length;i++){
        for(int j = 0; j<fieldToSave[0].length;j++){
            output+=fieldToSave[i][j]+" ";
        }
        output+="\n";
    }
    System.out.println(output);
    //used for testing, remove it if you wish UNTI HERE.
    }

    //gets the grid pane into FieldRelated class
    public GridPane getGrid() {
        return gridToSave;
    }

    //gets the field into FieldRelated class
    public int[][] getField() {
        return fieldToSave;
    }
    
    
}
//Author OlegKov33