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

    private static AnimationTimer gameThread;

    // Last tick
    private static long past = -1;

    /**
     * TODO
     * @param stage
     * @param graphicsContext
     * @param aimedFps
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

    public void start(){
        gameThread.start();
    }

    public void stop(){
        gameThread.stop();
    }
}
