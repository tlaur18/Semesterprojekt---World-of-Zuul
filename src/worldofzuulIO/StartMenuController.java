package worldofzuulIO;

import exceptions.NameInputException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import worldofzuul.Game;

public class StartMenuController implements Initializable {

    private TextUI textUI;

    @FXML
    private TextArea txtAreaOutput;
    @FXML
    private BorderPane startMenuRoot;
    @FXML
    private Button btnStart;
    @FXML
    private Button btnHighscore;
    @FXML
    private Button btnQuit;
    @FXML
    private ImageView logo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textUI = new TextUI(new Game(), txtAreaOutput);
    }

    @FXML
    private void btnStartEventHandler(ActionEvent e) {
        Label lblName = new Label();
        lblName.setAlignment(Pos.CENTER);
        lblName.setLayoutX(275);
        lblName.setLayoutY(200);
        lblName.prefHeight(25);
        lblName.prefWidth(300);
        lblName.setText("Before we begin.. \nWhat is your name?");
        lblName.setFont(Font.font("Calibri", FontWeight.BOLD, 30));
        lblName.setTextAlignment(TextAlignment.CENTER);

        Button btnOk = new Button();
        btnOk.setLayoutX(360);
        btnOk.setLayoutY(400);
        btnOk.setPrefHeight(40);
        btnOk.setPrefWidth(80);
        btnOk.setFont(new Font("Calibri", 16));
        btnOk.setText("OK");

        TextField nameInput = new TextField();
        nameInput.setLayoutX(325);
        nameInput.setLayoutY(325);
        nameInput.prefHeight(25);
        nameInput.prefWidth(220);

        Pane paneName = new Pane();
        paneName.getChildren().addAll(lblName, btnOk, nameInput);

        Scene scene = startMenuRoot.getScene();
        scene.setRoot(paneName);
        
//        btnOk.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent e) {
//                try {
//                    textUI.validName(nameInput.getText());
//                    textUI.getGame().getPlayer().setPlayerName(nameInput.getText());
//
//                    TextArea txtAreaIntro = new TextArea();
//                    txtAreaIntro.setEditable(false);
//                    txtAreaIntro.setFont(new Font("Calibri", 18));
//
//                    Button btnContinue = new Button();
//                    btnContinue.setText("Continue");
//                    btnContinue.setFont(new Font("Calibri", 32));
//                    btnContinue.setOnAction(new EventHandler<ActionEvent>() {
//                        @Override
//                        public void handle(ActionEvent event) {
//                            try {
//                                BorderPane main = FXMLLoader.load(getClass().getResource("main.fxml"));
////                                startMenuRoot.getChildren().setAll(main);
//                                scene.setRoot(main);
//
//                                //Gør så txtAreaOutput scroller automatisk ned lige fra starten af.
//                                txtAreaOutput.appendText("\n");
//                                txtAreaOutput.appendText("\n");
//                                txtAreaOutput.appendText("\n");
//                                txtAreaOutput.appendText("\n");
//                            } catch (IOException ex) {
//                                System.out.println("Nothing to be found");
//                            }
////                            Scene scene = txtAreaIntro.getScene();
////                            scene.setRoot(root);
//                        }
//                    });
//                    VBox introRoot = new VBox();
//                    introRoot.setAlignment(Pos.CENTER);
//                    introRoot.setPadding(new Insets(10, 10, 10, 10));
//                    introRoot.setSpacing(50);
//                    introRoot.getChildren().add(txtAreaIntro);
//                    introRoot.getChildren().add(btnContinue);
//
//                    Scene intro = paneName.getScene();
//                    intro.setRoot(introRoot);
//
//                    textUI.printWelcome(txtAreaIntro);
//                } catch (NameInputException ex) {
//                    lblName.setText("Your name cant include comma!");
//                }
//            }
//        });
    }
}
