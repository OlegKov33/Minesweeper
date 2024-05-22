package game.minesweeper;

//IMPORTS
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class FieldRelated {
//Fieldsize - ACTUAL CODE BEHIND GUI
//Createpane - GUI
    String Difficulty = null;
    int mines=0;
    private int Fx = 0; //field max x
    private int Fy = 0; //field max y

    int[][] mineLocations = null;//location of mines
    int[][] fieldSize = null; //field behind GUI below

    GridPane fieldStage = null; //GUI that uses field above
    private int total = 0; // the number of cells you need to open before winning


    //Anchor Pane
    @FXML
    private AnchorPane Pane = null;

    //Status of game, (nothing)(Won)(Lost)
    @FXML
    private Label status;

    //javaFX element that will get shown to user
    @FXML
    private GridPane gridForGame = new GridPane();

    //String that user will enter when pressing "Load"
    String CodeForGame = null;

    SaveAndLoad tempSaver;


    FieldRelated(String getDifficulty, AnchorPane fieldStage, String test){
        
        this.Difficulty = getDifficulty;
        this.Pane = fieldStage;
        this.CodeForGame = test;
        
        //checks what user has entered.
        if(Difficulty.equals("Beginner")){
            this.fieldSize = new int[8][8];
            this.mines = 10;
        }
        else if(Difficulty.equals("Intermidiate")){
            this.fieldSize = new int[16][16];
            this.mines = 40;
            
        }
        else if(Difficulty.equals("Expert")){
            this.fieldSize = new int[30][16];
            this.mines = 90;
        }
        else if(Difficulty.equals("Load")){
            Load();
        }
        
    }


    //Saves the current field state
    public String Save(){
        SaveAndLoad saver = new SaveAndLoad(gridForGame, fieldSize);
        tempSaver = saver;
        return saver.Save(); 
        //also replace the textarea with this^^

    }

    //Loads the game with code that you have saved
    public void Load(){
        gridForGame.setId("gridForGame");
        
        //Creates instance of save and load class.
        SaveAndLoad saver = new SaveAndLoad(gridForGame, fieldSize);
        tempSaver = saver;
        
        //you can use the strings below and see what happens.
        //String test = "08 08 9 1 0 0 0 1 2 9 2 2 1 0 0 2 9 3 1 9 2 1 1 2 9 3 1 1 2 9 1 1 2 9 1 1 2 1 1 0 1 1 2 9 2 0 0 1 1 1 2 9 2 0 0 1 9 1 1 1 1 0 0 1 1 1 0 1 1 1 1 1 0 0 0 0 1 1 1 1 0 0 0 0 0 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
        //String test = "16 16 0 0 0 1 9 2 1 1 0 0 0 1 1 1 0 0 0 0 0 1 2 3 9 1 0 0 1 3 9 2 0 0 1 1 1 0 1 9 3 2 0 0 1 9 9 3 1 1 1 9 1 0 1 2 9 1 1 1 2 2 3 3 9 1 1 1 1 0 0 1 2 2 2 9 1 1 2 9 2 1 0 0 0 0 0 1 3 9 3 1 1 1 9 2 2 1 2 2 1 0 1 3 9 9 2 0 0 1 1 2 3 9 9 9 1 0 1 9 9 5 3 1 0 0 0 1 9 9 3 3 1 0 1 2 4 9 9 1 0 0 0 1 3 9 9 1 0 0 0 0 2 9 3 1 0 0 1 1 2 1 1 1 0 0 0 0 1 1 1 0 0 0 1 9 1 0 0 0 0 0 0 0 0 0 0 1 2 2 2 1 1 0 0 0 0 0 0 0 0 1 1 2 9 9 1 0 0 0 0 0 0 1 2 2 1 2 9 3 3 3 2 1 1 1 1 1 1 2 9 9 2 4 9 3 1 9 1 1 9 1 1 9 1 2 9 3 2 9 9 2 1 1 1 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
        
        String test = (String)CodeForGame;

        //Loads string into the file.
        saver.Load(test);
        this.fieldSize = saver.getField();
        this.gridForGame = saver.getGrid();
        
        Fx = fieldSize.length;
        Fy = fieldSize[0].length;

        //sets ammount of mines based on field size.
        if(Fx==8){mines = 10;}
        else if(Fx==16){mines=40;}
        else{mines=90;}

        //initialized but not used... yet
        this.mineLocations = new int[mines][mines];
        //total = (Fx*Fy) -mines;
        total = getAccurateTotal()-mines;

        //adds functionality on click for buttons
        addingButtonFunctionality();

        //adds mines location
        addMineLocation();
        // testing purpose ONLY
        //showField();

        displayGUI();
        //find mines here
    }
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

    private void addMineLocation(){
        int counter = 0;
        for(int i = 0;i<fieldSize.length;i++){
            for(int j=0; j<fieldSize[0].length;j++){
                if(fieldSize[i][j]==9){
                    mineLocations[counter][0] = i;
                    mineLocations[counter][1]=j;
                    counter++;
                }
            }
        }
    }

    private void displayGUI() {
        //gets current Pane's width and height
        double x = (Pane.getWidth()-gridForGame.getWidth())/3;
        double y = (Pane.getHeight()-gridForGame.getHeight())/3;

        //sets field constraints
        Pane.setRightAnchor(gridForGame, x);
        Pane.setLeftAnchor(gridForGame, x);
        Pane.setTopAnchor(gridForGame, y);

        //adding grid (gui) minefield to Pane
        Pane.getChildren().add(gridForGame);
    }


    private void addingButtonFunctionality() {
        for(int i = 0; i<gridForGame.getChildren().size();i++){
            //get a button
            Button b1 = (Button) gridForGame.getChildren().get(i);
            //gets a button and adds on action event to it
            b1.setOnAction(buttonEffect);
        }
    }

    //unlike load, this creates the field, mines from scratch
    public void start(){

        Fx = fieldSize.length;
        Fy = fieldSize[0].length;
        
        //creates array in which all the mine locations will be stored at
        this.mineLocations = new int[mines][mines];

        //the ammount of cells you need to open to win
        total = (Fx*Fy) -mines;


        //creates an empty field with 0's
        generateField();

        //randomly assigns (9)(mine symbol) to a field cell
        generateMines();

        //generates numbers arround each mine so its possible to solve the game
        generateHints();

        //TODO testing purpose only.
        showField();

        //uses field to create GUI
        syncWithGUI();
    }
    
    //the event that will happen upon pressing a button
    final EventHandler<ActionEvent> buttonEffect = new EventHandler<ActionEvent>()
    {
        @Override
        public void handle(ActionEvent arg0)
        {
            //finds which button was pressed
            Button tempButton = (Button)arg0.getSource();

            tempButton.setDisable(true);// TODO THIS DISABLES BUTTONS!

            //find out where the button is on 2d array using 1d array and formulas below
            double x = Integer.parseInt(tempButton.getId());
            int y = (int)(((x/Fx)-(int)Math.floor(x/Fx))*Fx);

            //if the button pressed is between 1-9 set its value to fields value
            if(fieldSize[(int)Math.floor(x/Fx)][y]>=1){
            tempButton.setText(" "+fieldSize
                [(int)Math.floor(x/Fx)] //gets X
                [y]//gets Y
                );}
            else{
                tempButton.setText("   "); // otherwise, set it no clear space
            }
            total--;

            //attempt to open other cells arround pressed button that are blank (0)
            giveGuess((int)Math.floor(x/Fx), (int)(((x/Fx)-(int)Math.floor(x/Fx))*Fx));
        }
    };


    //Testing to see if you show your field correctly (field) NOT gui wise
    private void showField(){
        String output="";
        for(int i=0;i<Fx;i++){
            for(int j=0;j<Fy;j++){
                output+=fieldSize[i][j]+" ";
            }
            output+="\n";
        }
        System.out.println(output);
    }


    // generates mines (9) - is the mine symbol
    private void generateMines(){
        //start a while loop and randomly try and find a place for a mine
        while(mines>0){
            int a = (int) (Math.random()*Fx); //x coordinate based on max field length
            int b = (int) (Math.random()*Fy); //y coordinate based on max field[0] length
            
            if(this.fieldSize[a][b] != 9){
                this.fieldSize[a][b]=9;
                mines--;
                this.mineLocations[mines][0]=a;
                this.mineLocations[mines][1]=b;
            }
        }
    }

    //fills the fieldSize(field behind GUI) with 0's (blanks)
    private void generateField(){
        for(int i=0; i<Fx;i++){
            for(int j=0; j<Fy;j++){
                this.fieldSize[i][j]=0;
            }
        }
    }


    //arround each mine place a number
    private void generateHints(){
                    //mineLocations[0] Mine number 1
                    //mineLocations[0][0] cordinate x
                    //mineLocations[0][1] coordinate y

        for(int i=0;i<=mineLocations.length-1;i++){ //for each mine do the following:
            //check if the mine can go up+1 in x coordinate
            //This checks if the x can go up by 1
            if(mineLocations[i][0]+1 <=Fx-1){
                
                //if the value is not 9, add 1 (x+1)
                if(fieldSize[mineLocations[i][0]+1][mineLocations[i][1]] != 9){
                    fieldSize[mineLocations[i][0]+1][mineLocations[i][1]]+=1; // SETS X+1 value
                    
                }

                //try x+1, y+1
                if(mineLocations[i][1]+1 <= Fy-1){
                    if(fieldSize[mineLocations[i][0]+1][mineLocations[i][1]+1] != 9){
                        fieldSize[mineLocations[i][0]+1][mineLocations[i][1]+1]+=1; //SETS X+1 Y+1
                            
                    }
                }

                //try x+1, y-1
                if(mineLocations[i][1]-1 >= 0){
                    if(fieldSize[mineLocations[i][0]+1][mineLocations[i][1]-1] != 9){
                        fieldSize[mineLocations[i][0]+1][mineLocations[i][1]-1]+=1; //SETS X+1 Y-1
                    }
                }
            }
            // FOR X+1 loop end.


            //Check if Y can go up by 1
            if(mineLocations[i][1]+1 <=Fy-1){
                if(fieldSize[mineLocations[i][0]][mineLocations[i][1]+1] != 9){
                    fieldSize[mineLocations[i][0]][mineLocations[i][1]+1]+=1; //SETS Y+1
                }

                //try x-1, y+1
                if(mineLocations[i][0]-1 >=0 ){
                    if(fieldSize[mineLocations[i][0]-1][mineLocations[i][1]+1] != 9){
                        fieldSize[mineLocations[i][0]-1][mineLocations[i][1]+1]+=1; //SETS X-1 Y+1
                    }
                }
            }

            //try x-1, y
            if(mineLocations[i][0]-1 >=0 ){
                if(fieldSize[mineLocations[i][0]-1][mineLocations[i][1]] != 9){
                    fieldSize[mineLocations[i][0]-1][mineLocations[i][1]]+=1; //SETS X-1 Y0
                }
            }
            
            //try x-1, y-1
            if(mineLocations[i][1]-1 >=0 && mineLocations[i][0]<=Fx){
                if(fieldSize[mineLocations[i][0]][mineLocations[i][1]-1] != 9){
                    fieldSize[mineLocations[i][0]][mineLocations[i][1]-1]+=1; //SETS Y+1
                }
            }

            //try x, y-1
            if(mineLocations[i][1]-1 >=0 && mineLocations[i][0]-1>=0){
                if(fieldSize[mineLocations[i][0]-1][mineLocations[i][1]-1] != 9){
                    fieldSize[mineLocations[i][0]-1][mineLocations[i][1]-1]+=1; //SETS Y+1
                }
            }
        }
    }


    //uses field information and creates a GUI based on that
    private void syncWithGUI(){
        
        for(int i=0; i<Fx;i++){
            for(int j=0; j<Fy;j++){
                
                //creates a button, adds on click event, set's its text to (.) and add ID
                Button b2 = new Button();
                b2.setOnAction(buttonEffect);
                b2.setText(" . ");
                b2.setId((i*Fx)+j+"");
                gridForGame.add(b2, j, i);
            }
        }

        //show's the field created above
        displayGUI();
    }


    //using the buttons x, y coordinates, look recursively arround it for blank spaces and open first number it sees
    public void giveGuess(int x, int y){

        //if you guess lands on mine. Game over. Defeat
        if(fieldSize[x][y]==9){
            lost();
            return;
            
        }
        //if you opened all the squares. Game over. Victory
        else if(total==0){
            won();
            return;
        }
        //if neither, try the following
        else{
            upAttempt(x, y);
            downAttempt(x, y);
            leftAttempt(x, y);
            rightAttempt(x, y);
        }
    }


    // upon winning, mark all mines with flag emoji and display message
    private void won() {
        Label st = (Label)Pane.lookup("#status");
        st.setText("Congratulations!\nYou Win!");
        //disable all mines by putting flag on all UNOPED MINES.
        //🚩
        for(int i = 0;i<mineLocations.length;i++){
            Button disarmed = (Button)gridForGame.getChildren().get((int)(mineLocations[i][0]*Fx)+mineLocations[i][1]);
            disarmed.setText("🚩");
            disarmed.setDisable(true);
        }
    }


    //upon loosing, disable all cells and change mines into firework emoji
    private void lost() {
        Label st = (Label)Pane.lookup("#status");
        st.setText("You blew up.");
        //loop for all cells. x,y. disable.
        //loop again for all mines to highlight
        //💥

        for(int i = 0;i<Fx*Fy;i++){
                Button newField = (Button)gridForGame.getChildren().get(i);
                newField.setDisable(true);
        }
        for(int i = 0;i<mineLocations.length;i++){
            Button newField = (Button)gridForGame.getChildren().get((int)(mineLocations[i][0]*Fy)+mineLocations[i][1]);
            newField.setText("💥");
        }
    }


    //try to go left from the pressed button
    private void leftAttempt(int x, int y){
        //LEFT CAN ONLY CALL UP AND DOWN
        Boolean condition = y-1>=0;

       //if you can go up, try the following:
        if(condition){
            Button a  = (Button)gridForGame.getChildren().get((x)*Fy+y-1);
            
            //if you find closed cell
            if(a.getText().contains(".") && fieldSize[x][y-1]<1){
                a.setText("   ");
                a.setDisable(true);
                y-=1;
                total--;
                leftAttempt(x, y);
                downAttempt(x,y);
                upAttempt(x, y);
            }
            //if not, check if the cell next to it can be opened and is not a mine
            else if(a.getText().contains(".") && fieldSize[x][y-1] <9 && fieldSize[x][y]==0){
                a.setText(" "+fieldSize[x][y-1]);
                a.setDisable(true);
                total--;
                return;
            }
        }
    }

    //try to go to right from pressed button
    private void rightAttempt(int x, int y){
        //RIGHT CAN ONLY CALL UP AND DOWN
        Boolean condition = y+1<=Fy-1;
       //if you can go up, try the following:
        if(condition){
            //if you can go left, try to open a cell if its not a mine (9)
            Button a  = (Button)gridForGame.getChildren().get((x)*Fy+y+1);
            if(a.getText().contains(".") && fieldSize[x][y+1]<1){ //incorrent logic ;-;
                a.setText("   ");
                a.setDisable(true);
                y+=1;
                total--;
                rightAttempt(x, y);
                downAttempt(x,y);
                upAttempt(x, y);
            }

            //if you can't go left, try and open a cell
            else if(a.getText().contains(".") && fieldSize[x][y+1] <9 && fieldSize[x][y]==0){
                a.setText(" "+fieldSize[x][y+1]);
                a.setDisable(true);
                total--;
                return;
            }
        }
    }

    private void upAttempt(int x, int y){
        //UP CAN ONLY CALL LEFT AND RIGHT
        Boolean condition = x+1<=Fx-1;
       //if you can go up, try the following:
        if(condition){
            //if you can go up, try the following cell
            Button a  = (Button)gridForGame.getChildren().get((x+1)*Fy+y);
            if(a.getText().contains(".") && fieldSize[x+1][y]<1){ //incorrent logic ;-;
                a.setText("   ");
                a.setDisable(true);
                x+=1;
                total--;
                leftAttempt(x, y);
                rightAttempt(x,y);
                upAttempt(x, y);
            }

            else if(a.getText().contains(".") && fieldSize[x+1][y] <9 && fieldSize[x][y]==0){
                a.setText(" "+fieldSize[x+1][y]);
                a.setDisable(true);
                total--;
                return;
            }
        }
    }
        
    private void downAttempt(int x, int y){
        //DOWN CAN ONLY CALL LEFT AND RIGHT
        Boolean condition = x-1>=0;
           //if you can go up, try the following:
        if(condition){
            Button a  = (Button)gridForGame.getChildren().get((x-1)*Fy+y);// cahnged this to fy
            if(a.getText().contains(".") && fieldSize[x-1][y]<1){ //incorrent logic ;-;
                a.setText("   ");
                a.setDisable(true);
                x-=1;
                total--;
                leftAttempt(x, y);
                rightAttempt(x,y);
                downAttempt(x, y);
                }

                else if(a.getText().contains(".") && fieldSize[x-1][y] <9 && fieldSize[x][y]==0){
                    a.setText(" "+fieldSize[x-1][y]);
                    a.setDisable(true);
                    total--;
                    return;
                }
        }
    }
}
//Author OlegKov33
