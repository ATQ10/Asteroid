package com.asteroids;

import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class Large_Asteroid extends Asteroid{
    public final int ROCK_SPIN = 1;

    public Large_Asteroid(String img, int radius) throws IOException {
        super("", 15);
        this.center.x = new Random().nextInt(51);
        this.center.y = new Random().nextInt(151);
        this.direction = new Random().nextInt(51);
        this.speed = 2.5f;
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
        Medium_Asteroid med1 = new Medium_Asteroid();
        med1.center.x = this.center.x;
        med1.center.y = this.center.y;
        med1.velocity.dy = this.velocity.dy + 2;

        Medium_Asteroid med2 = new Medium_Asteroid();
        med2.center.x = this.center.x;
        med2.center.y = this.center.y;
        med2.velocity.dy = this.velocity.dy - 2;

        Small_Asteroid small = new Small_Asteroid();
        med1.center.x = this.center.x;
        med1.center.y = this.center.y;
        med1.velocity.dy = this.velocity.dy + 5;

        AsteroidsGame.asteroids.add(med1);
        AsteroidsGame.asteroids.add(med2);
        AsteroidsGame.asteroids.add(small);

        this.alive = false;
    }
}
