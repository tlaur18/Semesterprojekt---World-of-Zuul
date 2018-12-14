/**
 * 
 * This class is the controller for the Start Menu. Everything in the StartMenu.fxml
 * is drawn in this class. This class also contains the scene where you get
 * the players name and the scene where it prints the welcome text.
 * The Start Menu contains three different buttons: Start, Highscore and Quit.
 * Start changes the scenes, but not actually starting the game yet. The game
 * starts after you click 'Continue' at the welcome text scene.
 * Highscore shows the highscore list.
 * Quit quits the game.
 * 
 * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
 * @version 2018.12.14
 * 
 */

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
        textUI = new TextUI();
    }
    
    /*
    This is a onAction method for the 'Start' button in the start menu.
    This onAction method contains two different scenes. One of them is the
    scene where you get the players name and the other one is the welcome text.
    The method also contains two other button onActions inside. The first is the
    'Ok' button that changes scene to the welcome text scene. The second is
    the 'Continue' button in the welcome text scene that loads another FXML file
    and changes the scenes root to the main.fxml file.
    */
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
        lblName.setAlignment(Pos.CENTER);

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
                    lblName.setText("Invalid name\nYour name cannot be\nempty or include commas");
                    lblName.setFont(Font.font("Calibri", FontWeight.BOLD, 24));
                }
            }
        });

    }

    /*
    This is a onAction method for the 'Highscore' button that creates another
    scene and changes from the start menu root to the highscore root, so the
    scene changes.
    */
    @FXML
    private void btnHighscoreEventHandler(ActionEvent event) {
        VBox vbName = new VBox();
        vbName.setSpacing(10);

        VBox vbScore = new VBox();
        vbScore.setSpacing(10);

        HBox hbCenter = new HBox();
        hbCenter.getChildren().addAll(vbName, vbScore);
        hbCenter.setAlignment(Pos.CENTER);
        hbCenter.setPadding(new Insets(75, 0, 0, 0));
        hbCenter.setSpacing(40);

        Button btnBack = new Button("BACK");
        btnBack.setFont(Font.font("Calibri", FontWeight.BOLD, 24));
        btnBack.setAlignment(Pos.CENTER);
        
        Label lblTop = new Label("Highscores");
        lblTop.setFont(new Font("Calibri", 40));

        BorderPane highscoreRoot = new BorderPane();
        highscoreRoot.setPadding(new Insets(25, 25, 25, 25));
        highscoreRoot.setCenter(hbCenter);
        BorderPane.setAlignment(lblTop, Pos.CENTER);
        highscoreRoot.setTop(lblTop);
        BorderPane.setAlignment(btnBack, Pos.CENTER);
        BorderPane.setMargin(btnBack, new Insets(12, 12, 12, 12)); // optional
        highscoreRoot.setBottom(btnBack);

        Scene scene = startMenuRoot.getScene();
        scene.setRoot(highscoreRoot);

        List<Highscore> highscores = textUI.getGame().getHighscoreDatabase().getHighscores();

        for (Highscore hs : highscores) {
            Label lblName = new Label(hs.getName());
            lblName.setFont(new Font("Calibri", 18));
            vbName.getChildren().add(lblName);
            
            Label lblScore = new Label(Integer.toString(hs.getScore()));
            lblScore.setFont(new Font("Calibri", 18));
            vbScore.getChildren().add(lblScore);
        }

        btnBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Scene scene = highscoreRoot.getScene();
                scene.setRoot(startMenuRoot);
            }
        });
    }

    // OnAction method for the 'Quit' button in the start menu that quits the game.
    @FXML
    public void btnQuitEventHandler(ActionEvent event) {
        System.exit(0);
    }
}
