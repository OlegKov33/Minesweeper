package game.minesweeper;

import java.util.HashSet;
import java.util.Set;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class NewField {
    private String difficulty;
    private byte mines;
    private byte width;             // in a 1d array, this lets me know the x number of items per row
    private int[] minesLocations;  //location of mines
    private int[] fieldSize;       //field behind GUI below
    private int total = 0;         // the number of cells you need to open before winning
    
    GridPane fieldStage = null; //GUI that uses field above
    
    //Anchor Pane
    @FXML
    private AnchorPane gameStage = null;

    //Status of game, (nothing)(Won)(Lost)
    @FXML
    private Label status;

    //javaFX element that will get shown to user
    @FXML
    private GridPane gridForGame = new GridPane();

    SaveAndLoad innerSaver;
    String gameSaveCode = null;


    //default constructor (difficulty, location of where the mines will be, code that will be used to load game)
    NewField(String getDifficulty, AnchorPane fieldStage, String inputCode){
        difficulty = getDifficulty;
        gameStage = fieldStage;
        gameSaveCode = inputCode;

        switch (difficulty) {
            case "Beginner":
                fieldSize = new int[64];  // size 8 x 8
                width = 8;
                mines = 10;
                break;

            case "Intermediate":
                fieldSize = new int[256]; // size 16 x 16
                width = 16;
                mines = 40;
                break;

            case "Expert":
                fieldSize = new int[480]; // size 30 x 16
                width = 30;
                mines = 99;
                break;

            case "Load":
                load();
                break;
// NOTE! No default case is initialized because the MainController ensures that " if you didn't select a difficulty, you will get beginner"
        }
    }



    public void start() {
        total = fieldSize.length - mines;
        generateField();
        generateMines();
        generateHints();
        syncWithGUI();
    }

    // set all field to 0's (blank)
    private void generateField(){
        for(int i = 0; i < fieldSize.length; i++){
            fieldSize[i] = 0;
        }
    }

    // ensures that the game will have correct number of mines (mines can overlap) as Math.random is used for generation
    private void generateMines(){
        minesLocations = new int[mines];
        int tempMinesLeft = mines;
        Set<Integer> tempSet = new HashSet<Integer>();
        int tempGeneratedValue = (int) Math.floor( Math.random() * fieldSize.length);

        // if you still have mines to generate
        while(tempMinesLeft > 0){
            if(! (tempSet.contains(tempGeneratedValue)) ){

                tempSet.add(tempGeneratedValue);
                minesLocations[ (tempMinesLeft - 1) ] = tempGeneratedValue;
                fieldSize[tempGeneratedValue] = 9;
                tempMinesLeft --;
            }
            tempGeneratedValue = (int) Math.floor( Math.random() * fieldSize.length);
        }
        
    }


    // generates hints around mines
    private void generateHints(){
        int hintLocation;
        for(int mine : minesLocations){
            hintLocation = mine - width;
            if(hintLocation >= 0){

                // above mine
                if(fieldSize[hintLocation] != 9){
                fieldSize[hintLocation] ++;
                }

                // top right of mine
                hintLocation = mine + 1 - width;
                if( ( hintLocation % width ) != 0 && fieldSize[hintLocation] != 9){
                fieldSize[hintLocation] ++;
                }

                // top left of mine
                hintLocation = (mine - width) -1;
                if(hintLocation >= 0 && fieldSize[hintLocation] != 9 && (( hintLocation +1 ) % width) != 0){
                    fieldSize[hintLocation] ++;
                }
            }
            
            // left of mine
            hintLocation = mine - 1;
            if(hintLocation >= 0 && fieldSize[hintLocation] != 9 && (( hintLocation +1 ) % width) != 0){
                fieldSize[hintLocation] ++;
            }

            // ---- ---- ---- ---- //
            // ---- ---- ---- ---- //
            // ---- ---- ---- ---- //

            // below mine
            hintLocation = mine + width;
            if(hintLocation < fieldSize.length){

                if(fieldSize[hintLocation] != 9){
                    fieldSize[hintLocation] ++;
                }

                // bottom left of mine
                hintLocation = mine + width -1;
                if(hintLocation < fieldSize.length && fieldSize[hintLocation] != 9 && (( hintLocation +1 ) % width) != 0){
                    fieldSize[hintLocation] ++;
                }

                // bottom right of mine
                hintLocation = mine + width +1;
                if(hintLocation < fieldSize.length && fieldSize[hintLocation] != 9 && (hintLocation % width) != 0){
                    fieldSize[hintLocation] ++;
                }
            }
            

            // right of mine
            hintLocation = mine + 1;
            if(hintLocation < fieldSize.length && fieldSize[hintLocation] != 9 && (hintLocation % width) != 0){
                fieldSize[hintLocation] ++;
            }
        }
    }

    // saves the game and creates a code in a special window
    public String save() {
        SaveAndLoad saver = new SaveAndLoad(gridForGame, fieldSize, width);
        return saver.save();
    }

    // uses user code to load the existing progress on a game
    public void load(){

        gridForGame.setId("gridForGame");
        
        //Creates instance of save and load class.
        SaveAndLoad saver = new SaveAndLoad(gridForGame, fieldSize, width);
        
        //Loads string into the file.
        saver.load(gameSaveCode);
        fieldSize = saver.getField();
        width = saver.getWidth();

        //sets amount of mines based on field size.
        if( width == 8 )
            mines = 10;
        else if(width == 16 )
            mines=40;
        else
            mines=99;

        minesLocations = new int[mines];
        total = getAccurateTotal()-mines;

        addingButtonFunctionality();
        addMineLocations();
        displayGUI();
    }

    // correctly configures total from load method
    private int getAccurateTotal(){
        int newTotal = 0;
        for(int i = 0; i<gridForGame.getChildren().size();i++){
            Button tempButn = (Button)gridForGame.getChildren().get(i);
            if(tempButn.getText().contains(".")){
                newTotal++;
            }
        }
        return newTotal;
    }

    // adds mines from load method into array of mines
    private void addMineLocations(){
        byte counter = 0;
        for(int i = 0; i < fieldSize.length; i++){
            if(fieldSize[i] == 9){
                minesLocations[counter] = i;
                counter++;
            }
        }
    }

    //creates buttons with unique ID's
    private void syncWithGUI(){
        
        byte height = (byte) ( fieldSize.length/width );
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                
                //creates a button, adds on click event, set's its text to (.) and add ID
                Button b2 = new Button();
                b2.setOnAction(buttonEffect);
                b2.setText(" . ");
                b2.setId( (i * width) + j + "");

                byte value = 15;
                b2.setPrefWidth(value*2);
                b2.setPrefHeight(value);
                gridForGame.add(b2, j, i);
            }
        }

        //show's the field created above
        displayGUI();
    }

    // adds field with buttons to the anchor pane at a specific position
    private void displayGUI() {
        //gets current Pane's width and height
        int value = 3;
        double x = (gameStage.getWidth()-gridForGame.getWidth())/value;
        double y = (gameStage.getHeight()-gridForGame.getHeight())/value;

        //sets field constraints
        gameStage.setRightAnchor(gridForGame, x);
        gameStage.setLeftAnchor(gridForGame, x);
        gameStage.setTopAnchor(gridForGame, y);

        //adding grid (gui) minefield to Pane
        gameStage.getChildren().add(gridForGame);

        // This checks if the is a field already exists, if so. remove the first one
        ObservableList<Node> tempChildren = gameStage.getChildren();
        int number = tempChildren.size()-2;
        if (tempChildren.get(number) instanceof GridPane){
            tempChildren.remove(number);
        }
    }

    // give guess - takes the button that was pressed and attempts to open all the blank cells like the original minesweeper
    private void giveGuess(int buttonsLocationValue){
        //if you guess lands on mine. Game over. Defeat
        if(fieldSize[buttonsLocationValue] == 9){
            lost();
            return;
        }
        //if you opened all the squares. Game over. Victory
        else if(total == 0){
            won();
            return;
        }
        //if neither, try the following
        else{
            upAttempt(buttonsLocationValue);
            downAttempt(buttonsLocationValue);
            leftAttempt(buttonsLocationValue);
            rightAttempt(buttonsLocationValue);
            if(total == 0){
                won();
            }
        }
    }

    private void upAttempt(int buttonsLocationValue) {
        Boolean condition = (buttonsLocationValue - width) >= 0;
        if(condition){
            int newButtonLocation = buttonsLocationValue - width;
            //if you can go up, try the following cell
            Button a  = (Button)gridForGame.getChildren().get( newButtonLocation );
            if(a.getText().contains(".") && fieldSize[newButtonLocation] < 1){
                a.setText("   ");
                a.setDisable(true);
                total--;
                leftAttempt(newButtonLocation);
                rightAttempt(newButtonLocation);
                upAttempt(newButtonLocation);
            }
            else if(a.getText().contains(".")
                    && fieldSize[newButtonLocation] < 9
                    && fieldSize[buttonsLocationValue] == 0){
                a.setText(" "+fieldSize[newButtonLocation]);
                a.setDisable(true);
                total--;
                return;
            }
        }
    }

    private void rightAttempt(int buttonsLocationValue) {
        Boolean condition = (buttonsLocationValue + 1) < fieldSize.length;
        if(condition && ( (buttonsLocationValue + 1) % width) != 0 ){
            int newButtonLocation = buttonsLocationValue + 1;
            //if you can go right, try the following cell
            Button a  = (Button)gridForGame.getChildren().get( newButtonLocation );
            if(a.getText().contains(".") && fieldSize[newButtonLocation] < 1){
                a.setText("   ");
                a.setDisable(true);
                total--;
                downAttempt(newButtonLocation);
                upAttempt(newButtonLocation);
                rightAttempt(newButtonLocation);
                
            }
            else if(a.getText().contains(".")
                    && fieldSize[newButtonLocation] < 9
                    && fieldSize[buttonsLocationValue] == 0){
                a.setText(" "+fieldSize[newButtonLocation]);
                a.setDisable(true);
                total--;
                return;
            }
        }
    }

    private void leftAttempt(int buttonsLocationValue) {
        Boolean condition = (buttonsLocationValue - 1) >= 0;

        if(condition && ( (buttonsLocationValue - 1 ) % width+1) != width ){
            int newButtonLocation = buttonsLocationValue - 1;
            //if you can go left, try the following cell
            Button a  = (Button)gridForGame.getChildren().get( newButtonLocation );
            if(a.getText().contains(".") && fieldSize[newButtonLocation] < 1){
                a.setText("   ");
                a.setDisable(true);
                total--;
                downAttempt(newButtonLocation);
                leftAttempt(newButtonLocation);
                upAttempt(newButtonLocation);
            }
            
            else if(a.getText().contains(".")
                    && fieldSize[newButtonLocation] < 9
                    && fieldSize[buttonsLocationValue] == 0
                    && ( (buttonsLocationValue - 1 ) % width+1) != 0 ){
                a.setText(" "+fieldSize[newButtonLocation]);
                a.setDisable(true);
                total--;
                return;
            }
        }
    }

    private void downAttempt(int buttonsLocationValue) {
        Boolean condition = (buttonsLocationValue + width) < fieldSize.length;
        if(condition){
            int newButtonLocation = buttonsLocationValue + width;
            //if you can go down, try the following cell
            Button a  = (Button)gridForGame.getChildren().get( newButtonLocation );
            if(a.getText().contains(".") && fieldSize[newButtonLocation] < 1){
                a.setText("   ");
                a.setDisable(true);
                total--;
                downAttempt(newButtonLocation);
                leftAttempt(newButtonLocation);
                rightAttempt(newButtonLocation);
                
            }
            else if(a.getText().contains(".")
                    && fieldSize[newButtonLocation] < 9
                    && fieldSize[buttonsLocationValue] == 0){
                a.setText(" "+fieldSize[newButtonLocation]);
                a.setDisable(true);
                total--;
                return;
            }
        }
    }

    //generates button functionality to buttons that were loaded (it is used here as only here I declared buttonEffect behavior)
    private void addingButtonFunctionality() {
        for(int i = 0; i<gridForGame.getChildren().size();i++){
            //get a button
            Button b1 = (Button) gridForGame.getChildren().get(i);
            //gets a button and adds on action event to it
            b1.setOnAction(buttonEffect);
        }
    }

    // won condition, triggers when you open all the cells without a mine
    private void won() {
        Label st = (Label)gameStage.lookup("#status");
        st.setText("Congratulations!\nYou Win!");
        //disable all mines by putting flag on all UNOPED MINES.
        //🚩
        for(int i = 0; i < minesLocations.length; i++){
            Button disarmed = (Button)gridForGame.getChildren().get(minesLocations[i]);
            disarmed.setText("🚩");
            disarmed.setDisable(true);
        }
    }

    // lost condition, triggers when you open a mine.
    private void lost() {
        Label st = (Label)gameStage.lookup("#status");
        st.setText("You blew up.");
        //loop for all cells. x,y. disable.
        //loop again for all mines to highlight
        //💥

        for(int i = 0; i < fieldSize.length; i++){
                Button newField = (Button)gridForGame.getChildren().get(i);
                newField.setDisable(true);
        }
        for(int i = 0; i < minesLocations.length; i++){
            Button newField = (Button)gridForGame.getChildren().get(minesLocations[i]);
            newField.setText("💥");
        }
    }


    // this is where the buttons get their functionality
    final EventHandler<ActionEvent> buttonEffect = new EventHandler<ActionEvent>()
{
        @Override
        public void handle(ActionEvent arg0)
        {
            //finds which button was pressed
            Button tempButton = (Button)arg0.getSource();
            tempButton.setDisable(true);
            int buttonLocation = Integer.parseInt(tempButton.getId());

            if(fieldSize[buttonLocation]>=1){
                tempButton.setText(" "+fieldSize[buttonLocation]);
            }
            else{
                tempButton.setText("   "); // otherwise, set it no clear space
            }
            total--;
            giveGuess(buttonLocation);
        }
    };

    // THIS METHOD WAS USED FOR TESTING, CONSIDER USING IT IF YOU WISH TO SEE THE FIELD WITHOUT OBSTRUCTIONS
    private void TESTINGFIELD(){
        String output = "";
        int height = fieldSize.length/width;
        System.out.println("Heght is = "+ height+"\nWidth is = "+width);
        
        for(int i = 0; i < height ; i++){
            for(int j = 0; j < width; j++ ){
                output += fieldSize[(i*width)+j ]+ " ";
            }
            output +="\n";
        }
        System.out.println(output);
    }
}

//Author OlegKov33