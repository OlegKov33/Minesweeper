package game.minesweeper;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stage;

    
    @Override
    public void start(Stage stage) throws IOException {
        //if you are changing dimentions from 640 x 480, you will change how game layout will be.
        scene = new Scene(loadFXML("mainPage"), 640, 480);
        stage.setScene(scene);
        this.stage = stage;
        stage.show();
    }


    //use this to change between scenes
    public static void changeScene(String fxml)throws IOException{
        scene = new Scene(loadFXML(fxml), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    //locates and loads fxml file
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }


    //starts the game
    public static void main(String[] args) {
        launch();
    }

}
//Author OlegKov33