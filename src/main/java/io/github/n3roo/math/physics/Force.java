package io.github.n3roo.math.physics;

import javafx.geometry.Point2D;

public class Force {

    /**
     * Force mode :
     * Persistent : the force is affected by friction, and is RE-applied at each tick (example: gravity),
     * Velocity : the force is not affected by friction, but needs to be applied all the time (example: input movement),
     * Impulse : the force is affected by friction (example: punch).
     *
     * To remove a persistent force, you need to use removePersistentForce(Force persistentForce).
     */
    public enum Mode{
        Persistent,
        Velocity,
        Impulse
    }

    // Force vector
    private Point2D vector;

    // Force mode
    private Mode mode;

    /**
     * A force is represented by a 2D vector (x, y), and its mode (Velocity or Impulse).
     * @param vector the force vector,
     * @param mode the force mode.
     */
    public Force(Point2D vector, Mode mode){
        this.vector = vector;
        this.mode = mode;
    }

    /**
     * @return the values of this force.
     */
    public Point2D getVector() {
        return vector;
    }

    /**
     * @return the mode of this force.
     */
    public Mode getMode() {
        return mode;
    }
}
