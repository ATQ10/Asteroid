package com.gui;
import com.asteroids.AsteroidsGame;
import com.asteroids.Ship;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class WindowGame extends JPanel {
    JFrame frame;
    Image imageBackground;
    Ship ship;

    public WindowGame(){
        this.frame = new JFrame();
        //Titulamos la ventana
        this.frame.setTitle("Asteriods");
        //Redimencionamos la ventana
        this.frame.setSize(AsteroidsGame.SCREEN_WIDTH,AsteroidsGame.SCREEN_HEIGHT);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);

        //Ponemos el fondo visual
        try {
            imageBackground = ImageIO.read(new File("src/com/img/galaxy.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Container container = this.frame.getContentPane();
        container.add(this);

        //Reproducimos fondo sonoro
        playSound();
        this.frame.setVisible(true);
        loadGame();//carga los objetos del juego
        runGame();
    }

    private void loadGame() {
        ship = new Ship();
        // se registra KeyListener para ship
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                ship.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        setFocusable(true);
    }

    private void runGame() {
        while (true) {
            this.moveBall();
            this.repaint();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    int x = 0;
    int y = 0;
    int xa = 1;
    int ya = 1;

    private void moveBall() {
        if (x + xa < 0)
            xa = 1;
        if (x + xa > getWidth() - 30)
            xa = -1;
        if (y + ya < 0)
            ya = 1;
        if (y + ya > getHeight() - 30)
            ya = -1;

        x = x + xa;
        y = y + ya;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        //g.drawImage(ship.image,ship.center.x,ship.center.y,ship.image.getWidth(this),ship.image.getHeight(this),this);
        ship.draw((Graphics2D) g,this);
        System.out.println("x: "+ship.center.x+" | y: "+ship.center.y+" | vx: "+ship.velocity.dx+" | vy: "+ship.velocity.dy+" | a : "+ship.angle);
    }

    public void paintComponent(Graphics g){
        g.drawImage(imageBackground,0,0,getWidth(),getHeight(),this);
    }
    private void playSound() {
        try {
            Clip sonido = AudioSystem.getClip();
            sonido.open(AudioSystem.getAudioInputStream(new File(AsteroidsGame.soundWindowsGame)));
            sonido.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
}