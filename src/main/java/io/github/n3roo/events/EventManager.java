package io.github.n3roo.events;

import io.github.n3roo.events.keyboard.KeyEvent;
import io.github.n3roo.events.mouse.MouseEvent;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private static List<OnEngineClosing> listeners = new ArrayList<>();

    public static void init(Stage stage){
        KeyEvent.init(stage.getScene());
        MouseEvent.init(stage.getScene());

        // Close events
        addOnEngineClosingListener(() -> {
            Platform.exit();
            System.exit(0);
        });
        stage.setOnCloseRequest(windowEvent -> listeners.forEach(OnEngineClosing::performAction));
    }

    public static void addOnEngineClosingListener(OnEngineClosing listener){
        listeners.add(listener);
    }
}

