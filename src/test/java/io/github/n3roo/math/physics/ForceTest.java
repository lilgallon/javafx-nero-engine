package io.github.n3roo.math.physics;

import javafx.geometry.Point2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ForceTest {

    @Test
    void getVector() {
        Point2D vector = new Point2D(1, -1.3f);
        Force force = new Force(vector, Force.Mode.Impulse);
        assertEquals(vector, force.getVector());
    }

    @Test
    void getMode() {
        Force force = new Force(new Point2D(1, -1.3f), Force.Mode.Impulse);
        assertEquals(Force.Mode.Impulse, force.getMode());
    }

    @Test
    void equals(){
        Point2D vector = new Point2D(1, -1.3f);
        Force force = new Force(vector, Force.Mode.Persistent);
      //  assertEquals(new Force(vector, Force.Mode.Persistent), force);
        assertEquals(new Force(new Point2D(1, -1.3f), Force.Mode.Persistent), force);
        assertNotEquals(new Force(vector, Force.Mode.Velocity), force);
        assertNotEquals(new Force(new Point2D(1, 2), Force.Mode.Persistent), force);
        assertNotEquals(new Force(new Point2D(1, 2), Force.Mode.Impulse), force);
    }
}