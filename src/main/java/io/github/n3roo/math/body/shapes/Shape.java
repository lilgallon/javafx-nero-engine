package io.github.n3roo.math.body.shapes;

import io.github.n3roo.math.Position;

public abstract class Shape {

    /**
     * The shape position in the world
     */
    protected Position position;

    /**
     * The shape rotation in radians.
     */
    protected double rotation;

    /**
     * It creates a shape.
     * @param position the position of the top left corner of the shape (the first point),
     * @param rotation the shape rotation in radians.
     */
    public Shape(Position position, double rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    protected abstract void propertyUpdated();

    public Position getPosition() {
        return position;
    }

    public double getRotation() {
        return rotation;
    }

    public void setPosition(Position position) {
        this.position = position;
        propertyUpdated();
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
        propertyUpdated();
    }
}
