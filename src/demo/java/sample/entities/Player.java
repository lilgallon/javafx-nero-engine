package sample.entities;

import javafx.geometry.Point2D;
import io.github.n3roo.events.keyboard.KeyEvent;
import io.github.n3roo.math.Position;
import io.github.n3roo.math.physics.Force;
import io.github.n3roo.world.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Player extends GameObject {

    /**
     * It creates a game object. All the attributes are initialized as well.
     *
     * @param position the game object position.
     */
    public Player(Position position) {
        super(position);
    }

    @Override
    public void update(double delta){
        double inputSensibility = 5d;

        if(KeyEvent.isKeyDown(KeyCode.LEFT))
            addForce(new Force(new Point2D(-inputSensibility * delta, 0d), Force.Mode.Velocity));
        if(KeyEvent.isKeyDown(KeyCode.RIGHT))
            addForce(new Force(new Point2D(+ inputSensibility * delta, 0d), Force.Mode.Velocity));
        if(KeyEvent.isKeyDown(KeyCode.UP))
            addForce(new Force(new Point2D(0d, - inputSensibility * delta), Force.Mode.Velocity));
        if(KeyEvent.isKeyDown(KeyCode.DOWN))
            addForce(new Force(new Point2D(0d, + inputSensibility * delta), Force.Mode.Velocity));
    }

    @Override
    public void render(GraphicsContext g){
        super.render(g);

        g.setFill(Color.GOLD);
        g.fillRect(position.x - 5, position.y - 5, 10, 10);
    }
}
