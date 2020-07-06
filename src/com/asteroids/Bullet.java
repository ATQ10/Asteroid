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

/**
 * Clase para crear instancias de balas, las cuales son disparadas
 * por la nave
 */
public class Bullet extends Flying_Obj{
    public int life;
    // public String laserSound;

    public Bullet(Ship ship1) {
        super("/com/img/laserBlue1.png", 30); //sustituir img por imagen
        this.life = 60;
        this.speed = 10;
        this.angle = ship1.angle;
        this.center.x = ship1.center.x + ship1.width/2 - this.width/2;
        this.center.y = ship1.center.y;
        //this.start();
        fire();
    }

    /**
     * Metodo para manejo del disparo de la bala,
     * incremento de velocidad, asi como de calculo de angulos
     * para calcular la direccion del disparo
     */
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

    /**
     * Metodo para hacer que nuestra bala avanze y tenga movimiento.
     * En el chacamos si la bala aun esta viva y tambien metemos un
     * algoritmo para garantizar de que esta muera despues de cierto
     * tiempo si es que no choco con algun objeto.
     */
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

    /**
     * Sobreescribimos metodo de dibujo para poder
     * dibijar nuestra bala en panatalla
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        this.at = AffineTransform.getTranslateInstance(this.center.x,this.center.y);

        this.at.rotate(this.angle,this.width/2,this.height/2);

        g2d.drawImage(texture,this.at,null);
    }

    /**
     * Sobreescribimos metodo de actualizar para el movimiento
     */
    @Override
    public void update() {
        this.advance();
    }

    /**
     * Metodo para saber si nuestra bala a colisionado con un asteroide
     * @param asteroid
     * @return true o false de acuerdo a si hubo una colision
     */
    public boolean collision(Asteroid asteroid) {
        return asteroid.getBounds().intersects(this.getBounds());
    }
}
