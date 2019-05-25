package io.github.n3roo.math.physics;

import io.github.n3roo.world.entity.Entity;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.Stack;

public class Physics {

    public static double friction = 0.9;

    public static void handleForces(Entity entity){
        Point2D movement = entity.getMovement();

        movement.multiply(friction);

        // This vector stores the effective movement. This way, Velocity force will be applied only once, and there will
        // not be any remaining residual velocity forces.
        Point2D constantMovement = new Point2D(0, 0);
        ArrayList<Force> persistentForces = new ArrayList<>();

        Stack<Force> forces = entity.getForces();
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
        handleCollisionsAndMove(entity, constantMovement);

        // And set the current movement vector to the force vector containing all the impulse forces
        entity.setMovement(movement);

        // We put back all the persistent forces
        forces.addAll(persistentForces);
        entity.setForces(forces);
    }

    private static void handleCollisionsAndMove(Entity entity, Point2D movement){
        // TODO: handle collision
        entity.move(movement.getX(), movement.getY());
    }
}
