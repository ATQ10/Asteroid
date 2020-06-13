package com.asteroids;

import java.awt.*;
import java.io.IOException;

public class Medium_Asteroid extends Asteroid{
    public final int ROCK_SPIN = -2;

    public Medium_Asteroid() throws IOException {
        super("", 5);
        this.speed = 2;
        this.velocity.dx += Math.cos(Math.toRadians(this.direction)) * this.speed;
        this.velocity.dx += Math.cos(Math.toRadians(this.direction)) * this.speed;
    }
    @Override
    public void advance() {
        super.advance();
        this.angle += ROCK_SPIN;
    }

    @Override
    public void break_apart() throws IOException {
        Small_Asteroid small1 = new Small_Asteroid();
        small1.center.x = this.center.x;
        small1.center.y = this.center.y;
        small1.velocity.dx = this.velocity.dy - 1.5f;
        small1.velocity.dy = this.velocity.dy + 1.5f;

        Small_Asteroid small2 = new Small_Asteroid();
        small2.center.x = this.center.x;
        small2.center.y = this.center.y;
        small2.velocity.dx = this.velocity.dy - 1.5f;
        small2.velocity.dy = this.velocity.dy - 1.5f;

        AsteroidsGame.asteroids.add(small1);
        AsteroidsGame.asteroids.add(small2);

        this.alive = false;
    }
}
