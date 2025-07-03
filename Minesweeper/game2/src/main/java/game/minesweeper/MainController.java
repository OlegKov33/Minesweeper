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
    private Text mainsText;

    @FXML
    private MenuItem Beginner;

    @FXML
    private Label difficulty;

    @FXML
    private MenuItem Expert;

    @FXML
    private MenuItem Intermediate;

    @FXML
    private GridPane fieldStage;

    @FXML
    private Button saveButton;

    @FXML
    private TextArea inputCode;

    @FXML
    private AnchorPane anchorPane = new AnchorPane();

    private NewField tempFieldRelated = null;

    //this message that will be shown as soon as you run the program
    @FXML
    void defaultMessage(MouseEvent event) {
        String text = "To start, press or hover over the button to the left of this message to know what they do.";
        mainsText.setText(text);
    }


    //this message that will be shown when you hover over (Play) button
    @FXML
    void showPlayInfo(MouseEvent event){
        String text = "\t\tPLAY\nYour goal is to open all of the boxes without a mine, leaving those with.\n\nOpen boxes with (Left Click)\nIf you wish to load the game, paste the code in the box, select (Load) setting and press play.\n\nTo start select difficulty in (Settings) or press Start";
        mainsText.setText(text);
    }


    //this message that will be shown when you hover over (Quit) button
    @FXML
    void showQuitInfo(MouseEvent event) {
        String text = "\t\tQUIT\nYou are about to close the game.";
        mainsText.setText(text);
    }


    //this button is used to go to main page
    @FXML
    void back(MouseEvent event) throws IOException {
        App.changeScene("mainPage");
    }


    //changes into playing page
    @FXML
    void play(ActionEvent event) throws IOException {
        App.changeScene("playPage");
    }


    //pressing this button will close the game
    @FXML
    void quit(ActionEvent event){
        javafx.application.Platform.exit();
    }


    //saves the game
    @FXML
    void save(ActionEvent event) {
        inputCode.setText(tempFieldRelated.save());
    }


    //NOT TO BE CONFUSED with start button on the main page.
    //this method is called inside gamePage, starts FieldRelated class
    @FXML
    void start(ActionEvent event) {
        saveButton.setDisable(false);
        String saveText = inputCode.getText();
        saveText = saveText.replace("\t", "");


        if(difficulty.getText().length()==12){
            difficulty.setId("Beginner");
            difficulty.setText("Difficulty: Beginner");
        };


        NewField createBoard = new NewField(difficulty.getId(), anchorPane, saveText);
        if(difficulty.getId().contains("Load")){
        }
        else{
        createBoard.start();
        }

        // used for saving
        tempFieldRelated = createBoard;
    }


    //set's difficulty which then will be used by start method above
    @FXML
    void setDifficulty(ActionEvent event){
        difficulty.setText("Difficulty: "+((MenuItem)event.getSource()).getId());
        difficulty.setId(((MenuItem)event.getSource()).getId());
    }

}
//Author OlegKov33