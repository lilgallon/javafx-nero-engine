package io.github.n3roo.math.body.shapes;

import io.github.n3roo.math.Position;

import java.util.ArrayList;

public class Polygon extends Shape{

    /**
     * Model of the shape (non-transformed)
     */
    private ArrayList<Position> model;

    /**
     * Transformed points (taking rotation & position in account)
     */
    private ArrayList<Position> points;

    /**
     * It creates a polygon.
     * @param position the position of the first point in the world,
     * @param rotation the shape rotation (from the position),
     * @param model an array of points representing the polygon.
     */
    public Polygon(Position position, double rotation, ArrayList<Position> model) {
        super(position, rotation);
        this.model = model;
        transform();
    }

    private void transform() {
        points = new ArrayList<>();
        for(Position modelPosition : model) {
            // 2D translation
            Position transformPosition = position.copy();

            // 2D rotation
            transformPosition.move(
                    modelPosition.x * Math.cos(rotation) - modelPosition.y * Math.sin(rotation),
                    modelPosition.x * Math.sin(rotation) + modelPosition.y * Math.cos(rotation)
            );

            points.add(transformPosition);
        }
    }

    @Override
    protected void propertyUpdated() {
        transform();
    }

    public ArrayList<Position> getModel() {
        return model;
    }

    public ArrayList<Position> getWorldPoints() {
        return points;
    }
}
