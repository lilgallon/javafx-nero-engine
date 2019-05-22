package io.github.n3roo.world;

import io.github.n3roo.hud.HudComponent;
import io.github.n3roo.math.physics.Physics;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class World {

    private static Queue<HudComponent> hudComponents = new ConcurrentLinkedQueue<>();

    private static Queue<GameObject> gameObjects = new ConcurrentLinkedQueue<>();

    public static void update(double delta){
        for(HudComponent hudComponent : hudComponents){
            hudComponent.update(delta);
        }

        for(GameObject gameObject : gameObjects){
            gameObject.update(delta);
            Physics.handleForces(gameObject);
        }
    }

    public static void render(GraphicsContext g){
        g.setFill(Color.WHITE);
        g.fillRect(0, 0, g.getCanvas().getWidth(), g.getCanvas().getHeight());

        for(HudComponent hudComponent : hudComponents){
            hudComponent.render(g);
        }

        for(GameObject gameObject : gameObjects){
            gameObject.render(g);
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
     * @param gameObject game object to add.
     */
    public static void addGameObject(GameObject gameObject){
        gameObjects.offer(gameObject);
    }

    /**
     * Used to remove a game object.
     * @param gameObject game object to add.
     */
    public static void removeGameObject(GameObject gameObject){
        gameObjects.remove(gameObject);
    }

    /**
     * Used to remove all the game objects.
     */
    public static void removeAllGameObjects(){
        gameObjects.clear();
    }

}
