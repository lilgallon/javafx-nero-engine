package io.github.n3roo.events.mouse;

import javafx.scene.Scene;

public class MouseEvent {

    public static void init(Scene scene){
        // This event is fired whenever the mouse is moved (the scene must be focused)
        scene.setOnMouseMoved(event -> Mouse.updatePosition(event.getSceneX(), event.getSceneY()));

        // This event is fired whenever the mouse enters the scene
        scene.setOnMouseEntered(event -> Mouse.isIn(true));

        // This event is fired whenever the mouse leaves the scene
        scene.setOnMouseExited(event -> Mouse.isIn(false));

        // This event is fired whenever the mouse is pressed
        scene.setOnMousePressed(event -> Mouse.isPressed(true));

        // This event is fired whenever the mouse is pressed
        scene.setOnMouseReleased(event -> Mouse.isPressed(false));

        // TODO: implement all the drag methods
    }
}
