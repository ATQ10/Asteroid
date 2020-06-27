package com.asteroids;

import java.awt.*;
import java.nio.Buffer;
import java.util.ArrayList;

public class Game {
    Ship ship;
    ArrayList<Bullet> bullets = new ArrayList<>();
    ArrayList<Asteroid> asteroids = new ArrayList<>();
    int num_asteroids = 1;
    public int score = 0;

    public Game(){
        this.ship = new Ship(this);
        for (int i=0;i<num_asteroids;i++)
            this.asteroids.add(new Large_Asteroid(this));
    }

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

        if(this.ship.alive && (this.asteroids.size() == 0)) {
            num_asteroids += 2;
            for(int i=0; i<num_asteroids; i++)
                this.asteroids.add(new Large_Asteroid(this));
        }
    }

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
                    return;
                }
            }
        }
    }

    public void draw(Graphics g){
        if(this.ship.alive) {
            for (Bullet bullet : this.bullets)
                bullet.draw(g);
            this.ship.draw(g);
        }
        for (Asteroid asteroid : asteroids)
            asteroid.draw(g);
    }
}
