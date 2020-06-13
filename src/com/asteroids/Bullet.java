package com.asteroids;

import java.awt.*;
import java.io.IOException;

public class Bullet extends Flying_Obj{
    public int life;
    // public String laserSound;

    public Bullet(int shipAngle, int shipX, int shipY) throws IOException {
        super("", 30); //sustituir img por imagen
        this.life = 60;
        this.speed = 10;
        this.angle = shipAngle + 90;
        this.center.x = shipX;
        this.center.y = shipY;
    }

    public void fire() {
        // add play sound method
        this.velocity.dx -= Math.sin(Math.toRadians(this.angle-90)) * this.speed;
        this.velocity.dy += Math.cos(Math.toRadians(this.angle-90)) * this.speed;
    }

    public void advance() {
        super.advance();
        this.life -= 1;
        if (this.life <= 0) {
            this.alive = false;
        }
    }
}
