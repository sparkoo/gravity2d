package cz.sparko.gravity2d.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

import cz.sparko.gravity2d.util.Point;
import cz.sparko.gravity2d.util.Vector2d;

public class Body {
    private static final Logger LOG = LoggerFactory.getLogger(Body.class);

    private final Point position;

    private final Vector2d velocity;

    private final int mass;

    public Body(Point position, Vector2d velocity, int mass) {
        this.position = position;
        this.velocity = velocity;
        this.mass = mass;
    }

    public Body move(List<Body> bodies, int maxX, int maxY) {
        //        if (position.getX() <= 0 || position.getX() >= maxX) {
        //            this.velocity = this.velocity.reverseX();
        //        }
        //        if (this.position.getY() <= 0 || this.position.getY() >= maxY) {
        //            this.velocity = this.velocity.reverseY();
        //        }
        //
        //        position = position.move(this.velocity);
        double G = 6.674 * (10 ^ -11);
        Vector2d force = bodies.stream().map(b -> {
            double forceScalar = G * (this.mass * b.mass) / position.distance(b.position);
            Vector2d direction = position.direction(b.position);
            Vector2d unitDirection = direction.unit();
            return unitDirection.multiply(forceScalar);
        }).reduce(Vector2d::add)
                .orElseThrow(() -> new RuntimeException("unable to calculate the Force"));

        LOG.info("force by bodies [{}]", force);
        Vector2d finalVelocity = velocity.add(force);
        LOG.info("final velocity [{}]", finalVelocity);
        return new Body(position.move(finalVelocity), finalVelocity, mass);
    }

    @Override
    public String toString() {
        return "Body{" +
                "position=" + position +
                ", velocity=" + velocity +
                ", mass=" + mass +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Body body = (Body) o;
        return mass == body.mass &&
                Objects.equals(position, body.position) &&
                Objects.equals(velocity, body.velocity);
    }

    @Override
    public int hashCode() {

        return Objects.hash(position, velocity, mass);
    }

    public Point getPosition() {
        return position;
    }

    public Vector2d getVelocity() {
        return velocity;
    }

    public int getMass() {
        return mass;
    }
}
