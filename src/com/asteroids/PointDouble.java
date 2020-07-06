package com.asteroids;

/**
 * Clase encargada de guardas las posiciones de coordenadas de los elementos
 */
public class PointDouble {
    public double x,y;

    public PointDouble() {
        this.x = 0;
        this.y = 0;
    }

    public PointDouble(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Metodo encargado de retornar el valor de la coordenada en el eje x
     * @return
     */
    public double getX() {
        return x;
    }

    /**
     * Metodo encargado de asignar el valor de la coordenada en el eje x
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Metodo encargado de retornar el valor de la coordenada en el eje y
     * @return
     */
    public double getY() {
        return y;
    }

    /**
     * Metodo encargado de asignar el valor de la coordenada en el eje y
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Metodo encargado de retornar la magnitud del vector
     * @return
     */
    public double getMagnotude(){
        return Math.sqrt(this.x*this.x + this.y*this.y);
    }

    /**
     * Metodo encargado de retornar un vector con un determinado angulo de direccion
     * @param angle
     * @return
     */
    public PointDouble setDirection(double angle){
        return new PointDouble(Math.cos(angle)*this.getMagnotude(),Math.sin(angle)*this.getMagnotude());
    }
}
