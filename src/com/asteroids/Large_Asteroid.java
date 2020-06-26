package com.asteroids;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Large_Asteroid extends Asteroid{
    public final double ROCK_SPIN = Math.toRadians(1);

    public Large_Asteroid(Game game){
        super("/com/img/meteorGrey_big1.png", 15);
        this.center.x = new Random().nextInt(51);
        this.center.y = new Random().nextInt(151);
        this.direction = new Random().nextInt(51);
        this.speed = 1.5f;
        this.velocity.dx += Math.cos(Math.toRadians(this.direction)) * this.speed;
        this.velocity.dy += Math.cos(Math.toRadians(this.direction)) * this.speed;
        this.game = game;
    }

    @Override
    public void advance() {
        super.advance();
        this.angle += ROCK_SPIN;
    }

    @Override
    public void break_apart(){
        Medium_Asteroid med1 = new Medium_Asteroid(game);
        med1.center.x = this.center.x;
        med1.center.y = this.center.y;
        med1.velocity.dy = this.velocity.dy + 2;

        Medium_Asteroid med2 = new Medium_Asteroid(game);
        med2.center.x = this.center.x;
        med2.center.y = this.center.y;
        med2.velocity.dy = this.velocity.dy - 2;

        Small_Asteroid small = new Small_Asteroid(game);
        small.center.x = this.center.x;
        small.center.y = this.center.y;
        small.velocity.dy = this.velocity.dy + 5;

        this.game.asteroids.add(med1);
        this.game.asteroids.add(med2);
        this.game.asteroids.add(small);

        this.alive = false;
        try {
            Clip sound = AudioSystem.getClip();
            sound.open(AudioSystem.getAudioInputStream(new File(AsteroidsGame.soundCollision)));
            sound.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
}
