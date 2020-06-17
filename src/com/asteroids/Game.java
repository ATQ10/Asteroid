package com.asteroids;

import java.awt.*;
import java.nio.Buffer;

public class Game {
    Ship ship;

    public Game(){
        ship = new Ship();
    }

    public void update(){
        this.ship.update();
    }
    public void draw(Graphics g){
        this.ship.draw(g);
    }
}
