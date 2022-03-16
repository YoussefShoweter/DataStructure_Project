package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        primaryStage.setTitle("Sorts App");
        primaryStage.getIcons().add(new Image("/1.png"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    int step = 0;

    public static void main(String[] args) {
        launch();


    }}