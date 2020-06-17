package com.asteroids;

public class PointDouble {
    public double x,y;

    public PointDouble() {
        this.x = 0;
        this.y = 0;
    }

    public PointDouble(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

}
