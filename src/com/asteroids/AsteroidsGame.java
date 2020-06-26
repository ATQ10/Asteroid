package com.asteroids;

import com.gui.WindowGame;

import java.util.ArrayList;
import java.util.List;

//Favor de agregar su nombre para indicar que se tiene control del
// las versiones del repositorio:D
// * Ocampo Calderon Loredana Paulette (MusaDeSaturno) :3
// *
// * Tejeda Arnoldo (ATQ10)
public class AsteroidsGame {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final String soundWindowsGame = "src/com/sounds/game.wav";
    public static final String soundBullet = "src/com/sounds/laser.wav";
    public static final String soundCollision = "src/com/sounds/collision.wav";

    public static void main(String[] args) {
        System.out.println("***** Proyecto Asteroide *****");
        System.out.println("***** 4to commit *****");
        System.out.println("***** 4°A *****");
        System.out.println("Listo");
        System.out.println("Prueba Mena");
        System.out.println("Ahora todos tenemos acceso al repositorio :D");
        System.out.println("Versión BETA");

        //CODE
        WindowGame wg = new WindowGame();
        wg.start();
    }
}
