package io.github.n3roo.world;

import io.github.n3roo.hud.HudComponent;
import javafx.scene.canvas.GraphicsContext;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class World {

    private static Queue<HudComponent> hudComponents = new ConcurrentLinkedQueue<>();

    public static void update(double delta){
        for(HudComponent hudComponent : hudComponents){
            hudComponent.update(delta);
        }
    }

    public static void render(GraphicsContext g){
        for(HudComponent hudComponent : hudComponents){
            hudComponent.render(g);
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
}
