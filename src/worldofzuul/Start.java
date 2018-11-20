package worldofzuul;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Start extends Application {

    public Button startButton, highscoreButton, faqButton, quitButton;

    private playerSprite player = new playerSprite(0, 0, 40, 40, "player", Color.RED);

    private Pane root = new Pane();

    private Parent game() {
        root.setPrefSize(500, 500);

        root.getChildren().add(player);

        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Fire Escape");

        Scene scene = new Scene(game());

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W:
                    player.moveUp();
                    break;
                case S:
                    player.moveDown();
                    break;
                case A:
                    player.moveLeft();
                    break;
                case D:
                    player.moveRight();
                    break;
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private static class playerSprite extends Rectangle {

        boolean dead = false;
        final String type;

        playerSprite(int x, int y, int w, int h, String type, Color color) {
            super(w, h, color);

            this.type = type;
            setTranslateX(x);
            setTranslateY(y);
        }

        void moveLeft() {
            setTranslateX(getTranslateX() - 10);
        }

        void moveRight() {
            setTranslateX(getTranslateX() + 10);
        }

        void moveUp() {
            setTranslateY(getTranslateY() - 10);
        }

        void moveDown() {
            setTranslateY(getTranslateY() + 10);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        launch(args);
    }

}
