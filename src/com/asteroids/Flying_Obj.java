package com.asteroids;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

//import static com.sun.webkit.graphics.GraphicsDecoder.drawImage;

/**
 * Clase abstracta para todo objecto que sea volador
 * balas, asteroides y nave en este caso.
 */
abstract class Flying_Obj{
    protected BufferedImage texture;
    protected AffineTransform at;
    public Velocity velocity;
    public double maxVelocity;
    public PointDouble center;
    public Boolean alive;
    public int width;
    public int height;
    public int radius;
    public double angle;
    public float speed;
    public int direction;

    public static final int WIDTH = 0; // Sustituir por ancho en caso de ser necesario
    public static final int HEIGHT = 0; // Sustituir por ancho en caso de ser necesario

    /**
     * Constructor donde definimos todos los atributos esenciales de un
     * objeto volador, e inicializamos los mismos para su posterior
     * implementacion en otras clases hija.
     * @param img
     * @param radius
     */
    public Flying_Obj(String img, int radius) {
        this.velocity = new Velocity();
        this.center = new PointDouble();
        this.alive = true;
        this.radius = radius;
        this.angle = 0;
        this.speed = 0;
        this.direction = 0;
        this.texture = Loader.ImageLoader(img);
        this.width = this.texture.getWidth();
        this.height = this.texture.getHeight();
    }

    /**
     * Metodo basico a base de coordenadas para poder crear el
     * movimiento de nuestro objeto. Asi mismo previo a
     * crear este movimiento, hacemos llamada al metodo wrapping
     * el cual manejara que los objetos no salgan de la ventana.
     */
    public void advance() {
        this.wrapping();
        this.center.x += this.velocity.dx;
        this.center.y += this.velocity.dy;
    }

    /**
     * Metodo update que actualizara valores de atributos posterior
     * a que los objetos sean dibujados o avanzados
     */
    public abstract void update();

    /**
     * Metodo para dibujar los graficos del objeto en pantalla
     * @param g
     */
    public abstract void draw(Graphics g);
/*
    public void draw(Graphics2D g2,JPanel panel) {
        g2.drawImage(this.image, (int) this.center.x, (int)this.center.y, panel);
    }


    public Boolean isAlive() {
        return this.alive;
    }
*/

    /**
     * Metodo para garantizar que los objetos no salgan de pantalla.
     * Si algun objeto llega a chocar o salir de algun borde de la pantalla,
     * este aparecera del lado contrario del borde del cual salio, para asi
     * cumplir con ese objetivo.
     */
    public void wrapping() {
        if(this.center.x > AsteroidsGame.SCREEN_WIDTH) {
            this.center.x -= AsteroidsGame.SCREEN_WIDTH;
        }
        if(this.center.x < 0) {
            this.center.x += AsteroidsGame.SCREEN_WIDTH;
        }
        if(this.center.y > AsteroidsGame.SCREEN_HEIGHT) {
            this.center.y -= AsteroidsGame.SCREEN_HEIGHT;
        }
        if(this.center.y < 0) {
            this.center.y += AsteroidsGame.SCREEN_HEIGHT;
        }
    }

    /**
     * Metodo para obtener un nuevo rectangulo con las medidas necesarias
     * para saber el borde de nuestro objeto
     * @return
     */
    public Rectangle getBounds() {
        return new Rectangle((int)this.center.x, (int)this.center.y, this.width, this.height);
    }

}
