package io.github.n3roo.engine;

import io.github.n3roo.events.EventManager;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NeroEngine {

    // Lo4j2 logger: its properties are found in "log4j2.properties" in resources/ folder.
    private static final Logger LOGGER = LogManager.getLogger(NeroEngine.class.getName());

    public static void init(Stage stage){
        // Data
        String windowName = "Nero engine 0.1";
        int windowWidth = 512;
        int windowHeight = 512;
        int fps = 60;

        // Set up the stage
        Group root = new Group();
        stage.setScene(new Scene(root));
        stage.setTitle(windowName);

        // Set up the engine
        Canvas canvas = new Canvas(windowWidth, windowHeight);
        root.getChildren().add(canvas);

        // Start the engine components
        LOGGER.info("Creating event handlers");
        EventManager.init(stage);

        LOGGER.info("Starting game loop");
        GameLoop.start(stage, canvas.getGraphicsContext2D(), fps);

        LOGGER.info("Showing stage");
        stage.show();
    }
}
