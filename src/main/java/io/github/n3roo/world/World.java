package io.github.n3roo.world;

import io.github.n3roo.hud.HudComponent;
import io.github.n3roo.math.physics.Physics;
import io.github.n3roo.world.entity.Entity;
import io.github.n3roo.world.entity.Particle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class World {

    private static Queue<HudComponent> hudComponents = new ConcurrentLinkedQueue<>();

    private static Queue<Entity> entities = new ConcurrentLinkedQueue<>();

    public static void update(double delta){
        for(HudComponent hudComponent : hudComponents){
            hudComponent.update(delta);
        }

        for(Entity entity : entities){
            entity.update(delta);
            Physics.handleForces(entity);

            // Remove the particle if it was killed
            if(entity instanceof Particle){
                Particle particle = (Particle) entity;
                if(particle.isKilled()) removeGameObject(particle);
            }
        }
    }

    public static void render(GraphicsContext g){
        g.setFill(Color.WHITE);
        g.fillRect(0, 0, g.getCanvas().getWidth(), g.getCanvas().getHeight());

        for(HudComponent hudComponent : hudComponents){
            hudComponent.render(g);
        }

        for(Entity entity : entities){
            entity.render(g);
        }
    }

    /**
     * Used to add an hud component.
     * @param hudComponent hud component to add.
     */
    public static void addHudComponent(HudComponent hudComponent){
        hudComponents.offer(hudComponent);
    }

    /**
     * Used to remove an hud component.
     * @param hudComponent hud component to add.
     */
    public static void removeHudComponent(HudComponent hudComponent){
        hudComponents.remove(hudComponent);
    }

    /**
     * Used to remove all the hud components.
     */
    public static void removeAllHudComponents(){
        hudComponents.clear();
    }

    /**
     * Used to add a game object.
     * @param entity game object to add.
     */
    public static void addGameObject(Entity entity){
        entities.offer(entity);
    }

    /**
     * Used to remove a game object.
     * @param entity game object to add.
     */
    public static void removeGameObject(Entity entity){
        entities.remove(entity);
    }

    /**
     * Used to remove all the game objects.
     */
    public static void removeAllGameObjects(){
        entities.clear();
    }

}
