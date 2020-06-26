package com.asteroids;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;

public class Asteroid extends Flying_Obj{
    // public String sound;
    Game game;
    public Asteroid(String img, int radius){
        super(img, radius);
        // this.sound = sound;
    }

    public void advance() {
        super.advance();
    }

    public void break_apart() {};
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        this.at = AffineTransform.getTranslateInstance(this.center.x,this.center.y);

        this.at.rotate(this.angle,this.width/2,this.height/2);

        g2d.drawImage(texture,this.at,null);
    }

    @Override
    public void update() {
        this.advance();
    }
}
