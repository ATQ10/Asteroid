package com.asteroids;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Medium_Asteroid extends Asteroid{
    public final double ROCK_SPIN = Math.toRadians(-2);
    public static final int POINTS = 3;

    public Medium_Asteroid(Game game){
        super("/com/img/meteorGrey_med1.png", 5);
        this.speed = 1;
        this.velocity.dx += Math.cos(Math.toRadians(this.direction)) * this.speed;
        this.velocity.dx += Math.cos(Math.toRadians(this.direction)) * this.speed;
        this.game = game;
        this.points = 3;
    }

    /**
     * Metodo de avance para nuestro asteroide por
     * su tipo (MEDIANO)
     */
    @Override
    public void advance() {
        super.advance();
        this.angle += ROCK_SPIN;
    }

    /**
     * Metodo para especificar proceso a seguir posterior a la colision
     * del asteroide con algun otro objeto. En este caso por ser un asteroide
     * mediano al momento de la colision genera otros nuevos asteroides
     * de tipo PEQUEÑO y el actual asteroide ponemos su atributo de vida en falso.
     */
    @Override
    public void break_apart() {
        Small_Asteroid small1 = new Small_Asteroid(game);
        small1.center.x = this.center.x;
        small1.center.y = this.center.y;
        small1.velocity.dx = this.velocity.dy - 1.5f;
        small1.velocity.dy = this.velocity.dy + 1.5f;

        Small_Asteroid small2 = new Small_Asteroid(game);
        small2.center.x = this.center.x;
        small2.center.y = this.center.y;
        small2.velocity.dx = this.velocity.dy - 1.5f;
        small2.velocity.dy = this.velocity.dy - 1.5f;

        this.game.asteroids.add(small1);
        this.game.asteroids.add(small2);

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
