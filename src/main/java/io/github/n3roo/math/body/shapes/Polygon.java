package io.github.n3roo.math.body.shapes;

import io.github.n3roo.math.Position;
import javafx.scene.canvas.GraphicsContext;

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

    @Override
    public void draw(GraphicsContext g) {
        double[] xPoints = new double[points.size()];
        double[] yPoints = new double[points.size()];

        for(int i = 0; i < points.size(); i++) {
            xPoints[i] = points.get(i).x;
            yPoints[i] = points.get(i).y;
        }

        g.fillPolygon(xPoints, yPoints, points.size());
    }

    public ArrayList<Position> getModel() {
        return model;
    }

    public ArrayList<Position> getWorldPoints() {
        return points;
    }
}
