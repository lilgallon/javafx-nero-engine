package io.github.n3roo.graphics;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class AnimationTest {

    private GraphicsContext g;

    @Start
    public void start(Stage stage) {
        Group root = new Group();
        stage.setScene(new Scene(root));
        stage.setTitle("test");
        Canvas canvas = new Canvas(10, 10);
        g = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        stage.show();
    }

    @Test
    void play() {
        LinkedList<String> spriteNames = new LinkedList<>();
        spriteNames.add("images/nero-engine-transparent.png");
        spriteNames.add("images/nero-engine-text-transparent.png");
        Animation animation = new Animation(spriteNames, 2);

        animation.play();
        assertEquals(animation.getFrames().get(0), animation.getImage());

        try {
            Thread.sleep(600);
            animation.play();
            assertEquals(animation.getFrames().get(1), animation.getImage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(600);
            animation.play();
            assertEquals(animation.getFrames().get(0), animation.getImage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void changeSprites() {
        LinkedList<String> spriteNames = new LinkedList<>();
        spriteNames.add("images/nero-engine-transparent.png");
        spriteNames.add("images/nero-engine-text-transparent.png");
        Animation animation = new Animation(spriteNames, 2);

        assertThrows(NullPointerException.class, () -> animation.changeSprites(null));
        final LinkedList<String> sn = new LinkedList<>();
        assertThrows(IllegalArgumentException.class, () -> animation.changeSprites(sn));
    }

    @Test
    void getSet() {
        LinkedList<String> spriteNames = new LinkedList<>();
        spriteNames.add("images/nero-engine-transparent.png");
        Animation animation = new Animation(spriteNames, 2);

        animation.setFps(5);
        assertEquals(5, animation.getFps());

        assertTrue(animation.isLooping());
        animation.setLoop(false);
        assertFalse(animation.isLooping());

        assertEquals(1, animation.getFrames().size());
    }
}