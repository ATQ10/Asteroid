package com.asteroids;

/**
 * Clase para creacion de instancias de asteroides de tipo PEQUEÑOS.
 */
public class Small_Asteroid extends Asteroid{
    public final double ROCK_SPIN = Math.toRadians(5);
    public static  final int POINTS = 1;

    public Small_Asteroid(Game game){
        super("/com/img/meteorGrey_small1.png", 2);
        this.speed = .5f;
        this.game = game;
        this.points = 1;
    }

    /**
     * Spbreescribimos metodo de avance del asteroide de acuerdo
     * a su tipo (PEQUEÑO).
     */
    @Override
    public void advance() {
        super.advance();
        this.angle += ROCK_SPIN;
    }

    /**
     * Metodo para definir proceso a seguir posterior a su colision
     * con algun objeto. En este caso como es el asteroide mas pequeño
     * simplemente su atributo de estar vivo lo ponemos en falso para
     * posteriormente removerlo de los objetos actuales de nuestro juego.
     */
    @Override
    public void break_apart() {
        this.alive = false;
    }
}
