package cz.sparko.gravity2d.fxui;

import java.io.IOException;
import java.util.Random;
import java.util.stream.IntStream;

import cz.sparko.gravity2d.core.Body;
import cz.sparko.gravity2d.core.Field;
import cz.sparko.gravity2d.util.Point;
import cz.sparko.gravity2d.util.Vector2d;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    private Field field;

    private WindowController windowController;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Window.fxml"));
        Parent root = loader.load();
        windowController = loader.getController();

        windowController.canvas.widthProperty().bind(windowController.content.widthProperty());
        windowController.canvas.heightProperty().bind(windowController.content.heightProperty());

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
        mainLoop();
    }

    private void mainLoop() {
        this.field = new Field();
        Random r = new Random();
        IntStream.range(0, 5).forEach(i -> field.addBody(
                new Body(new Point(r.nextInt(WIDTH) * WindowController.SPEED_DIVIDER, r.nextInt(HEIGHT) * WindowController.SPEED_DIVIDER),
                new Vector2d(r.nextInt(10), r.nextInt(10)), r.nextInt(1000))));

        new Thread(() -> {
            while(true) {
                this.windowController.drawBodies(this.field.getBodies());
                this.field.nextIteration();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
