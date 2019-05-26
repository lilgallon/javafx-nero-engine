package io.github.n3roo.event.mouse;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MouseEventTest {

    private Stage stage;

    @BeforeAll
    static void init() {
        Mouse.reset();
    }

    @Start
    public void start(Stage stage) {
        Group root = new Group();
        stage.setScene(new Scene(root));
        stage.setTitle("test");
        Canvas canvas = new Canvas(10, 10);
        root.getChildren().add(canvas);
        stage.show();
        stage.requestFocus();

        this.stage = stage;

        MouseEvent.init(stage.getScene());
    }

    @Test
    @Order(1)
    void beforeEvents() {
        assertEquals(0, Mouse.getX());
        assertEquals(0, Mouse.getY());
        assertFalse(Mouse.isIn());
        assertFalse(Mouse.isPressed());
    }

    @Test
    @Order(2)
    void mouseEvents(){
        // I can't find a way to click on a specified position ... :(
        fireEvent(javafx.scene.input.MouseEvent.MOUSE_PRESSED);
        assertTrue(Mouse.isPressed());
        fireEvent(javafx.scene.input.MouseEvent.MOUSE_RELEASED);
        assertFalse(Mouse.isPressed());
        fireEvent(javafx.scene.input.MouseEvent.MOUSE_EXITED);
        assertFalse(Mouse.isIn());
        fireEvent(javafx.scene.input.MouseEvent.MOUSE_ENTERED);
        assertTrue(Mouse.isIn());
        fireEvent(javafx.scene.input.MouseEvent.MOUSE_MOVED, 1, 2);
        assertEquals(1, Mouse.getX());
        assertEquals(2, Mouse.getY());
    }

    private void fireEvent(EventType<javafx.scene.input.MouseEvent> mouseEvent){
        fireEvent(mouseEvent, 0, 0);
    }

    private void fireEvent(EventType<javafx.scene.input.MouseEvent> mouseEvent, int x, int y){
        Event.fireEvent(stage.getScene(),
                new javafx.scene.input.MouseEvent(
                        mouseEvent,
                        x, y,
                        0, 0,
                        MouseButton.PRIMARY, 1,
                        true, true, true, true,
                        true, true, true, true, true, true, null)
        );
    }

}