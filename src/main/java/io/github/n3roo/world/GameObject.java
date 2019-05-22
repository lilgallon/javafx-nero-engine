package io.github.n3roo.world;

import javafx.geometry.Point2D;
import io.github.n3roo.graphics.Animation;
import io.github.n3roo.math.Position;
import io.github.n3roo.math.RigidBody;
import io.github.n3roo.math.physics.Force;
import javafx.scene.canvas.GraphicsContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class GameObject {

    // The game object position
    protected Position position;

    // Rigid body (used for collision)
    protected RigidBody rigidBody;

    // Animations
    protected Map<Long, Animation> animations;
    protected long currentAnimation;

    // Forces
    private Stack<Force> forces;
    private Point2D movement;

    /**
     * It creates a game object. All the attributes are initialized as well.
     * @param position the game object position.
     */
    public GameObject(Position position){
        this.position = position;
        this.rigidBody = null;
        this.animations = new HashMap<>();
        this.currentAnimation = -1;
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
     * It adds a force to the game object.
     * @param force the force to apply.
     */
    public void addForce(Force force){
        forces.push(force);
    }

    /**
     * It adds a force to the game object.
     * @param vector the force vector,
     * @param mode the force mode.
     */
    public void addForce(Point2D vector, Force.Mode mode){
        addForce(new Force(vector, mode));
    }

    /**
     * It changes the forces applied to this game object.
     * @param forces new forces.
     */
    public void setForces(Stack<Force> forces){
        this.forces = forces;
    }

    /**
     * @return the forces applied to this game object.
     */
    public Stack<Force> getForces(){
        return forces;
    }

    /**
     * It clears the forces applied to this game object.
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
     * It changes the game object movement vector.
     * @param movement new movement vector.
     */
    public void setMovement(Point2D movement){
        this.movement = movement;
    }

    /**
     * It moves the game object (you have to be sure to what you are doing, it does not take in account collisions or
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
    public void setPosition(float x, float y){
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
     * It changes the animations of this object.
     * @param animations new animations.
     */
    public void setAnimations(HashMap<Long, Animation> animations){
        this.animations = animations;
    }

    /**
     * @return the current position of this object.
     */
    public Position getPosition(){
        return this.position;
    }

    /**
     * The rigid body is a square. It is used to handle collision between objects.
     * @return the rigid body of this game object, or null, if this object does not have any rigid body.
     */
    public RigidBody getRigidBody(){
        return rigidBody;
    }
}