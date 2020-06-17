package com.asteroids;

import java.awt.*;
import java.io.IOException;

public class Asteroid extends Flying_Obj{
    // public String sound;
    public Asteroid(String img, int radius) throws IOException {
        super(img, radius);
        // this.sound = sound;
    }

    public void advance() {
        super.advance();
    }

    public void break_apart() throws IOException {};
    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void update() {

    }
}
