package io.github.n3roo.world.entity;

import io.github.n3roo.math.Position;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class ParticleTest {

    GraphicsContext g;

    @Start
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        stage.setScene(new Scene(root));
        stage.setTitle("test");
        Canvas canvas = new Canvas(10, 10);
        g = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        stage.show();
    }

    @Test
    void kill() {
        Particle particle = new Particle(new Position(), 600);
        try {
            Thread.sleep(400);
            assertFalse(particle.isKilled());
            particle.kill();
            assertTrue(particle.isKilled());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void isKilled() {
        Particle particle = new Particle(new Position(), 600);
        try {
            Thread.sleep(400);
            assertFalse(particle.isKilled());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(250);
            assertTrue(particle.isKilled());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        particle = new Particle(new Position());
        assertFalse(particle.isKilled());
    }

    @Test
    void updateAndRender(){
        Particle particle = new Particle(new Position());
        assertDoesNotThrow(() -> particle.update(1d));
        assertDoesNotThrow(() -> particle.render(g));
    }
}