package com.asteroids;

import com.gui.Main;
import com.gui.WindowGame;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
/**
 * Clase Game es la encargada de proporcionar todos los datos referentes al juego
 * puesto que guarda los elementos (nave, asteroides, descansos) y sus respectivas
 * propiedades
 */
public class Game {
    public static boolean takeAbreak = false;
    public static boolean loser = false;
    public static boolean winner = false;
    public static int newRound = 4;
    public int lastRound;
    Ship ship;
    ArrayList<Bullet> bullets = new ArrayList<>();
    ArrayList<Asteroid> asteroids = new ArrayList<>();
    int num_asteroids = -1;
    public int score = 0;
    public Game(){
        this.ship = new Ship(this);
        this.lastRound = 3;
    }
    /**
     * Metodo encargado de actualizar los datos del juego
     * actualizando las propiedades de los objetos
     */
    public void update(){
        //Ship
        this.ship.update();
        //Bullets
        if(this.bullets.size()>0) {
            for (Bullet bullet : this.bullets)
                bullet.update();
            for (int i=0;i<this.bullets.size();i++)
                if (!this.bullets.get(i).alive)
                    this.bullets.remove(i);
        }
        //Asteroids
        if(this.asteroids.size()>0) {
            for (Asteroid asteroid : this.asteroids)
                asteroid.update();
            for (int i=0;i<this.asteroids.size();i++)
                if (!this.asteroids.get(i).alive)
                    this.asteroids.remove(i);
        }
        //System.out.println(this.bullets.size());
        //Coliciones
        this.collisions();
        //Siguiente ronda
        if(this.ship.alive && (this.asteroids.size() == 0)&&!this.takeAbreak) {
            if(this.newRound==4){
                   this.takeAbreak = true;
                if(this.num_asteroids==this.lastRound){
                    WindowGame.sound.stop();
                    Main.sound.loop(Clip.LOOP_CONTINUOUSLY);
                    winner = true;
                    Timer.game = false;
                    return;
                }
            }
            else{
                this.num_asteroids += 2;
                for(int i=0; i<this.num_asteroids; i++)
                    this.asteroids.add(new Large_Asteroid(this));
                this.newRound=4;
            }
        }
        if(!this.ship.alive){
            WindowGame.sound.stop();
            Main.sound.loop(Clip.LOOP_CONTINUOUSLY);
            loser = true;
        }
    }

    /**
     * Metodo encargado de verificar las colisiones entre los asteroides
     * y los rayos lacers, ademas de verificar la colición de la nave
     * con algun asteroide.
     */
    private void collisions() {
        if(this.bullets.size()>0) {
            for (int i=0;i<this.bullets.size();i++)
                for (int j=0;j<this.asteroids.size();j++)
                    if(bullets.get(i).collision(this.asteroids.get(j))){
                        this.score += this.asteroids.get(j).points;
                        this.asteroids.get(j).break_apart();
                        this.bullets.remove(i);
                        return;
                    }
        }

        for(Asteroid asteroid: this.asteroids) {
            if(this.ship.alive &&  asteroid.alive) {
                if(this.ship.collision(asteroid)) {
                    asteroid.break_apart();
                    this.ship.alive = false;
                    Loader.playSoundExplosion();
                    Timer.game = false;
                    return;
                }
            }
        }
    }

    /**
     * Metodo encargado de dibujar los elementos en el canvas
     * @param g
     */
    public void draw(Graphics g){
        if(this.ship.alive) {
            for (Bullet bullet : this.bullets)
                bullet.draw(g);
            this.ship.draw(g);
        }
        for (Asteroid asteroid : asteroids)
            asteroid.draw(g);
        //Tomar un descanso
        if(this.takeAbreak)
            coutNewRound(g);
    }

    /**
     * Metodo encargado de dibujar el conteo regresivo 3, 2, 1, ¡Fuego!
     * @param g
     */
    private void coutNewRound(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform at;
        switch (newRound){
            case 0:
                at = AffineTransform.getTranslateInstance(
                        AsteroidsGame.SCREEN_WIDTH/2-Loader.count0.getWidth()/2,AsteroidsGame.SCREEN_HEIGHT/2-Loader.count0.getHeight()/2
                );
                g2d.drawImage(Loader.count0,at,null);
                break;
            case 1:
                at = AffineTransform.getTranslateInstance(
                        AsteroidsGame.SCREEN_WIDTH/2-Loader.count1.getWidth()/2,AsteroidsGame.SCREEN_HEIGHT/2-Loader.count1.getHeight()/2
                );
                g2d.drawImage(Loader.count1,at,null);
                break;
            case 2:
                at = AffineTransform.getTranslateInstance(
                        AsteroidsGame.SCREEN_WIDTH/2-Loader.count2.getWidth()/2,AsteroidsGame.SCREEN_HEIGHT/2-Loader.count2.getHeight()/2
                );
                g2d.drawImage(Loader.count2,at,null);
                break;
            case 3:
                at = AffineTransform.getTranslateInstance(
                        AsteroidsGame.SCREEN_WIDTH/2-Loader.count3.getWidth()/2,AsteroidsGame.SCREEN_HEIGHT/2-Loader.count3.getHeight()/2
                );
                g2d.drawImage(Loader.count3,at,null);
                break;
        }
    }
}
