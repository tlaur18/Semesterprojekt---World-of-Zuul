package worldofzuulIO;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws InterruptedException {
        launch(args);
    }
    
    public void restart() throws Exception {
        start(new Stage());
    }
}
