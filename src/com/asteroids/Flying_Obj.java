package com.asteroids;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

//import static com.sun.webkit.graphics.GraphicsDecoder.drawImage;

abstract class Flying_Obj{
    protected BufferedImage texture;
    public Velocity velocity;
    public PointDouble center;
    public Boolean alive;
    public Image image;
    public int radius;
    public float angle;
    public float speed;
    public int direction;

    public static final int WIDTH = 0; // Sustituir por ancho en caso de ser necesario
    public static final int HEIGHT = 0; // Sustituir por ancho en caso de ser necesario

    public Flying_Obj(String img, int radius) {
        this.velocity = new Velocity();
        this.center = new PointDouble();
        this.alive = true;
        this.radius = radius;
        this.angle = 0;
        this.speed = 0;
        this.direction = 0;
        this.texture = Loader.ImageLoader(img);
    }

    public void advance() {
        this.wrapping();
        this.center.x += this.velocity.dx;
        this.center.y += this.velocity.dy;
    }

    public abstract void update();

    public abstract void draw(Graphics g);
/*
    public void draw(Graphics2D g2,JPanel panel) {
        g2.drawImage(this.image, (int) this.center.x, (int)this.center.y, panel);
    }


    public Boolean isAlive() {
        return this.alive;
    }
*/

    public void wrapping() {
        if(this.center.x > AsteroidsGame.SCREEN_WIDTH) {
            this.center.x -= AsteroidsGame.SCREEN_WIDTH;
        }
        if(this.center.x < 0) {
            this.center.x += AsteroidsGame.SCREEN_WIDTH;
        }
        if(this.center.y > AsteroidsGame.SCREEN_HEIGHT) {
            this.center.y -= AsteroidsGame.SCREEN_HEIGHT;
        }
        if(this.center.y < 0) {
            this.center.y += AsteroidsGame.SCREEN_HEIGHT;
        }
    }

}
