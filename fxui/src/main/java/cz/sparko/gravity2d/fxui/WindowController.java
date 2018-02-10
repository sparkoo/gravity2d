package cz.sparko.gravity2d.fxui;

import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import cz.sparko.gravity2d.core.Body;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class WindowController implements Initializable {

    static final int SPEED_DIVIDER = 100;
    private static final int SIZE_DIVIDER = 10;

    @FXML
    public Canvas canvas;
    @FXML
    public BorderPane content;

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

    void drawBodies(List<Body> bodies) {
        this.clearCanvas();
        Random r = new Random();
        bodies.forEach(body -> {
            double size = body.getMass() / SIZE_DIVIDER;
            double halfSize = size / 2;

            canvasContext.setFill(body.getColor());
            canvasContext.fillOval(
                    (body.getPosition().getX() / SPEED_DIVIDER) - 2.5,
                    (body.getPosition().getY() / SPEED_DIVIDER) - 2.5,
                    5, 5);
        });
    }
}
