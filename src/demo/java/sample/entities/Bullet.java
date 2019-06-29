package sample.entities;

import io.github.n3roo.math.Position;
import io.github.n3roo.math.body.Body;
import io.github.n3roo.math.body.shapes.Rectangle;
import io.github.n3roo.math.physics.Force;
import io.github.n3roo.world.entity.Particle;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bullet extends Particle {

    private double radius;
    private boolean fired;

    public Bullet(Position position, double radius) {
        super(position);
        this.radius = radius;
        this.fired = false;

        this.body = new Body(
                new Rectangle(position.copy(), radius*2, radius*2, 0, true),
                1
        );
    }

    public void fire(Point2D impulse) {
        addForce(new Force(impulse, Force.Mode.Impulse));
        fired = true;
    }

    @Override
    public void render(GraphicsContext g){
        g.setFill(Color.BLACK);
        g.fillOval(position.x - radius, position.y - radius, radius*2, radius*2);

        g.setStroke(Color.RED);
        this.body.getHitbox().draw(g);
    }

    @Override
    public void update(double delta){
        if(getForces().size() == 0 && getMovement().getX() == 0 && getMovement().getY() == 0 && fired){
            kill();
        }
    }
}
