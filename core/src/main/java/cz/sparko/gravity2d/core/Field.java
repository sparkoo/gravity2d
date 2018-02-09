package cz.sparko.gravity2d.core;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private final List<Body> bodies = new ArrayList<>();

    private final int width;
    private final int height;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void nextIteration() {
        this.bodies.forEach(b -> b.move(this.bodies, this.width, this.height));
    }

    public void addBody(Body body) {
        this.bodies.add(body);
    }

    public List<Body> getBodies() {
        return this.bodies;
    }
}
