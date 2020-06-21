package com.asteroids;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;


public class Bullet extends Flying_Obj{
    public int life;
    // public String laserSound;

    public Bullet(Ship ship1) {
        super("/com/img/laserBlue1.png", 30); //sustituir img por imagen
        this.life = 60;
        this.speed = 10;
        this.angle = ship1.angle;
        this.center.x = ship1.center.x;
        this.center.y = ship1.center.y;
        //this.start();
        fire();
    }

    public void fire() {
        try {
            Clip sound = AudioSystem.getClip();
            sound.open(AudioSystem.getAudioInputStream(new File(AsteroidsGame.soundBullet)));
            sound.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        this.velocity.dx += Math.sin(this.angle) * this.speed;
        this.velocity.dy -= Math.cos(this.angle) * this.speed;
    }

    public void advance() {
        super.advance();
        this.life -= 1;
        if (this.life <= 0) {
            this.alive = false;
        }
    }
/*
    public void run(){
        //this.fire();
        //System.out.println(this.getName());
    }

    public void draw(Graphics2D g2,JPanel panel) {
            g2.rotate(this.angle* Math.PI/180.0 ,this.center.x+this.image.getWidth(panel)/2,this.center.y+this.image.getHeight(panel)/2);
            super.draw(g2,panel);
        }

 */

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
