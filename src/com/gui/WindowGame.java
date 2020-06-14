package com.gui;
import com.asteroids.AsteroidsGame;
import com.asteroids.Bullet;
import com.asteroids.PointDouble;
import com.asteroids.Ship;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WindowGame extends JPanel {
    JFrame frame;
    Image imageBackground;
    Ship ship;
    Clip sound;
    ArrayList<Bullet> bullets;
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
        loadGame();//carga los objetos del juego
        this.frame.setVisible(true);
        runGame();
    }

    private void loadGame() {
        this.ship = new Ship();
        this.bullets = new ArrayList<>();
        // se registra KeyListener para ship
        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                ship.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_SPACE && ship.shot){
                    bullets.add(new Bullet(ship));
                    ship.shot = false;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                ship.keyReleased(e);
                if (e.getKeyCode() == KeyEvent.VK_SPACE){
                    ship.shot = true;
                }
            }
        });
        setFocusable(true);
    }

    private void runGame() {
        while (ship.alive) {
            this.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!ship.alive)
                this.sound.stop();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        //g.drawImage(ship.image,ship.center.x,ship.center.y,ship.image.getWidth(this),ship.image.getHeight(this),this);
        this.ship.advance();
        this.ship.draw(g2d,this);
        if(this.bullets.size()>0)
            for(Bullet bullet : this.bullets)
                if(bullet.alive){
                    bullet.advance();
                    bullet.draw(g2d,this);
                    System.out.println("x: "+bullet.center.x+" | y: "+bullet.center.y+" | vx: "+bullet.velocity.dx+" " +
                            "| vy: "+bullet.velocity.dy+" | a : "+bullet.angle +" | "+ship.angle);
                }
        //System.out.println("x: "+ship.center.x+" | y: "+ship.center.y+" | vx: "+ship.velocity.dx+" | vy: "+ship.velocity.dy+" | a : "+ship.angle);
    }

    public void paintComponent(Graphics g){
        g.drawImage(imageBackground,0,0,getWidth(),getHeight(),this);
    }

    public void playSound(){
        try {
            sound = AudioSystem.getClip();
            sound.open(AudioSystem.getAudioInputStream(new File(AsteroidsGame.soundWindowsGame)));
            sound.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
}