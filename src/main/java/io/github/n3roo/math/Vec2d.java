package io.github.n3roo.math;

public class Vec2d {

    public double x;
    public double y;

    public Vec2d() {
        this.x = 0;
        this.y = 0;
    }

    public Vec2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vec2d add(double value) {
        this.x += value;
        this.y += value;
        return this;
    }

    public Vec2d multiply(double value) {
        this.x *= value;
        this.y *= value;
        return this;
    }

    public Vec2d add(double dx, double dy) {
        this.x += dx;
        this.y += dy;
        return this;
    }
}
