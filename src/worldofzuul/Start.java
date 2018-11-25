package worldofzuul;

import worldofzuulIO.TextIO;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Start extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Ny TextArea
        TextArea txtAreaOutput = new TextArea();
        txtAreaOutput.setPadding(new Insets(10, 10, 10, 10));
        txtAreaOutput.setEditable(false);
        
        TextIO textIO = new TextIO(new Game(), txtAreaOutput);

        //Ny textField
        TextField txtFieldInput = new TextField();

        //Ny label der siger hvilket rum man befinder sig i.
        Label lblCurrentRoom = new Label();
        lblCurrentRoom.setFont(new Font("Calibri", 30));
        lblCurrentRoom.setText("Bedroom");
        lblCurrentRoom.setVisible(false);

        //Ny Button der fungerer som input-knap
        Button btnInput = new Button();
        btnInput.setText("Input");
//        btnInput.setOnAction(new btnInputActionEventHandler(game, txtFieldInput, lblCurrentRoom, txtAreaOutput));

        //Ny Button der starter spillet
        Button btnStart = new Button();
        btnStart.setText("Start");
        btnStart.setOnAction(new btnInputActionEventHandler(textIO, lblCurrentRoom, btnStart));

        //HBox oprettes og TextField og Button sættes ind.
        HBox hbBottom = new HBox();
        hbBottom.setPadding(new Insets(10, 10, 10, 10));
        hbBottom.setSpacing(10);
        hbBottom.getChildren().add(txtFieldInput);
        HBox.setHgrow(txtFieldInput, Priority.ALWAYS);

        hbBottom.getChildren().add(btnInput);

        BorderPane root = new BorderPane();
        root.setBottom(hbBottom);
        root.setTop(txtAreaOutput);
        root.setCenter(lblCurrentRoom);
        root.setCenter(btnStart);

        Scene scene = new Scene(root, 600, 500);

        primaryStage.setTitle("Fire Escape");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws InterruptedException {
        launch(args);
    }

}
