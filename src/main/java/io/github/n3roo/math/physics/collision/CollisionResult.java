package io.github.n3roo.math.physics.collision;

import io.github.n3roo.math.Vec2d;
import io.github.n3roo.math.body.shapes.Shape;

public class CollisionResult {

    public Shape reference;
    public Shape target;
    public Boolean overlap;
    public Vec2d displacement;

    public CollisionResult(Shape reference, Shape target) {
        this.reference = reference;
        this.target = target;
        this.overlap = null;
        this.displacement = null;
    }
}
