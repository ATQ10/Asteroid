package com.asteroids;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;

/**
 * Clase Asteroids que extiende de la clase abstracta Flying Object.
 * Esta clase contiene todos los atributos y funciones esenciales base
 * para poder crear nuevos asteroides de acuerdo a su tipo o tamaño.
 */
public class Asteroid extends Flying_Obj{
    // public String sound;
    Game game;
    public int points;
    public Asteroid(String img, int radius){
        super(img, radius);
        // this.sound = sound;
    }

    /**
     * Funcion para que nuestro asteroide tenga movimiento.
     * Ya que extiende de Flying_Object solo hacemos una llamada super()
     * para ejecutar dicha funcion que tiene la clase de la que heredamos.
     */
    public void advance() {
        super.advance();
    }

    /**
     * Esta funcion tendra que ser sobrescrita en las clases de asteroides hijas
     * para implementar su propio metodo de destruccion o particion cuando exista una colision.
     */
    public void break_apart() {};

    /**
     * Sobreescribimos el metodo draw de Flyin_Obj para poder dibujar en este caso los
     * graficos necesarios para poder construir nuestro asteroide y hacer que este rotando
     * cada vez que se vaya dibujando en pantalla. Hacemos uso de la clase Graphics2D y tambien
     * de sus metodos para lograr esto.
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        this.at = AffineTransform.getTranslateInstance(this.center.x,this.center.y);

        this.at.rotate(this.angle,this.width/2,this.height/2);

        g2d.drawImage(texture,this.at,null);
    }

    /**
     * Sobreescribimos metodo update que actualiza los valores de los atributos
     * para continuar con el movimiento del asteroide.
     */
    @Override
    public void update() {
        this.advance();
    }
}
