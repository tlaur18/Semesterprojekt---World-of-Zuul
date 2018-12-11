package worldofzuulIO;

import exceptions.PlayerDiedException;
import exceptions.PlayerWinException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import worldofzuul.Command;
import worldofzuul.Fire;
import worldofzuul.Game;
import worldofzuul.Item;
import worldofzuul.Parser;
import worldofzuul.Room;

public class MainController implements Initializable {

    private TextUI textUI;

    @FXML
    private BorderPane root;
    @FXML
    private TextArea txtAreaOutput;
    @FXML
    private Button btnStart;
    @FXML
    private Button btnNorth;
    @FXML
    private Button btnWest;
    @FXML
    private Button btnSouth;
    @FXML
    private Button btnEast;
    @FXML
    private Label lblCurrentRoom;
    @FXML
    private ImageView imgPhone;
    @FXML
    private Button btnDrop;
    @FXML
    private Button btnInspect;
    @FXML
    private Button btnHelp;
    @FXML
    private Label lblInventoryHeadline;
    @FXML
    private ImageView imgBackground;
    @FXML
    private ImageView timon;
    @FXML
    private ImageView deadTimon;
    @FXML
    private Pane paneRoom;
    @FXML
    private ImageView imgInventory;
    @FXML
    private Button btnUse;
    @FXML
    private Rectangle greenbar;
    @FXML
    private Rectangle redbar;
    @FXML
    private Label healthText;
    @FXML
    private Label stepCounterText;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textUI = new TextUI(new Game(), txtAreaOutput);
    }

    @FXML
    private void btnStartEventHandler(ActionEvent event) {
        btnStart.setVisible(false);

        Label lblName = new Label();
        lblName.setAlignment(Pos.CENTER);
        lblName.setLayoutX(85);
        lblName.setLayoutY(25);
        lblName.prefHeight(25);
        lblName.prefWidth(300);
        lblName.setText("What is your name?");
        lblName.setFont(Font.font("Calibri", FontWeight.BOLD, 18));

        Button btnOk = new Button();
        btnOk.setLayoutX(145);
        btnOk.setLayoutY(100);
        btnOk.setText("OK");

        TextField nameInput = new TextField();
        nameInput.setLayoutX(85);
        nameInput.setLayoutY(63);
        nameInput.prefHeight(25);
        nameInput.prefWidth(220);

        Pane paneName = new Pane();
        paneName.prefHeight(150);
        paneName.prefWidth(300);
        paneName.getChildren().addAll(lblName, btnOk, nameInput);

        Scene scene = new Scene(paneName);

        Stage nameStage = new Stage();
        nameStage.setHeight(175);
        nameStage.setWidth(325);
        nameStage.setScene(scene);
        nameStage.setTitle("Player");
        nameStage.show();

        btnOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                textUI.getGame().getPlayer().setPlayerName(nameInput.getText());
                nameStage.close();

                TextArea txtAreaIntro = new TextArea();
                txtAreaIntro.setEditable(false);
                txtAreaIntro.setFont(new Font("Calibri", 18));

                Button btnContinue = new Button();
                btnContinue.setText("Continue");
                btnContinue.setFont(new Font("Calibri", 32));
                btnContinue.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Scene scene = txtAreaIntro.getScene();
                        scene.setRoot(root);

                        lblCurrentRoom.setVisible(true);
                        imgBackground.setVisible(true);
                        timon.setVisible(true);
                        btnUse.setDisable(false);
                        btnDrop.setDisable(false);
                        btnInspect.setDisable(false);
                        btnHelp.setDisable(false);
                        lblInventoryHeadline.setDisable(false);
                        redbar.setVisible(true);
                        greenbar.setVisible(true);
                        healthText.setVisible(true);
                        stepCounterText.setVisible(true);
                        printDirectionButtons();
                        printItems();
                        
                        //Gør så txtAreaOutput scroller automatisk ned lige fra starten af.
                        txtAreaOutput.appendText("\n");
                        txtAreaOutput.appendText("\n");
                        txtAreaOutput.appendText("\n");
                        txtAreaOutput.appendText("\n");
                    }
                });

                VBox introRoot = new VBox();
                introRoot.setAlignment(Pos.CENTER);
                introRoot.setPadding(new Insets(10, 10, 10, 10));
                introRoot.setSpacing(50);
                introRoot.getChildren().add(txtAreaIntro);
                introRoot.getChildren().add(btnContinue);

                Scene scene = root.getScene();
                scene.setRoot(introRoot);

                textUI.printWelcome(txtAreaIntro);
            }
        });
    }

    @FXML
    private void btnNorthEventHandler(ActionEvent event) {
        if (processCommand("go north")) {
            redrawRoom();
        }
    }

    @FXML
    private void btnWestEventHandler(ActionEvent event) {
        if (processCommand("go west")) {
            redrawRoom();
        }
    }

    @FXML
    private void btnSouthEventHandler(ActionEvent event) {
        if (processCommand("go south")) {
            redrawRoom();
        }
    }

    @FXML
    private void btnEastEventHandler(ActionEvent event) {
        if (processCommand("go east")) {
            redrawRoom();
        }
    }

    @FXML
    private void btnUseEventHandler(ActionEvent event) {
        processCommand("use " + (textUI.getGame().getPlayer().getInventory() != null ? textUI.getGame().getPlayer().getInventory().getName() : ""));

        if (textUI.getGame().getPlayer().getInventory() == null) {
            imgInventory.setImage(null);
        } else if (!(textUI.getGame().getPlayer().getInventory().getImage().equals(imgInventory))) {
            imgInventory.setImage(textUI.getGame().getPlayer().getInventory().getImage().getImage());
        }

        if (textUI.getGame().getPlayer().getCurrentRoom().getFire() == null) {
            removeFire();
        }

        drawHealthBar();
    }

    @FXML
    private void btnDropEventHandler(ActionEvent event) {
        removeItems(textUI.getGame().getPlayer().getCurrentRoom());
        processCommand("drop");
        printItems();
        imgInventory.setImage(null);
    }

    @FXML
    private void btnInspectEventHandler(ActionEvent event) {
        processCommand("inspect");
    }

    @FXML
    private void btnHelpEventHandler(ActionEvent event) {
        processCommand("help");
    }

    private void drawHealthBar() {
        greenbar.setWidth(textUI.getGame().getPlayer().getHealth() * 1.5);
        healthText.setText(Integer.toString(textUI.getGame().getPlayer().getHealth()) + " " + "HP");
    }

    private void stepCounterText() {
        stepCounterText.setText("Step counter: " + Integer.toString(textUI.getGame().getPlayer().getStepCount()));
    }

    private boolean processCommand(String inputLine) {
        boolean changedRoom = false;
        try {
            Parser parser = new Parser();
            txtAreaOutput.appendText("\n");
            Command command = parser.getCommand(inputLine);
            changedRoom = textUI.processCommand(command);
            lblCurrentRoom.setText(textUI.getGame().getPlayer().getCurrentRoom().getName());
        } catch (PlayerDiedException ex) {
            redrawRoom();
            disableGame();

            Label lblDead = new Label();
            lblDead.setText("You died. Would you like to try again?");

            Button btnYes = new Button();
            btnYes.setText("Yes");

            Button btnNo = new Button();
            btnNo.setText("No");
            btnNo.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    System.exit(0);
                }
            });

            HBox hBox = new HBox();
            hBox.getChildren().add(btnYes);
            hBox.getChildren().add(btnNo);
            hBox.setAlignment(Pos.CENTER);
            hBox.setPadding(new Insets(10, 10, 10, 10));
            hBox.setSpacing(10);

            VBox vBox = new VBox();
            vBox.getChildren().add(lblDead);
            vBox.getChildren().add(hBox);
            vBox.setAlignment(Pos.CENTER);

            Scene scene = new Scene(vBox);

            Stage deadStage = new Stage();
            timon.setVisible(false);
            deadTimon.setVisible(true);
            deadStage.setScene(scene);
            deadStage.setHeight(150);
            deadStage.setWidth(300);
            deadStage.setTitle("Try again?");
            deadStage.show();

            btnYes.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    Stage primaryStage = (Stage) root.getScene().getWindow();
                    primaryStage.close();

                    Start start = new Start();
                    try {
                        start.start(new Stage());
                    } catch (Exception ex1) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex1);
                    }

                    deadStage.close();
                }
            });
        } catch (PlayerWinException ex) {
            redrawRoom();
            txtAreaOutput.appendText("\nYOU WON THE GAME!");
            disableGame();

            Label lblWin = new Label();
            lblWin.setText("YOU WIN!");

            Button btnNo = new Button();
            btnNo.setText("OK");
            btnNo.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    System.exit(0);
                }
            });

            HBox hBox = new HBox();
            hBox.getChildren().add(btnNo);
            hBox.setAlignment(Pos.CENTER);
            hBox.setPadding(new Insets(10, 10, 10, 10));
            hBox.setSpacing(10);

            VBox vBox = new VBox();
            vBox.getChildren().add(lblWin);
            vBox.getChildren().add(hBox);
            vBox.setAlignment(Pos.CENTER);

            Scene scene = new Scene(vBox);

            Stage winStage = new Stage();
            winStage.setScene(scene);
            winStage.setHeight(150);
            winStage.setWidth(300);
            winStage.setTitle("Congratulation");
            winStage.show();
        }
        return changedRoom;
    }

    private void redrawRoom() {
        removeItems(textUI.getGame().getPlayer().getPreviousRoom());
        removeFire();
        printDirectionButtons();
        printItems();
        printFire();
        setBackground();
        drawHealthBar();
        stepCounterText();
    }

    private void printDirectionButtons() {
        btnNorth.setVisible(false);
        btnWest.setVisible(false);
        btnSouth.setVisible(false);
        btnEast.setVisible(false);

        for (String exitString : textUI.getGame().getPlayer().getCurrentRoom().getExits().keySet()) {
            switch (exitString) {
                case "north":
                    btnNorth.toFront();
                    btnNorth.setVisible(true);
                    btnNorth.setText(textUI.getGame().getPlayer().getCurrentRoom().getExit("north").getName());
                    break;
                case "west":
                    btnWest.toFront();
                    btnWest.setVisible(true);
                    btnWest.setText(textUI.getGame().getPlayer().getCurrentRoom().getExit("west").getName());
                    break;
                case "south":
                    btnSouth.toFront();
                    btnSouth.setVisible(true);
                    btnSouth.setText(textUI.getGame().getPlayer().getCurrentRoom().getExit("south").getName());
                    break;
                case "east":
                    btnEast.toFront();
                    btnEast.setVisible(true);
                    btnEast.setText(textUI.getGame().getPlayer().getCurrentRoom().getExit("east").getName());
                    break;
            }
        }
    }

    private void printItems() {
        for (Item item : textUI.getGame().getPlayer().getCurrentRoom().getItems()) {
            ImageView img = item.getImage();
            paneRoom.getChildren().add(img);

            img.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    if (textUI.getGame().getPlayer().getInventory() == null) {
                        imgInventory.setImage(img.getImage());
                        paneRoom.getChildren().remove(img);
                    }
                    processCommand("take " + item.getName());
                }
            });
        }
    }

    private void removeItems(Room whereToRemoveItemsFrom) {
        for (Item item : whereToRemoveItemsFrom.getItems()) {
            ImageView img = item.getImage();
            paneRoom.getChildren().remove(img);
        }
    }

    private void disableGame() {
        root.setDisable(true);
    }

    private void setBackground() {
        ImageView img = textUI.getGame().getPlayer().getCurrentRoom().getImage();
        imgBackground.setImage(img.getImage());
    }

    private void printFire() {
        Fire fire = textUI.getGame().getPlayer().getCurrentRoom().getFire();
        if (fire != null) {
            for (int i = 0; i < fire.getLvl() * 3; i++) {
                ImageView imgFire = new ImageView(Fire.IMAGE_FIRE);
                imgFire.fitHeightProperty().set(100);
                imgFire.fitWidthProperty().set(60);
                imgFire.setTranslateX(80 + Math.random() * 480);
                imgFire.setTranslateY(30 + Math.random() * 330);
                paneRoom.getChildren().add(imgFire);
            }
        }
    }

    private void removeFire() {
        ArrayList<Node> nodesToRemove = new ArrayList<>();

        for (Node node : paneRoom.getChildren()) {
            if (node instanceof ImageView) {
                if (((ImageView) node).getImage().equals(Fire.IMAGE_FIRE)) {
                    nodesToRemove.add(node);
                }
            }
        }

        paneRoom.getChildren().removeAll(nodesToRemove);
    }
}
