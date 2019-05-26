package io.github.n3roo.event.keyboard;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class KeyEventTest {

    private Stage stage;

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

        KeyEvent.init(stage.getScene());
    }

    @Test
    @Order(1)
    void beforeEvents() {
        assertNull(KeyEvent.peekKeyTyped());
        assertNull(KeyEvent.nextKeyTyped());
        assertFalse(KeyEvent.isKeyDown(KeyCode.A));
    }

    @Test
    @Order(2)
    void keyEvents() {
        // Press / release
        fireEvent(javafx.scene.input.KeyEvent.KEY_PRESSED, KeyCode.A);
        assertTrue(KeyEvent.isKeyDown(KeyCode.A));
        fireEvent(javafx.scene.input.KeyEvent.KEY_RELEASED, KeyCode.A);
        assertFalse(KeyEvent.isKeyDown(KeyCode.A));

        // Type
        KeyEvent.startKeyTypedRecord();
        fireEvent(javafx.scene.input.KeyEvent.KEY_TYPED, KeyCode.H);
        fireEvent(javafx.scene.input.KeyEvent.KEY_TYPED, KeyCode.E);
        fireEvent(javafx.scene.input.KeyEvent.KEY_TYPED, KeyCode.L);
        fireEvent(javafx.scene.input.KeyEvent.KEY_TYPED, KeyCode.L);
        fireEvent(javafx.scene.input.KeyEvent.KEY_TYPED, KeyCode.O);
        fireEvent(javafx.scene.input.KeyEvent.KEY_TYPED, KeyCode.EXCLAMATION_MARK);
        fireEvent(javafx.scene.input.KeyEvent.KEY_TYPED, KeyCode.MINUS);
        KeyEvent.stopKeyTypedRecord();

        assertEquals("h", KeyEvent.nextKeyTyped());
        assertEquals("e", KeyEvent.nextKeyTyped());
        assertEquals("l", KeyEvent.nextKeyTyped());
        assertEquals("l", KeyEvent.nextKeyTyped());
        assertEquals("o", KeyEvent.nextKeyTyped());
        assertEquals("ȅ", KeyEvent.nextKeyTyped()); // yes, JavaFX KeyCode mapping sucks

        assertEquals("-", KeyEvent.peekKeyTyped());
        KeyEvent.clearKeyTyped();
        assertNull(KeyEvent.nextKeyTyped());

        // "Specials" characters are broken with javaFX key code. For example: the code of ! is 517, whereas it should
        // be 33.
    }

    private void fireEvent(EventType<javafx.scene.input.KeyEvent> keyEvent, KeyCode code){
        Event.fireEvent(stage.getScene(),
                new javafx.scene.input.KeyEvent(
                        keyEvent, code.getChar(), code.getName(), code,
                        false, false, false, false)
        );
    }
}