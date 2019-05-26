package io.github.n3roo.hud.components;

import io.github.n3roo.math.Position;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class TextHudTest {

    private GraphicsContext g;

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
    void updateAndRender() {
        TextHud textHud = new TextHud("yea", new Position());
        assertDoesNotThrow(() -> textHud.update(1d));
        assertDoesNotThrow(() -> textHud.render(g));
    }

    @Test
    void setAndGet() {
        TextHud textHud = new TextHud("", new Position());

        textHud.setColor(Color.RED);
        assertEquals(Color.RED, textHud.getColor());

        textHud.setFont(new Font(12));
        assertEquals(new Font(12), textHud.getFont());

        textHud.setMaxWidth(123.456);
        assertEquals(123.456, textHud.getMaxWidth());

        textHud.setPosition(new Position(1, -2.2));
        assertEquals(new Position(1, -2.2), textHud.getPosition());

        textHud.setText("heyYa_8");
        assertEquals("heyYa_8", textHud.getText());
    }

    @Test
    void equals() {
        TextHud textHud = new TextHud(new Font(12), Color.RED, "bA", new Position(), 1.2);
        assertEquals(new TextHud(new Font(12), Color.RED, "bA", new Position(), 1.2), textHud);
        assertNotEquals(new TextHud(new Font(11), Color.RED, "bA", new Position(), 1.2), textHud);
        assertNotEquals(new TextHud(new Font(12), Color.BLACK, "bA", new Position(), 1.2), textHud);
        assertNotEquals(new TextHud(new Font(12), Color.RED, "b", new Position(), 1.2), textHud);
        assertNotEquals(new TextHud(new Font(12), Color.RED, "bA", new Position(1, 0), 1.2), textHud);
        assertNotEquals(new TextHud(new Font(12), Color.RED, "bA", new Position(), 1), textHud);
        assertNotEquals(new TextHud(new Font(11), Color.BLACK, "b", new Position(1, 1), 0), textHud);
    }
}