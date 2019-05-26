package io.github.n3roo.event.mouse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MouseTest {

    @BeforeAll
    static void init() {
        Mouse.reset();
    }

    @Test
    void isIn() {
        assertFalse(Mouse.isIn());
        Mouse.isIn(true);
        assertTrue(Mouse.isIn());
        Mouse.isIn(false);
        assertFalse(Mouse.isIn());
    }

    @Test
    void isPressed() {
        assertFalse(Mouse.isPressed());
        Mouse.isPressed(true);
        assertTrue(Mouse.isPressed());
    }

    @Test
    void getSetXY() {
        assertEquals(0, Mouse.getX());
        assertEquals(0, Mouse.getY());
        Mouse.updatePosition(1.2, 2.3);
        assertEquals(1.2, Mouse.getX());
        assertEquals(2.3, Mouse.getY());
    }
}