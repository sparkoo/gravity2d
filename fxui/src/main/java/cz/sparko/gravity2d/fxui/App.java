package cz.sparko.gravity2d.fxui;

import java.io.IOException;
import java.util.Random;
import java.util.stream.IntStream;

import cz.sparko.gravity2d.core.Body;
import cz.sparko.gravity2d.core.Field;
import cz.sparko.gravity2d.util.Point;
import cz.sparko.gravity2d.util.Vector2d;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static cz.sparko.gravity2d.fxui.WindowController.SPEED_DIVIDER;

public class App extends Application {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    private final Random r = new Random();

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

    private Vector2d randomSpeed(int bound) {
        return new Vector2d(randomNumber(bound), randomNumber(bound));
    }

    private int randomNumber(int bound) {
        return r.nextInt(bound) * (r.nextInt(2) == 1 ? -1 : 1);
    }

    private void mainLoop() {
        this.field = new Field();
        IntStream.range(0, 5).forEach(i -> field.addBody(
                new Body(new Point(r.nextInt(WIDTH) * SPEED_DIVIDER, r.nextInt(HEIGHT) * SPEED_DIVIDER),
                        randomSpeed(50), r.nextInt(100))));

                field.addBody(new Body(new Point(WIDTH / 2 * SPEED_DIVIDER, HEIGHT / 2 * SPEED_DIVIDER),
                        new Vector2d(0, 0), 10000));
        //
        //        field.addBody(new Body(new Point((WIDTH / 2 + 50) * SPEED_DIVIDER, HEIGHT / 2 * SPEED_DIVIDER),
        //                new Vector2d(0, 150), 10));
        //
        //        field.addBody(new Body(new Point((WIDTH / 2 + 100) * SPEED_DIVIDER, HEIGHT / 2 * SPEED_DIVIDER),
        //                new Vector2d(0, 125), 10));
        //
        //        field.addBody(new Body(new Point((WIDTH / 2 + 200) * SPEED_DIVIDER, HEIGHT / 2 * SPEED_DIVIDER),
        //                new Vector2d(0, 100), 10));

        new Thread(() -> {
            LOG.info("so, what we have here ???");
            field.getBodies().forEach(b -> LOG.info("[{}]", b));
            while (true) {
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
