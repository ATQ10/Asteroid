package com.asteroids;

import java.awt.*;
import java.nio.Buffer;
import java.util.ArrayList;

public class Game {
    Ship ship;
    ArrayList<Bullet> bullets = new ArrayList<>();
    public Game(){
        ship = new Ship(this);
    }

    public void update(){
        this.ship.update();
        if(this.bullets.size()>0) {
            for (Bullet bullet : this.bullets)
                bullet.update();
            for (int i=0;i<this.bullets.size();i++)
                if (!this.bullets.get(i).alive)
                    this.bullets.remove(i);
        }
        System.out.println(this.bullets.size());

    }
    public void draw(Graphics g){
        this.ship.draw(g);
        for (Bullet bullet : this.bullets)
            bullet.draw(g);
    }
}
