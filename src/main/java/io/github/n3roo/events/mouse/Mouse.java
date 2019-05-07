package io.github.n3roo.events.mouse;

public class Mouse {

    // x position of the mouse in the scene
    private static double x;

    // y position of the mouse in the scene
    private static double y;

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
     * @return x position of the mouse in the scene.
     */
    public static double getX(){
        return x;
    }

    /**
     * @return y position of the mouse in the scene.
     */
    public static double getY(){
        return y;
    }
}
