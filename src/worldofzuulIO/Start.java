/**
 *
 * The Start class contains the main method which is run when the programme is launched.
 *
 * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen, Thomas S. Laursen
 * @version 2018.12.14
 *
 */
package worldofzuulIO;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application {

    /*
    * The start method is overridden from the Application class.
    * The scene graph is loaded from the fxml file and added to a scene.
    * The scene is added to a stage.
    * The stage is then drawn on the screen.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent startMenuRoot = FXMLLoader.load(getClass().getResource("startmenu.fxml"));

        Scene scene = new Scene(startMenuRoot);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /*
    * The main method calls the method 'launch'. This launches the javafx application.
     */
    public static void main(String[] args) throws InterruptedException {
        launch(args);
    }
}
