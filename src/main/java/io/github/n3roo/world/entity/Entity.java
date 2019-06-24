package io.github.n3roo.world.entity;

import javafx.geometry.Point2D;
import io.github.n3roo.graphics.Animation;
import io.github.n3roo.math.Position;
import io.github.n3roo.math.RigidBody;
import io.github.n3roo.math.physics.Force;
import javafx.scene.canvas.GraphicsContext;

import java.util.HashMap;
import java.util.Stack;

public class Entity {

    // The entity position
    protected Position position;

    // Rigid body (used for collision)
    protected RigidBody rigidBody;

    // Animations
    protected HashMap<Long, Animation> animations;
    protected long currentAnimation;

    // Forces
    private Stack<Force> forces;
    private Point2D movement;

    /**
     * It creates an entity. All the attributes are initialized as well.
     * @param position the entity position.
     */
    public Entity(Position position){
        this.position = position;
        this.rigidBody = null;
        this.animations = new HashMap<>();
        this.currentAnimation = 0;
        this.forces = new Stack<>();
        this.movement = new Point2D(0d, 0d);
    }

    /**
     * Update values in this function.
     */
    public void update(double delta){}

    /**
     * Render anything in this function.
     */
    public void render(GraphicsContext g){
        if(animations.size() != 0 && currentAnimation != -1){
            Animation animation = animations.get(currentAnimation);

            if(animation != null){
                g.drawImage(animation.getImage(), position.x, position.y);
            }
        }
    }

    /**
     * It adds a force to the entity.
     * @param force the force to apply.
     */
    public void addForce(Force force){
        forces.push(force);
    }

    /**
     * It changes the forces applied to this entity.
     * @param forces new forces.
     */
    public void setForces(Stack<Force> forces){
        this.forces = forces;
    }

    /**
     * @return the forces applied to this entity.
     */
    public Stack<Force> getForces(){
        return forces;
    }

    /**
     * It clears the forces applied to this entity.
     */
    public void clearForces(){
        forces = new Stack<>();
    }

    /**
     * It removes a persistent force.
     * @param force the persistent force to remove.
     */
    public void removePersistentForce(Force force){
        if(force.getMode() == Force.Mode.Persistent){
            forces.remove(force);
        }else{
            throw new IllegalArgumentException(
                    "Asked to remove a persistent force, but the force given is not persistent"
            );
        }
    }

    /**
     * @return movement vector.
     */
    public Point2D getMovement() {
        return movement;
    }

    /**
     * It changes the entity movement vector.
     * @param movement new movement vector.
     */
    public void setMovement(Point2D movement){
        this.movement = movement;
    }

    /**
     * It moves the entity (you have to be sure to what you are doing, it does not take in account collisions or
     * anything).
     * @param dx x movement,
     * @param dy y movement.
     */
    public void move(double dx, double dy){
        this.position.move(dx, dy);
    }

    /**
     * It changes the position.
     * @param x new x position,
     * @param y new y position.
     */
    public void setPosition(double x, double y){
        this.position.x = x;
        this.position.y = y;
    }

    /**
     * It changes the current animation (example: walking, and running).
     * @param currentAnimation new animation index.
     */
    public void setCurrentAnimation(int currentAnimation){
        this.currentAnimation = currentAnimation;
    }

    /**
     * @return the current animation index.
     */
    public long getCurrentAnimation(){
        return currentAnimation;
    }

    /**
     * It changes the animations of this object.
     * @param animations new animations.
     */
    public void setAnimations(HashMap<Long, Animation> animations){
        this.animations = animations;
    }

    /**
     * @return animations of this object.
     */
    public HashMap<Long, Animation> getAnimations(){
        return animations;
    }

    /**
     * @return the current position of this object.
     */
    public Position getPosition(){
        return this.position;
    }

    /**
     * The rigid body is a square. It is used to handle collision between objects.
     * @return the rigid body of this entity, or null, if this object does not have any rigid body.
     */
    public RigidBody getRigidBody(){
        return rigidBody;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Entity)) return false;
        Entity e = (Entity) o;
        return (e.getPosition().equals(position) &&
                e.getRigidBody() == rigidBody &&
                e.getForces().equals(forces) &&
                e.getMovement().equals(movement) &&
                e.getPosition().equals(position) &&
                e.getAnimations().equals(animations) &&
                e.getCurrentAnimation() == currentAnimation);
    }
}
