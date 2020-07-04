package client.sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Clients extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/sample.fxml"));
        primaryStage.setTitle("Заказы и склады");
        primaryStage.setScene(new Scene(root,900,500));
        primaryStage.setOnCloseRequest(event -> Platform.exit());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}