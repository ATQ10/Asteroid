package com.asteroids;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.PortUnreachableException;

/**
 * Clase encargada de registrar las teclas presionadas
 */

public class KeyBoard  implements KeyListener {
    public boolean[] keys = new boolean[256];
    public static boolean UP, DOWN, LEFT, RIGHT, SPACE, ENTER;

    public KeyBoard(){
        UP = false;
        DOWN = false;
        LEFT = false;
        RIGHT = false;
        SPACE = false;
    }

    /**
     * Metodo encargado de actualizar el registro de
     * las teclas precionadas
     */
    public void update(){
        UP = keys[KeyEvent.VK_UP];
        DOWN = keys[KeyEvent.VK_DOWN];
        LEFT = keys[KeyEvent.VK_LEFT];
        RIGHT = keys[KeyEvent.VK_RIGHT];
        SPACE = keys[KeyEvent.VK_SPACE];
        ENTER = keys[KeyEvent.VK_ENTER];
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Metodo encargado de registrar que una tecla está
     * siendo presionada
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int index = e.getKeyCode();
        keys[index] = true;
    }

    /**
     * Metodo encargado de registrar que una tecla dejó
     * de presionarse
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int index = e.getKeyCode();
        keys[index] = false;
    }
}
