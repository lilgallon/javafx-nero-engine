package io.github.n3roo.math.physics;

import io.github.n3roo.world.GameObject;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.Stack;

public class Physics {

    public static double friction = 0.9;

    public static void handleForces(GameObject gameObject){
        Point2D movement = gameObject.getMovement();

        movement.multiply(friction);

        // This vector stores the effective movement. This way, Velocity force will be applied only once, and there will
        // not be any remaining residual velocity forces.
        Point2D constantMovement = new Point2D(0, 0);
        ArrayList<Force> persistentForces = new ArrayList<>();

        Stack<Force> forces = gameObject.getForces();
        while(!forces.empty()){
            Force force = forces.pop();
            switch (force.getMode()){
                case Persistent:
                    movement = movement.add(force.getVector());
                    persistentForces.add(force);
                    break;
                case Velocity:
                    constantMovement = constantMovement.add(force.getVector());
                    break;
                case Impulse:
                    movement = movement.add(force.getVector());
                    break;
            }
        }


        // Then, we can move
        constantMovement = constantMovement.add(movement);
        handleCollisionsAndMove(gameObject, constantMovement);

        // And set the current movement vector to the force vector containing all the impulse forces
        gameObject.setMovement(movement);

        // We put back all the persistent forces
        forces.addAll(persistentForces);
        gameObject.setForces(forces);
    }

    private static void handleCollisionsAndMove(GameObject gameObject, Point2D movement){
        // TODO: handle collision
        gameObject.move(movement.getX(), movement.getY());
    }
}
