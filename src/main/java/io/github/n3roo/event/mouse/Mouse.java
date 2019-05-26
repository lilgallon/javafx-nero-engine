package io.github.n3roo.event.mouse;

public class Mouse {

    // x position of the mouse in the scene
    private static double x;

    // y position of the mouse in the scene
    private static double y;

    // true whenever the mouse is in the scene (useful only if we use multiple scenes)
    private static boolean in = false;

    // true whenever the mouse is being pressed
    private static boolean pressed = false;

    public static void reset(){
        x = 0;
        y = 0;
        in = false;
        pressed = false;
    }

    /**
     * It updates the mouse position by overriding its current position.
     * @param x the new x position on the scene,
     * @param y the new y position on the scene.
     */
    static void updatePosition(double x, double y){
        Mouse.x = x;
        Mouse.y = y;
    }

    /**
     * It updates whenever the mouse is in the scene or not.
     * @param isIn true means that the mouse is in the scene.
     */
    static void isIn(boolean isIn){
        Mouse.in = isIn;
    }

    /**
     * It updates whenever the mouse is pressed or not.
     * @param isPressed true means that the mouse is being pressed.
     */
    static void isPressed(boolean isPressed){
        Mouse.pressed = isPressed;
    }

    /**
     * @return x position of the mouse in the scene.
     */
    public static double getX(){
        return Mouse.x;
    }

    /**
     * @return y position of the mouse in the scene.
     */
    public static double getY(){
        return Mouse.y;
    }

    /**
     * @return true whenever the mouse is in the scene.
     */
    public static boolean isIn(){
        return Mouse.in;
    }

    /**
     * @return true whenever the mouse is being pressed.
     */
    public static boolean isPressed(){
        return Mouse.pressed;
    }
}
