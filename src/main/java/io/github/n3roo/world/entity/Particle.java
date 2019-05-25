package io.github.n3roo.world.entity;

import io.github.n3roo.math.Position;
import io.github.n3roo.util.TimeHelper;
import javafx.scene.canvas.GraphicsContext;

public class Particle extends Entity {

    // Time before it ends (in milliseconds)
    protected long lifeTime;

    // Time lived
    protected TimeHelper timeLived;

    // If set to true, it means that the particle needs to be killed as soon as possible.
    protected boolean killed;

    /**
     * It creates a game object. All the attributes are initialized as well.
     *
     * @param position the game object position,
     * @param lifeTime the time before the particle dies.
     */
    public Particle(Position position, long lifeTime) {
        super(position);
        this.lifeTime = lifeTime;

        killed = false;
        timeLived = new TimeHelper();
        timeLived.start();
    }

    /**
     * It creates a game object. All the attributes are initialized as well.
     * To kill the particle you must use the function "kill()". Otherwise, you have
     * to use the other constructor by indicating the particle lifetime
     *
     * @param position the game object position.
     */
    public Particle(Position position) {
        super(position);
        killed = false;
        timeLived = null;
    }

    /**
     * Update values in this function.
     * You need to call the super function to update the time lived.
     */
    public void update(double delta){
        if(timeLived != null && !killed){
            killed = timeLived.isDelayComplete(lifeTime);
            timeLived.stop();
        }
    }

    /**
     * Render anything in this function.
     * You need to call the super function to render animations.
     */
    public void render(GraphicsContext g){
        super.render(g);
    }

    /**
     * It kills the particle in the next update() call.
     */
    public void kill(){
        killed = true;
    }

    /**
     * @return true if the particle is going to get killed
     */
    public boolean isKilled(){
        return killed;
    }
}
