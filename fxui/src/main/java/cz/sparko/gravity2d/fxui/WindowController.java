package cz.sparko.gravity2d.fxui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.EventListener;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

import cz.sparko.gravity2d.core.Body;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class WindowController implements Initializable {
    private static final Logger LOG = LoggerFactory.getLogger(WindowController.class);

    static final int SPEED_DIVIDER = 100000;
    private static final double MAX_SIZE = 50;
    private static final double MIN_SIZE = 3;

    @FXML
    public Canvas canvas;
    @FXML
    public BorderPane content;

    private Runnable startListener;

    private GraphicsContext canvasContext;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canvasContext = canvas.getGraphicsContext2D();
        canvasContext.setFill(Color.YELLOW);
        canvasContext.fillRect(10, 10, 10, 10);
    }

    private void clearCanvas() {
        canvasContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    void drawBodies(List<Body> bodies, double maxMass) {
        this.clearCanvas();
        double sizeCoefficient = maxMass / MAX_SIZE;
        bodies.forEach(body -> {
            LOG.trace("[{}]", body);
            double size = body.getMass() / sizeCoefficient;
            if (size < MIN_SIZE) {
                size = MIN_SIZE;
            }
            double halfSize = size / 2;

            canvasContext.setFill(body.getColor());
            canvasContext.fillOval(
                    (body.getPosition().getX() / SPEED_DIVIDER) - halfSize,
                    (body.getPosition().getY() / SPEED_DIVIDER) - halfSize,
                    size, size);
        });
    }

    public void listenStartSwitch(Runnable listener) {
        this.startListener = listener;
    }

    public void onStartStop(ActionEvent actionEvent) {
        LOG.info("start/stop button pressed [{}]", actionEvent);

        if (startListener != null) {
            startListener.run();
        }
    }
}
