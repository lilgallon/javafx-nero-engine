package io.github.n3roo.event;

import io.github.n3roo.event.keyboard.KeyEvent;
import io.github.n3roo.event.mouse.MouseEvent;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class EventManager {

    // The list containing all the closing listeners.
    private static List<OnEngineClosing> listeners = new ArrayList<>();

    /**
     * It initialize all the events handlers on the stage.
     * @param stage the stage where the event are fired.
     */
    public static void init(Stage stage){
        KeyEvent.init(stage.getScene());
        MouseEvent.init(stage.getScene());

        // Close event
        addOnEngineClosingListener(() -> {
            Platform.exit();
            System.exit(0);
        });
        stage.setOnCloseRequest(windowEvent -> listeners.forEach(OnEngineClosing::performAction));
    }

    /**
     * It is used to add a listener that will be called when the engine shuts down.
     * @param listener the listener.
     */
    public static void addOnEngineClosingListener(OnEngineClosing listener){
        listeners.add(0, listener);
    }
}

