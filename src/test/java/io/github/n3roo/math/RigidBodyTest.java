package io.github.n3roo.math;

import javafx.scene.shape.Polygon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RigidBodyTest {

    @Test
    void getHitbox() {
        Polygon hitbox = new Polygon();
        RigidBody rigidBody = new RigidBody(hitbox, 2.3);
        assertEquals(hitbox, rigidBody.getHitbox());
    }

    @Test
    void getMass() {
        RigidBody rigidBody = new RigidBody(new Polygon(), -1.4);
        assertEquals(-1.4, rigidBody.getMass());
    }

    @Test
    void equals(){
        Polygon hitbox = new Polygon();
        RigidBody rigidBody = new RigidBody(hitbox, 2);

        assertEquals(new RigidBody(hitbox, 2), rigidBody);

        assertNotEquals(new RigidBody(hitbox, -1), rigidBody);
        assertNotEquals(new RigidBody(new Polygon(1, 2), -1), rigidBody);
    }
}