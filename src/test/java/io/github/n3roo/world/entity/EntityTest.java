package io.github.n3roo.world.entity;

import io.github.n3roo.graphics.Animation;
import io.github.n3roo.math.Position;
import io.github.n3roo.math.physics.Force;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class EntityTest {

    @Start
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        stage.setScene(new Scene(root));
        stage.setTitle("test");
        root.getChildren().add(new Canvas(10, 10));
        stage.show();
    }

    @Test
    void addForce() {
        Entity entity = new Entity(new Position());
        entity.addForce(new Force(new Point2D(0, 1), Force.Mode.Impulse));
        assertEquals(new Force(new Point2D(0, 1), Force.Mode.Impulse), entity.getForces().peek());
    }

    @Test
    void setGetForces() {
        Stack<Force> forces = new Stack<>();
        forces.push(new Force(new Point2D(0, 0), Force.Mode.Velocity));
        forces.push(new Force(new Point2D(1, 0), Force.Mode.Impulse));
        forces.push(new Force(new Point2D(0, -1), Force.Mode.Persistent));
        Entity entity = new Entity(new Position());
        entity.setForces(forces);
        assertEquals(forces, entity.getForces());
    }

    @Test
    void clearForces() {
        Stack<Force> forces = new Stack<>();
        forces.push(new Force(new Point2D(0, 0), Force.Mode.Velocity));
        forces.push(new Force(new Point2D(1, 0), Force.Mode.Impulse));
        forces.push(new Force(new Point2D(0, -1), Force.Mode.Persistent));
        Entity entity = new Entity(new Position());
        entity.setForces(forces);
        entity.clearForces();
        assertEquals(new Stack<>(), entity.getForces());
    }

    @Test
    void removePersistentForce() {
        Stack<Force> forces = new Stack<>();
        forces.push(new Force(new Point2D(0, 0), Force.Mode.Velocity));
        forces.push(new Force(new Point2D(1, 0), Force.Mode.Impulse));
        forces.push(new Force(new Point2D(0, -1), Force.Mode.Persistent));


        Stack<Force> expected = new Stack<>();
        expected.push(new Force(new Point2D(0, 0), Force.Mode.Velocity));
        expected.push(new Force(new Point2D(1, 0), Force.Mode.Impulse));

        Entity entity = new Entity(new Position());
        entity.setForces(forces);
        entity.removePersistentForce(new Force(new Point2D(0, -1), Force.Mode.Persistent));
        assertEquals(expected, entity.getForces());
    }

    @Test
    void setGetMovement() {
        Entity entity = new Entity(new Position());
        assertEquals(new Point2D(0, 0), entity.getMovement());
        entity.setMovement(new Point2D(1, -2));
        assertEquals(new Point2D(1, -2), entity.getMovement());
    }

    @Test
    void move() {
        Entity entity = new Entity(new Position());
        assertEquals(new Position(), entity.getPosition());
        entity.move(2, -3);
        assertEquals(new Position(2, -3), entity.getPosition());
        entity.move(-2, 1);
        assertEquals(new Position(0, -2), entity.getPosition());
    }

    @Test
    void setGetPosition() {
        Entity entity = new Entity(new Position());
        assertEquals(new Position(), entity.getPosition());
        entity.setPosition(1, 3);
        assertEquals(new Position(1, 3), entity.getPosition());
        entity.setPosition(-1.2, 0.3);
        assertEquals(new Position(-1.2, 0.3), entity.getPosition());
    }

    @Test
    void setCurrentAnimation() {
        Entity entity = new Entity(new Position());
        assertEquals(0, entity.getCurrentAnimation());
        entity.setCurrentAnimation(3);
        assertEquals(3, entity.getCurrentAnimation());
    }

    @Test
    void setGetAnimations() {
        Entity entity = new Entity(new Position());
        entity.setAnimations(new HashMap<>());
        assertEquals(new HashMap<>(), entity.getAnimations());

        HashMap<Long, Animation> animations = new HashMap<>();
        LinkedList<String> spriteNames = new LinkedList<>();
        spriteNames.add("images/nero-engine-transparent.png");
        animations.put(0L, new Animation(spriteNames, 3));
        animations.put(-1L, new Animation(spriteNames, 5));
        entity.setAnimations(animations);
        assertEquals(animations, entity.getAnimations());
    }

    @Test
    void getRigidBody() {
        Entity entity = new Entity(new Position());
        assertNull(entity.getRigidBody());
    }
}