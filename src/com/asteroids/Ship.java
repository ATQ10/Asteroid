package com.asteroids;

import com.gui.WindowGame;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Clase Ship para la creacion de instancias de objetos de tipo nave.
 */
public class Ship extends Flying_Obj{
    // public String sound;
    public final double TURN_AMOUNT = Math.toRadians(3);
    public final float THRUST_AMOUNT = 0.25f;
    public boolean shot;
    public Game game;
    public Ship(Game game) {
        super(WindowGame.skin, 30);
        this.angle = Math.toRadians(1);
        this.center.x = (AsteroidsGame.SCREEN_WIDTH/2);
        this.center.y = (AsteroidsGame.SCREEN_HEIGHT/2);
        this.shot = true;
        this.maxVelocity = 2;
        this.game = game;
    }

    /**
     * Metodo para rotar nave hacia lado izquierdo.
     */
    public void left() { this.angle -= TURN_AMOUNT; }

    /**
     * Metodo para rotar nave hacia lado derecho
     */
    public void right() {
        this.angle += TURN_AMOUNT;
    }

    /**
     * Metodo para incremento o decremento de velocidad
     * de la nave con manejo de teclas up y down
     * @param isUp
     */
    public void thrust(Boolean isUp) {

        if(isUp) {
            this.velocity.dx -= Math.sin(this.angle) * THRUST_AMOUNT;
            this.velocity.dy += Math.cos(this.angle) * THRUST_AMOUNT;
        }
        else {
            this.velocity.dx += Math.sin(this.angle) * THRUST_AMOUNT;
            this.velocity.dy -= Math.cos(this.angle) * THRUST_AMOUNT;
        }
    }

    /**
     * Metodo para actualizacion de catual estado de la nave,
     * disparo, si esta viva, movimiento, asi como tambien
     * la velocidd actual.
     */
    @Override
    public void update() {
        if(this.alive) {
            if (KeyBoard.SPACE && this.shot) {
                game.bullets.add(new Bullet(this));
                this.shot = false;
            }
            if (!KeyBoard.SPACE)
                this.shot = true;
            if (KeyBoard.LEFT)
                left();
            if (KeyBoard.RIGHT)
                right();
            if (KeyBoard.UP)
                thrust(false);
            if (KeyBoard.DOWN)
                thrust(true);

            this.velocity.limit(this.maxVelocity);
        }
    }


    /**
     * Metodo de dibujo para la nave, asi como tambien de otros graficos
     * como lo son los propulsores de la nave de acuerto a la direccion
     * de rotacion o incremento de velocidad.
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        this.advance();
        //g.drawImage(this.texture,(int)this.center.x,(int)this.center.y,null);
        Graphics2D g2d = (Graphics2D) g;

        AffineTransform at1 = AffineTransform.getTranslateInstance(this.center.x + this.width - 5 - Loader.speed.getWidth(),this.center.y + this.height/2 + 25);
        AffineTransform at2 = AffineTransform.getTranslateInstance(this.center.x + 5,this.center.y + this.height/2 + 25);

        at1.rotate(this.angle, - this.width/2 + 5 + Loader.speed.getWidth(),-25);
        at2.rotate(this.angle,this.width/2 - 5,-25);

        if (KeyBoard.UP) {
            g2d.drawImage(Loader.speed, at1, null);
            g2d.drawImage(Loader.speed, at2, null);
        }
        if (KeyBoard.RIGHT)
            g2d.drawImage(Loader.speed, at2, null);
        if (KeyBoard.LEFT)
            g2d.drawImage(Loader.speed, at1, null);
        this.at = AffineTransform.getTranslateInstance(this.center.x,this.center.y);
        this.at.rotate(this.angle,this.width/2,this.height/2);
        g2d.drawImage(texture,this.at,null);
    }

    /**
     * Metodo para checar colisiones de la nave con algun asteroide.
     * @param asteroid
     * @return true o false de acuerdo a si hubo o no una colision.
     */
    public boolean collision(Asteroid asteroid) {
        return asteroid.getBounds().intersects(this.getBounds());
    }
}
