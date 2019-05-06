package io.github.n3roo.events;

import io.github.n3roo.events.keyboard.KeyEvent;
import javafx.scene.Scene;

public class EventManager {

    public static void init(Scene scene){
        KeyEvent.init(scene);
    }
}
