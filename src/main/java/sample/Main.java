package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main function of the entire program
 */
public class Main extends Application {
    /**
     * Default constructor of the Main class
     */
    public Main() {
    }

    /**
     * Instantiate myController, to "Start" the game
     * 
     * @param primaryStage main stage of the Game representing all of the GUI
     *                     elements
     * @throws Exception javaFx game start exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Tower Defense");
        primaryStage.setScene(new Scene(root, 600, 480));
        primaryStage.show();
        MyController appController = (MyController) loader.getController();
        appController.createArena();
    }

    /**
     * Start point of the program
     * 
     * @param args arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
