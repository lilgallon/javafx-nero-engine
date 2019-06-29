package io.github.n3roo.math;

public class Position {

    /**
     * Horizontal position
     */
    public double x;

    /**
     * Vertical position
     */
    public double y;

    /**
     * It creates a position with the defined (x; y).
     * @param x horizontal position,
     * @param y vertical position.
     */
    public Position(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * It creates a position at (0; 0).
     */
    public Position(){
        this.x = 0;
        this.y = 0;
    }

    /**
     * Moves the position with the given deltas.
     * @param dx x delta,
     * @param dy y delta.
     * @return this instance.
     */
    public Position move(double dx, double dy){
        this.x += dx;
        this.y += dy;
        return this;
    }

    /**
     * Moves the position with the given deltas.
     * @param position x;y deltas,
     * @return this instance.
     */
    public Position move(Position position){
        this.x += position.x;
        this.y += position.y;
        return this;
    }

    /**
     * @return a copy of the current object.
     */
    public Position copy(){
        return new Position(x, y);
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Position)) return false;
        Position p = (Position) o;
        return p.x == this.x && p.y == this.y;
    }
}
