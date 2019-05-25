package io.github.n3roo.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void move() {
        Position p = new Position();
        assertEquals(0, p.x);
        assertEquals(0, p.y);
    }
}