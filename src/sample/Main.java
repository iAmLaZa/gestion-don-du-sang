package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BD.c();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Login ");
        primaryStage.setScene(new Scene(root, 458, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
