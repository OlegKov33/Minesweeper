package game.minesweeper;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
public class MainController {
    
    @FXML
    private Text MainInfo;

    @FXML
    private MenuItem Beginner;

    @FXML
    private Label Difficulty;

    @FXML
    private MenuItem Expert;

    @FXML
    private MenuItem Intermediate;

    @FXML
    private GridPane fieldStage;

    @FXML
    private Button SaveBtn;

    @FXML
    private TextArea STRINGCODE;

    @FXML
    private AnchorPane Pane = new AnchorPane();
    
    private FieldRelated tempFieldRelated = null;

    //the message that will be shown as soon as you run the program
    @FXML
    void defaultMessage(MouseEvent event) {
        String text = "To start, press or hover over the button to the left of this message to know what they do.";
        MainInfo.setText(text);
    }


    //the message that will be shown when you hover over (Play) button
    @FXML
    void showPlayInfo(MouseEvent event){
        String text = "\t\tPLAY\nYour goal is to open all of the boxes without a mine, leaving those with.\n\nOpen boxes with (Left Click)\nIf you wish to load the game, paste the code in the box, select (Load) setting and press play.\n\nTo start select difficulty in (Settings) or press Play";
        MainInfo.setText(text);
    }


    //the message that will be shown when you hover over (Quit) button
    @FXML
    void showQuitInfo(MouseEvent event) {
        String text = "\t\tQUIT\nYou are about to close the game.";
        MainInfo.setText(text);
    }


    //this button is used to go to main page
    @FXML
    void Back(MouseEvent event) throws IOException {
        App.changeScene("mainPage");
    }


    //changes into playing page
    @FXML
    void Play(ActionEvent event) throws IOException {
        App.changeScene("playPage");
    }


    //pressing this button will close the game
    @FXML
    void Quit(ActionEvent event){
        javafx.application.Platform.exit();
    }


    //saves the game
    @FXML
    void Save(ActionEvent event) {
        STRINGCODE.setText(tempFieldRelated.Save());
    }


    //NOT TO BE CONFUSED with start button on the main page.
    //this method is called inside gamePage, starts FieldRelated class
    @FXML
    void Start(ActionEvent event) {
        SaveBtn.setDisable(false);
        String test = STRINGCODE.getText();
        test = test.replace("\t", "");

        if(Difficulty.getText().length()==12){
            Difficulty.setId("Beginner");
            Difficulty.setText("Difficulty: Beginner");

        };
        
        FieldRelated createBoard = new FieldRelated(Difficulty.getId(), Pane, test);
        if(Difficulty.getId().contains("Load")){
        }
        else{
        createBoard.start();
        }


        tempFieldRelated = createBoard;
        Button a = (Button)event.getSource();
        a.setDisable(true);
    }


    //set's difficulty which then will be used by start method above
    @FXML
    void setDifficulty(ActionEvent event){
        Difficulty.setText("Difficulty: "+((MenuItem)event.getSource()).getId());
        Difficulty.setId(((MenuItem)event.getSource()).getId());
    }

}
//Author OlegKov33