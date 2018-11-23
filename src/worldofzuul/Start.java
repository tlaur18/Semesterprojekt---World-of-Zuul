package worldofzuul;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("noget");
        

        Parent root = FXMLLoader.load(getClass().getResource("StartPage.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        Game game = new Game();
       

    }

    public static void main(String[] args) throws InterruptedException {
        launch(args);

    }

}
