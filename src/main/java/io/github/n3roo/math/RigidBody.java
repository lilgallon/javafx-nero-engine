package io.github.n3roo.math;

import javafx.scene.shape.Polygon;

public class RigidBody {

    /**
     * Shape of this rigid body.
     */
    private Polygon hitbox;

    /**
     * Mass of this rigid body.
     */
    private double mass;

    /**
     * It creates a collision box. It is a square, with the (x, y) position being the top left corner.
     * @param hitbox the collision polygon,
     * @param mass the mass of the rigid body. (value < 0 means an infinite mass)
     */
    public RigidBody(Polygon hitbox, double mass){
        this.hitbox = hitbox;
        this.mass = mass;
    }

    /**
     * @return the shape of this rigid body.
     */
    public Polygon getHitbox(){
        return this.hitbox;
    }

    /**
     * @return the mass of this rigid body.
     */
    public double getMass(){
        return this.mass;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof RigidBody)) return false;
        RigidBody rb = (RigidBody) o;
        return rb.getHitbox().equals(hitbox) && rb.getMass() == mass;
    }
}
