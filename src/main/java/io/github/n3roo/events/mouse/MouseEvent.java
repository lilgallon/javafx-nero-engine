package io.github.n3roo.events.mouse;

import javafx.scene.Scene;
import javafx.scene.input.MouseDragEvent;

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
        // This event is fired whenever a full press-drag-release gesture enters the scene.
        scene.setOnMouseDragEntered(MouseEvent::dragEntered);
        // This event is fired whenever a full press-drag-release gesture leaves the scene.
        scene.setOnMouseDragExited(MouseEvent::dragExited);
        // This event is fired whenever a mouse button is pressed on the scene and then dragged.
        scene.setOnMouseDragged(MouseEvent::dragged);
        // This event is fired whenever a full press-drag-release gesture progresses within the scene.
        scene.setOnMouseDragOver(MouseEvent::dragOver);
        // This event is fired whenever a full press-drag-release gesture ends (by releasing mouse button) within the scene.
        scene.setOnMouseDragReleased(MouseEvent::dragReleased);
    }

    /**
     * Called when a full press-drag-release gesture enters the scene.
     * @param event all the information that you need.
     */
    protected static void dragEntered(MouseDragEvent event){}

    /**
     * Called when a full press-drag-release gesture leaves the scene.
     * @param event all the information that you need.
     */
    protected static void dragExited(MouseDragEvent event){}

    /**
     * Called when a mouse button is pressed on the scene and then dragged.
     * @param event all the information that you need.
     */
    protected static void dragged(javafx.scene.input.MouseEvent event){}

    /**
     * Called when a full press-drag-release gesture progresses within the scene.
     * @param event all the information that you need.
     */
    protected static void dragOver(MouseDragEvent event){}

    /**
     * Called when a full press-drag-release gesture ends (by releasing mouse button) within the scene.
     * @param event all the information that you need.
     */
    protected static void dragReleased(MouseDragEvent event){}
}
