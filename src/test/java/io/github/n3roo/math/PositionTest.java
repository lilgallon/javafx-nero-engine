package io.github.n3roo.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void instantiation(){
        Position p = new Position();
        // Default instantiation at (0; 0)
        assertEquals(0, p.x);
        assertEquals(0, p.y);

        p = new Position(0.4, -1);
        assertEquals(0.4, p.x);
        assertEquals(-1, p.y);
    }

    @Test
    void equals(){
        Position p = new Position(1, 2);
        assertEquals(new Position(1, 2), p);
        // let's change x ...
        p.x = 3;
        assertEquals(new Position(3, 2), p);
        // ... why not y as well ? ...
        p.y = -5;
        assertEquals(new Position(3, -5), p);
        // ... and both at the same time !
        p.x --; p.y --;
        assertEquals(new Position(2, -6), p);

        assertNotEquals(new Position(-1, -6), p);
        assertNotEquals(new Position(-2, -5), p);
        assertNotEquals(new Position(-1, -1), p);
    }

    @Test
    void move() {
        // This test depends on equals() test !

        Position p = new Position(0, 0);
        // Move returns the position ...
        assertEquals(new Position(1.1, -2.5), p.move(1.1, -2.5));
        // ...but it is updated as well
        assertEquals(new Position(1.1, -2.5), p);
        // let's try one more time
        assertEquals(new Position(1, -2), p.move(-0.1, 0.5));
    }
}