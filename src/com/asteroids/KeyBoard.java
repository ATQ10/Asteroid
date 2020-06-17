package com.asteroids;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.PortUnreachableException;

public class KeyBoard  implements KeyListener {
    public boolean[] keys = new boolean[256];
    public static boolean UP, DOWN, LEFT, RIGHT, SPACE;

    public KeyBoard(){
        UP = false;
        DOWN = false;
        LEFT = false;
        RIGHT = false;
        SPACE = false;
    }

    public void update(){
        UP = keys[KeyEvent.VK_UP];
        DOWN = keys[KeyEvent.VK_DOWN];
        LEFT = keys[KeyEvent.VK_LEFT];
        RIGHT = keys[KeyEvent.VK_RIGHT];
        SPACE = keys[KeyEvent.VK_SPACE];
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int index = e.getKeyCode();
        keys[index] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int index = e.getKeyCode();
        keys[index] = false;
    }
}
