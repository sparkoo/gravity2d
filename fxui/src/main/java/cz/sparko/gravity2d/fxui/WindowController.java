package cz.sparko.gravity2d.fxui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import cz.sparko.gravity2d.core.Body;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class WindowController implements Initializable {
    @FXML
    public Canvas canvas;
    @FXML
    public BorderPane content;

    private GraphicsContext canvasContext;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canvasContext = canvas.getGraphicsContext2D();
        canvasContext.setFill(Color.ORANGERED);
        canvasContext.fillRect(10, 10, 10, 10);
    }

    private void clearCanvas() {
        canvasContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    void drawBodies(List<Body> bodies) {
        this.clearCanvas();
        bodies.forEach(body -> canvasContext.fillOval(body.getX(), body.getY(), 5, 5));
    }
}
