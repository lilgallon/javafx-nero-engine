package io.github.n3roo.hud.components;

import io.github.n3roo.hud.HudComponent;
import io.github.n3roo.math.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TextHud extends HudComponent {

    // The text font
    private Font font;

    // The text color
    private Color color;

    // The text content
    private String text;

    // The text position
    private Position position;

    // The text max width
    private double maxWidth;

    /**
     * Renders a text on the specified position.
     * @param font the text font,
     * @param color the text color,
     * @param text the text content,
     * @param position the text position,
     * @param maxWidth maximum width the text string can have (< 0 is ignored).
     */
    public TextHud(Font font, Color color, String text, Position position, double maxWidth){
        this.font = font;
        this.color = color;
        this.text = text;
        this.position = position;
        this.maxWidth = maxWidth;
    }

    /**
     * Renders a text on the specified position.
     * The color is set to black, and there is no max width.
     * @param font the text font,
     * @param text the text content,
     * @param position the text position.
     */
    public TextHud(Font font, String text, Position position){
        this(font, Color.BLACK, text, position, -1);
    }

    /**
     * Renders a text on the specified position.
     * The color is set to black, there is no max width, and the font is the one by default in JavaFX 12 (System).
     * @param text the text content,
     * @param position the text position.
     */
    public TextHud(String text, Position position){
        this(new Font(12), text, position);
    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void render(GraphicsContext g) {
        g.setFill(color);
        g.setFont(font);
        if(maxWidth > 0) g.fillText(text, position.x, position.y, maxWidth);
        else g.fillText(text, position.x, position.y);
    }

    /**
     * Change the text font.
     * @param font new text font.
     */
    public void setFont(Font font){
        this.font = font;
    }

    /**
     * Change the text color.
     * @param color new text color.
     */
    public void setColor(Color color){
        this.color = color;
    }

    /**
     * Change the text content.
     * @param text new text content.
     */
    public void setText(String text){
        this.text = text;
    }

    /**
     * Change the text position.
     * @param position new text position.
     */
    public void setPosition(Position position){
        this.position = position;
    }

    /**
     * Change the text max width (< 0 is ignored)
     * @param maxWidth new text max width.
     */
    public void setMaxWidth(double maxWidth){
        this.maxWidth = maxWidth;
    }

    /**
     * @return the text font.
     */
    public Font getFont(){
        return font;
    }

    /**
     * @return the text color.
     */
    public Color getColor(){
        return color;
    }

    /**
     * @return the text content.
     */
    public String getText(){
        return text;
    }

    /**
     * @return the text position.
     */
    public Position getPosition(){
        return position;
    }

    /**
     * @return the text max width.
     */
    public double getMaxWidth(){
        return maxWidth;
    }
}
