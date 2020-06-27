package com.asteroids;

import java.io.IOException;

public class Small_Asteroid extends Asteroid{
    public final double ROCK_SPIN = Math.toRadians(5);
    public static  final int POINTS = 1;

    public Small_Asteroid(Game game){
        super("/com/img/meteorGrey_small1.png", 2);
        this.speed = .5f;
        this.game = game;
        this.points = 1;
    }

    @Override
    public void advance() {
        super.advance();
        this.angle += ROCK_SPIN;
    }

    @Override
    public void break_apart() {
        this.alive = false;
    }
}
