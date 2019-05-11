package io.github.n3roo.hud;

public abstract class HudComponent {

    /**
     * Use this method to update the component if needed.
     * @param delta delta time since last render.
     */
    public abstract void update(double delta);

    /**
     * Use this method to render the component.
     */
    public abstract void render();
}
