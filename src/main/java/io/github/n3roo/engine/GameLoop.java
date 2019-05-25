package io.github.n3roo.engine;

import io.github.n3roo.world.World;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameLoop {

    // Lo4j2 logger: its properties are found in "log4j2.properties" in resources/ folder.
    private static final Logger LOGGER = LogManager.getLogger(NeroEngine.class.getName());

    // Used for the game loop
    private static AnimationTimer gameThread;

    // Last tick
    private static long past = -1;

    /**
     * It starts the game loop on the specified stage with the specified FPS.
     * @param stage stage in which the game will be played,
     * @param graphicsContext the thing to draw on the canvas,
     * @param aimedFps the desired amount of frames per seconds.
     */
    public static void start(Stage stage, GraphicsContext graphicsContext, int aimedFps){
        gameThread = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double delta = (past == -1) ? (now - past) / 1e9 : 1f;

                World.update(delta);
                World.render(graphicsContext);

                past = now;
            }
        };
        gameThread.start();
    }

    /**
     * It starts the game loop
     */
    public void start(){
        gameThread.start();
    }

    /**
     * It stops the game loop
     */
    public void stop(){
        gameThread.stop();
    }
}
