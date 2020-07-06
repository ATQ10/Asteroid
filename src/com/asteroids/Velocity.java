package com.asteroids;

/**
 * Clase para manejo de velocidad
 */
public class Velocity {
    public double dx;
    public double dy;

    public Velocity() {
        this.dx = 0;
        this.dy = 0;
    }

    /**
     * Metodo para mantener velocidad maxima en los objetos del juego.
     * @param max
     */
    public void limit(double max){
        if(this.dx > max)
            this.dx = max;
        if(this.dx < -max)
            this.dx = -max;
        if(this.dy > max)
            this.dy = max;
        if(this.dy < -max)
            this.dy = -max;
    }
}
