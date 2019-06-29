package io.github.n3roo.math.body.shapes;

import io.github.n3roo.math.Position;

import java.util.ArrayList;

public class Rectangle extends Polygon {

    /**
     * It creates a rectangle.
     *
     * @param position the position of the first point in the world,
     * @param rotation the shape rotation (from the position),
     * @param model    an array of points representing the rectangle (4 points).
     */
    public Rectangle(Position position, double rotation, ArrayList<Position> model) {
        super(position, rotation, model);
        if(model.size() != 4) throw new IllegalArgumentException("Model strictly requires 4 points for a rectangle.");
    }
}
