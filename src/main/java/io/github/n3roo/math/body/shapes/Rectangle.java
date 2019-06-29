package io.github.n3roo.math.body.shapes;

import io.github.n3roo.math.Position;

import java.util.ArrayList;

public class Rectangle extends Polygon {

    /**
     * It creates a rectangle.
     *
     * @param position the position of the first point in the world,
     * @param width the width of the rectangle,
     * @param height the height of the rectangle,
     * @param rotation the shape rotation (from the position),
     * @param originInCenter true means that the position given is the center. Otherwise, it corresponds to the top left
     *                       corner.
     */
    public Rectangle(Position position, double width, double height, double rotation, boolean originInCenter) {
        super(position, rotation, new ArrayList<>());

        ArrayList<Position> model = new ArrayList<>();
        if(originInCenter) {
            model.add(new Position(- width / 2, - height / 2));
            model.add(new Position(width / 2, - height / 2));
            model.add(new Position(width / 2, height / 2));
            model.add(new Position(- width / 2, height / 2));
        } else {
            model.add(new Position(0, 0));
            model.add(new Position(width, 0));
            model.add(new Position(width, height));
            model.add(new Position(0, height));
        }
        setModel(model);
    }
}
