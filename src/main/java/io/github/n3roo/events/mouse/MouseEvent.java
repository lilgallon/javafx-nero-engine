package io.github.n3roo.events.mouse;

import javafx.scene.Scene;

public class MouseEvent {

    public static void init(Scene scene){
        // This event is fired whenever the mouse is moved (the scene must be focused)
        scene.setOnMouseMoved(event -> Mouse.updatePosition(event.getSceneX(), event.getSceneY()));
    }
}
