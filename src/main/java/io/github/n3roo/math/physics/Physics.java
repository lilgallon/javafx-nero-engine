package io.github.n3roo.math.physics;

import io.github.n3roo.math.Vec2d;
import io.github.n3roo.math.body.shapes.Polygon;
import io.github.n3roo.math.physics.collision.CollisionAlgorithms;
import io.github.n3roo.math.physics.collision.CollisionResult;
import io.github.n3roo.world.World;
import io.github.n3roo.world.entity.Entity;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.Stack;

public class Physics {

    // World friction
    public static double friction = 0.95;

    /**
     * It applies the forces of the specified entity, and handles the collision. At the end of this function, the entity
     * moved to the right place, and all the forces were updated.
     * @param entity the entity that needs to be updated.
     */
    public static void handleForces(Entity entity){
        Point2D movement = entity.getMovement();

        movement = movement.multiply(friction);

        double x, y;
        x = (movement.getX() < 0.001 && movement.getX() > - 0.001) ? 0 : movement.getX();
        y = (movement.getY() < 0.001  && movement.getY() > - 0.001) ? 0 : movement.getY();
        movement = new Point2D(x, y);

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

    /**
     * We have the entity, and its movement vector, and we need to move it according to its environment. We will handle
     * collisions and move the entity in this function.
     * @param entity the entity to move,
     * @param movement the movement to apply.
     */
    private static void handleCollisionsAndMove(Entity entity, Point2D movement){
        entity.move(movement.getX(), movement.getY());

        if(entity.getBody() != null){
            // Handle collision
            for(Entity target : World.getEntities()) {
                if(entity.equals(target)) continue;
                if(target.getBody() == null) continue;

                CollisionResult collisionResult = null;

                // TODO: aabb algorithm between rectangles & circles

                // aabb rectangle - rectangle
                // ...

                // aabb rectangle - circle
                // ...

                if(target.getBody().getHitbox() instanceof Polygon && entity.getBody().getHitbox() instanceof Polygon) {
                    collisionResult = CollisionAlgorithms.polygonPolygonSAT(
                            (Polygon) entity.getBody().getHitbox(),
                            (Polygon) target.getBody().getHitbox()
                    );
                }

                // ??? polygon - circle
                // ...

                if(collisionResult != null) {
                    if(collisionResult.overlap){
                        // Mass handling
                        double referenceMass = entity.getBody().getMass();
                        double targetMass = target.getBody().getMass();

                        double percentage;
                        if(targetMass == 0 && referenceMass == 0){
                            // Prevent division by 0
                            percentage = 0f;
                        }else if(referenceMass < 0){
                            // Infinite mass (it won't move)
                            percentage = 0f;
                        }else if(targetMass < 0 || referenceMass == 0){
                            // The other one has infinite mass (g1 will move)
                            percentage = 1f;
                        }else{
                            // Otherwise, we take the right percentage
                            percentage = (targetMass * 100 / (referenceMass + targetMass) ) / 100;
                        }

                        collisionResult.displacement.multiply(percentage);

                        entity.move(collisionResult.displacement.x, collisionResult.displacement.y);
                    }
                }
            }
        }
    }
}
