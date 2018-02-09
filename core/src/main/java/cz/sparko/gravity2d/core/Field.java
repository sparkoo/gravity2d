package cz.sparko.gravity2d.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Field {
    private List<Body> bodies = new ArrayList<>();

    public Field() {
    }

    public void nextIteration() {
        this.bodies = this.bodies.stream()
                .map(b -> b.move(this.bodies))
                .collect(Collectors.toList());
    }

    public void addBody(Body body) {
        this.bodies.add(body);
    }

    public List<Body> getBodies() {
        return this.bodies;
    }
}
