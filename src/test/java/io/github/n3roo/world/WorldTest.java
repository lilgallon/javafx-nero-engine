package io.github.n3roo.world;

import io.github.n3roo.hud.components.TextHud;
import io.github.n3roo.math.Position;
import io.github.n3roo.world.entity.Entity;
import io.github.n3roo.world.entity.Particle;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WorldTest {

    GraphicsContext g;

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
    @Order(1)
    void addHudComponent() {
        World.addHudComponent(new TextHud("test", new Position()));
        assertEquals(new TextHud("test", new Position()), World.getHudComponents().peek());
        assertEquals(1, World.getHudComponents().size());
        World.addHudComponent(new TextHud("test1", new Position(1, 3)));
        assertEquals(2, World.getHudComponents().size());
    }

    @Test
    @Order(2)
    void removeHudComponent() {
        assertEquals(2, World.getHudComponents().size());

        World.removeHudComponent(new TextHud("test", new Position()));
        assertEquals(1, World.getHudComponents().size());
        assertEquals(new TextHud("test1", new Position(1, 3)), World.getHudComponents().peek());
    }

    @Test
    @Order(3)
    void removeAllHudComponents() {
        World.removeAllHudComponents();
        assertEquals(0, World.getHudComponents().size());
    }

    @Test
    @Order(4)
    void addEntity() {
        World.addEntity(new Entity(new Position()));
        assertEquals(new Entity(new Position()), World.getEntities().peek());
        assertEquals(1, World.getEntities().size());

        World.addEntity(new Entity(new Position(1, 2)));
        assertEquals(2, World.getEntities().size());
    }

    @Test
    @Order(5)
    void removeEntity() {
        assertEquals(2, World.getEntities().size());

        World.removeEntity(new Entity(new Position()));
        assertEquals(1, World.getEntities().size());
        assertEquals(new Entity(new Position(1, 2)), World.getEntities().peek());
    }

    @Test
    @Order(6)
    void removeAllEntities() {
        assertEquals(1, World.getEntities().size());
        World.removeAllEntities();
        assertEquals(0, World.getEntities().size());
    }

    @Test
    @Order(7)
    void updateAndRender() {
        World.addEntity(new Particle(new Position()));
        World.addHudComponent(new TextHud("test", new Position()));
        assertDoesNotThrow(() -> World.update(1d));
        assertDoesNotThrow(() -> World.render(g));
    }
}