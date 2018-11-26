package worldofzuul;

import worldofzuulIO.TextIO;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
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
        
        //Ny label der siger hvilket rum man befinder sig i.
        Label lblCurrentRoom = new Label();
        lblCurrentRoom.setFont(new Font("Calibri", 30));
        lblCurrentRoom.setText("Bedroom");
        lblCurrentRoom.setVisible(false);

        //Ny textField
        TextField txtFieldInput = new TextField();
        txtFieldInput.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    Parser parser = new Parser();
                    txtAreaOutput.appendText("\n");
                    String userInput = txtFieldInput.getText();
                    Command command = parser.getCommand(userInput);
                    textIO.processCommand(command);
                    lblCurrentRoom.setText(textIO.getGame().getPlayer().getCurrentRoom().getName());
                    txtFieldInput.clear();
                }
            }
        });
        
        //Ny Button der starter spillet
        Button btnStart = new Button();
        btnStart.setText("Start");
        btnStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lblCurrentRoom.setVisible(true);
                btnStart.setVisible(false);
                textIO.printWelcome();
            }

        });

        //Her sættes label og knap ind i en VBOX som skal være i midten.
        VBox vbCenter = new VBox();
        vbCenter.setAlignment(Pos.CENTER);
        vbCenter.getChildren().add(lblCurrentRoom);
        vbCenter.getChildren().add(btnStart);

        //HBox oprettes og TextField og Button sættes ind.
        HBox hbBottom = new HBox();
        hbBottom.setPadding(new Insets(10, 10, 10, 10));
        hbBottom.setSpacing(10);
        hbBottom.getChildren().add(txtFieldInput);
        HBox.setHgrow(txtFieldInput, Priority.ALWAYS);

        BorderPane root = new BorderPane();
        root.setBottom(hbBottom);
        root.setTop(txtAreaOutput);
        root.setCenter(vbCenter);

        Scene scene = new Scene(root, 600, 500);

        primaryStage.setTitle("Fire Escape");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws InterruptedException {
        launch(args);
    }

}
