package io.github.n3roo.event.keyboard;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class KeyEvent {

    // Map that stores the status of the key that have been pressed at least once
    private static Map<KeyCode, Boolean> keysState = new HashMap<>();

    // If true, then we will record all the key typed in a queue
    private static boolean recordKeyTyped = false;

    // Queue that stores all the key that were typed in order
    private static Queue<String> keysTyped = new LinkedList<>();

    /**
     * It initializes all the events handlers of the specified scene.
     * @param scene the scene where the keyboard events are fired.
     */
    public static void init(Scene scene){
        // This event is fired whenever any key press occurs
        // This is mostly used to obtain raw key presses
        scene.setOnKeyPressed(event -> keysState.put(event.getCode(), true));

        // This event is fired whenever any key release occurs
        scene.setOnKeyReleased(event -> keysState.put(event.getCode(), false));

        // This event is fired when a key is pressed that can be converted into a unicode character
        // This is mostly used to find characters that are typed
        scene.setOnKeyTyped(event -> { if(recordKeyTyped) keysTyped.offer(event.getCharacter()); });
    }

    /**
     * Used to retrieve the state of a key.
     * @param keyCode the javafx.scene.input key code,
     * @return true if the key is being pressed, false otherwise.
     */
    public static boolean isKeyDown(KeyCode keyCode){
        return keysState.getOrDefault(keyCode, false);
    }

    /**
     * @return the next key typed in the queue, and removes it.
     */
    public static String nextKeyTyped(){
        return keysTyped.poll();
    }

    /**
     * @return the next key typed in the queue, but does not remove it.
     */
    public static String peekKeyTyped(){
        return keysTyped.peek();
    }

    /**
     * Used to start recording the keys typed by the user.
     * keysTyped will be populated.
     */
    public static void stopKeyTypedRecord(){
        recordKeyTyped = false;
    }

    /**
     * Used to stop recording the keys typed by the user.
     * Use clearKeyTyped() if you want to clear the keys recorded.
     */
    public static void startKeyTypedRecord(){
        recordKeyTyped = true;
    }

    /**
     * Used to clear the keys recorded.
     */
    public static void clearKeyTyped(){
        keysTyped.clear();
    }
}
