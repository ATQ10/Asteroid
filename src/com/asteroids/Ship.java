package com.asteroids;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Ship extends Flying_Obj{
    // public String sound;
    public final int TURN_AMOUNT = 9;
    public final float THRUST_AMOUNT = 0.25f;
    public boolean shot;
    public Ship() {
        super("src/com/img/ovni.png", 30);
        this.angle = 1;
        this.center.x = (AsteroidsGame.SCREEN_WIDTH/2);
        this.center.y = (AsteroidsGame.SCREEN_HEIGHT/2);
        this.shot = true;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            left();
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            right();
        if (e.getKeyCode() == KeyEvent.VK_UP)
            thrust(false);
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            thrust(true);
    }
    public void keyReleased(KeyEvent e) {
    }

    public void left() { this.angle -= TURN_AMOUNT; }

    public void right() {
        this.angle += TURN_AMOUNT;
    }

    public void thrust(Boolean isUp) {

        if(isUp) {
            this.velocity.dx -= Math.sin(Math.toRadians(this.angle)) * THRUST_AMOUNT;
            this.velocity.dy += Math.cos(Math.toRadians(this.angle)) * THRUST_AMOUNT;
        }
        else {
            this.velocity.dx += Math.sin(Math.toRadians(this.angle)) * THRUST_AMOUNT;
            this.velocity.dy -= Math.cos(Math.toRadians(this.angle)) * THRUST_AMOUNT;
        }
    }
    public void draw(Graphics2D g2,JPanel panel) {
        g2.rotate(this.angle* Math.PI / 180.0,this.center.x+this.image.getWidth(panel)/2,this.center.y+this.image.getHeight(panel)/2);
        super.draw(g2,panel);
    }
}
