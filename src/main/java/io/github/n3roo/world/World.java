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

    // Queue containing all the hud components.
    private static Queue<HudComponent> hudComponents = new ConcurrentLinkedQueue<>();

    // Queue containing all the game entities.
    private static Queue<Entity> entities = new ConcurrentLinkedQueue<>();

    /**
     * It updates all the HUDs and then, it updates all the entities.
     * @param delta time since last update.
     */
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
                if(particle.isKilled()) removeEntity(particle);
            }
        }
    }

    /**
     * It clears the screen, draws all the HUDs and then, it draws all the entities.
     * @param g the thing used to draw.
     */
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
     * Used to add an entity.
     * @param entity entity to add.
     */
    public static void addEntity(Entity entity){
        entities.offer(entity);
    }

    /**
     * Used to remove an entity.
     * @param entity entity to add.
     */
    public static void removeEntity(Entity entity){
        entities.remove(entity);
    }

    /**
     * Used to remove all the entities.
     */
    public static void removeAllEntities(){
        entities.clear();
    }

}
