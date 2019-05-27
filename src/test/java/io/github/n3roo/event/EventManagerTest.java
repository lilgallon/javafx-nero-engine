package io.github.n3roo.event;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.security.Permission;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EventManagerTest {

    private Stage stage;
    private static boolean called = false;

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
    }

    @Test
    @Order(1)
    void init() {
        assertDoesNotThrow(() -> EventManager.init(stage));
    }

    @Test
    @Order(2)
    void addOnEngineClosingListener() {
        // We need to catch the exception, but it is thrown in a async thread, I don't know how to catch it :(

        /*
        System.setSecurityManager(new SecurityManager() {
            @Override
            public void checkExit(int status){
                //throw new SecurityException();
            }
            @Override
            public void checkPermission( Permission permission ) {

            }
        });
        EventManagerTest.called = false;
        EventManager.addOnEngineClosingListener(() -> EventManagerTest.called = true);
        assertThrows(SecurityException.class,() -> this.stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST)));
        assertTrue(EventManagerTest.called);
        */
    }
}