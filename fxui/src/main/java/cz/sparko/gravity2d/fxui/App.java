package cz.sparko.gravity2d.fxui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import cz.sparko.gravity2d.core.Body;
import cz.sparko.gravity2d.core.Field;
import cz.sparko.gravity2d.util.Point;
import cz.sparko.gravity2d.util.Vector2d;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    static final int WIDTH = 800;
    static final int HEIGHT = 800;

    private final Random r = new Random();

    private boolean running = true;

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

        windowController.content.heightProperty().addListener((observable, oldValue, newValue) -> {
            windowController.canvas.heightProperty().setValue(newValue.doubleValue() - 50);
        });
        windowController.canvas.widthProperty().bind(windowController.content.widthProperty());

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();

        windowController.listenStartSwitch(() -> this.running = !this.running);
        mainLoop();
    }

    private Vector2d randomSpeed(int bound) {
        return new Vector2d(randomNumber(bound), randomNumber(bound));
    }

    private int randomNumber(int bound) {
        return r.nextInt(bound) * (r.nextInt(2) == 1 ? -1 : 1);
    }

    private void mainLoop() {
        field = new Field();
        field.addBody(new Body(new Point(0, 0),
                new Vector2d(0, 0), 100000, Color.DEEPSKYBLUE));
        field.addBody(new Body(new Point(300, 0),
                new Vector2d(0, -5), 1, Color.DARKGRAY));
        IntStream.range(0, 100).forEach(i -> field.addBody(new Body(new Point(r.nextInt(600) - 300, r
                .nextInt(600) - 300), new Vector2d(0, 0), 1)));

        new Thread(() -> {
            LOG.info("so, what we have here ???");
            field.getBodies().forEach(b -> LOG.info("[{}]", b));
            double maxMass = field.getBodies().stream()
                    .map(Body::getMass)
                    .max((a, b) -> a - b > 0 ? 1 : -1)
                    .get();

            AtomicInteger fpsCounter = new AtomicInteger(0);
            new Timer().scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    int fps = fpsCounter.getAndSet(0);
                    LOG.info("[{}] FPS", fps);
                }
            }, 0, 1000);

            while (true) {
                if (running) {
                    this.windowController.drawBodies(this.field.getBodies(), maxMass);
                    this.field.nextIteration();
                    int fps = fpsCounter.incrementAndGet();
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
