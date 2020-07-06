package com.asteroids;

import com.gui.Main;
import com.gui.WindowGame;
/**
 * Clase principal de ejecución
 * Integrantes:
 * Mena Mondragón JoséEduardo
 * Ocampo Calderon Loredana Paulette
 * Tejeda Arnoldo
 */

public class AsteroidsGame {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final String soundWindowsGame = "src/com/sounds/game.wav";
    public static final String soundMain = "src/com/sounds/menu.wav";
    public static final String soundSelect = "src/com/sounds/select.wav";
    public static final String soundBullet = "src/com/sounds/laser.wav";
    public static final String soundCollision = "src/com/sounds/collision.wav";
    public static final String soundExplosion = "src/com/sounds/explosion.wav";

    public static void main(String[] args) {
        System.out.println("***** Proyecto Asteroide *****");
        System.out.println("***** 4to commit *****");
        System.out.println("***** 4°A *****");
        System.out.println("Listo");
        System.out.println("Ahora todos tenemos acceso al repositorio :D");
        System.out.println("Versión BETA");

        //CODE
        new Main().setVisible(true);
    }
}
