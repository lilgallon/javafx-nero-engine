package io.github.n3roo.math;

import io.github.n3roo.math.body.Body;
import javafx.scene.shape.Polygon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BodyTest {

    @Test
    void getHitbox() {
        Polygon hitbox = new Polygon();
        Body body = new Body(hitbox, 2.3);
        assertEquals(hitbox, body.getHitbox());
    }

    @Test
    void getMass() {
        Body body = new Body(new Polygon(), -1.4);
        assertEquals(-1.4, body.getMass());
    }

    @Test
    void equals(){
        Polygon hitbox = new Polygon();
        Body body = new Body(hitbox, 2);

        assertEquals(new Body(hitbox, 2), body);

        assertNotEquals(new Body(hitbox, -1), body);
        assertNotEquals(new Body(new Polygon(1, 2), -1), body);
    }
}