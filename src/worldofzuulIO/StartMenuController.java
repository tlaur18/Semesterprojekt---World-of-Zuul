package worldofzuulIO;

import exceptions.NameInputException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
import worldofzuul.Highscore;

public class StartMenuController implements Initializable {

    private TextUI textUI;

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
        textUI = new TextUI(new Game(), new TextArea());
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

        btnOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    textUI.validName(nameInput.getText());
                    textUI.getGame().getPlayer().setPlayerName(nameInput.getText());

                    TextArea txtAreaIntro = new TextArea();
                    txtAreaIntro.setEditable(false);
                    txtAreaIntro.setFont(new Font("Calibri", 18));

                    Button btnContinue = new Button();
                    btnContinue.setText("Continue");
                    btnContinue.setFont(new Font("Calibri", 32));
                    btnContinue.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
                                BorderPane root = loader.load();
                                
                                MainController mainController = loader.<MainController>getController();
                                mainController.setTextUI(textUI);
                                
                                scene.setRoot(root);
                            } catch (IOException ex) {
                                System.out.println("Nothing to be found");
                            }
                        }
                    });
                    VBox introRoot = new VBox();
                    introRoot.setAlignment(Pos.CENTER);
                    introRoot.setPadding(new Insets(10, 10, 10, 10));
                    introRoot.setSpacing(50);
                    introRoot.getChildren().add(txtAreaIntro);
                    introRoot.getChildren().add(btnContinue);

                    Scene intro = paneName.getScene();
                    intro.setRoot(introRoot);

                    textUI.printWelcome(txtAreaIntro);
                } catch (NameInputException ex) {
                    lblName.setText("Your name cant include comma!");
                }
            }
        });

    }

    @FXML
    private void btnHighscoreEventHandler(ActionEvent event) {
        TableColumn<Highscore, String> tcName = new TableColumn<>();
        tcName.setText("Name");
        
        TableColumn<Highscore, Integer> tcScore = new TableColumn<>();
        tcScore.setText("Score");
        
        TableView<Highscore> tvHighscore = new TableView<>();
        tvHighscore.getColumns().add(tcName);
        tvHighscore.getColumns().add(tcScore);
        tvHighscore.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        VBox highscoreRoot = new VBox();

        Button btnBack = new Button("BACK");
        btnBack.setFont(Font.font("Calibri", FontWeight.BOLD, 24));
        btnBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Scene scene = highscoreRoot.getScene();
                scene.setRoot(startMenuRoot);
            }
        });

        highscoreRoot.setAlignment(Pos.CENTER);
        highscoreRoot.setPadding(new Insets(10, 10, 10, 10));
        highscoreRoot.setSpacing(50);
        highscoreRoot.getChildren().addAll(tvHighscore, btnBack);

        Scene scene = startMenuRoot.getScene();
        scene.setRoot(highscoreRoot);
        
        List<Highscore> highscores = textUI.getGame().getHighscoreDatabase().getHighscores();
        
        for (Highscore hs : highscores) {
            tvHighscore.getItems().add(hs);
        }
    }

    @FXML
    public void btnQuitEventHandler(ActionEvent event) {
        System.exit(0);
    }
}
