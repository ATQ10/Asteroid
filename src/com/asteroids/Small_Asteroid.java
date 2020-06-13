package com.asteroids;

import java.io.IOException;

public class Small_Asteroid extends Asteroid{
    public final int ROCK_SPIN = 5;

    public Small_Asteroid() throws IOException {
        super("", 2);
        this.speed = 1.5f;
    }

    @Override
    public void advance() {
        super.advance();
        this.angle += ROCK_SPIN;
    }

    @Override
    public void break_apart() throws IOException {
        this.alive = false;
    }
}
