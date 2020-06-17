package com.gui;
import com.asteroids.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WindowGame extends JFrame implements Runnable{
    Canvas canvas;
    Image imageBackground;
    Thread thread;
    Boolean active = false;
    BufferStrategy bs;
    Graphics g;
    final int FPS = 60;
    double ATOMTIME = 1000000000/FPS;
    double deltaTime = 0;
    int PROMFPS = FPS;
    Game game;
    KeyBoard keyBoard;
    Ship ship;
    Clip sound;
    ArrayList<Bullet> bullets;

    public WindowGame(){
        //Titulamos la ventana
        this.setTitle("Asteriods");
        //Redimencionamos la ventana
        this.setSize(AsteroidsGame.SCREEN_WIDTH,AsteroidsGame.SCREEN_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.canvas = new Canvas();
        this.canvas.setPreferredSize(new Dimension(AsteroidsGame.SCREEN_WIDTH,AsteroidsGame.SCREEN_HEIGHT));
        this.canvas.setMaximumSize(new Dimension(AsteroidsGame.SCREEN_WIDTH,AsteroidsGame.SCREEN_HEIGHT));
        this.canvas.setMinimumSize(new Dimension(AsteroidsGame.SCREEN_WIDTH,AsteroidsGame.SCREEN_HEIGHT));
        this.add(canvas);
        //Reproducimos fondo sonoro
        //playSound();
        //loadGame();//carga los objetos del juego
        //runGame();
        this.setVisible(true);
        this.setFocusable(true);
    }

    private void loadGame() {
        //Elegimos nuestro fondo
        try {
            this.imageBackground = ImageIO.read(new File("src/com/img/galaxy.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.game = new Game();
        keyBoard = new KeyBoard();
        this.addKeyListener(this.keyBoard);

        /*
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
        */
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
/*
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        //g.drawImage(ship.image,ship.center.x,ship.center.y,ship.image.getWidth(this),ship.image.getHeight(this),this);
        this.ship.advance();
        this.ship.draw(g2d,new JPanel());
        if(this.bullets.size()>0)
            for(Bullet bullet : this.bullets)
                if(bullet.alive){
                    bullet.advance();
                    bullet.draw(g2d,new JPanel());
                    System.out.println("x: "+bullet.center.x+" | y: "+bullet.center.y+" | vx: "+bullet.velocity.dx+" " +
                            "| vy: "+bullet.velocity.dy+" | a : "+bullet.angle +" | "+ship.angle);
                }
        //System.out.println("x: "+ship.center.x+" | y: "+ship.center.y+" | vx: "+ship.velocity.dx+" | vy: "+ship.velocity.dy+" | a : "+ship.angle);

    }

    public void paintComponent(Graphics g){
        g.drawImage(imageBackground,0,0,getWidth(),getHeight(),this);
    }
*/
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

    public void update(){
        this.keyBoard.update();
        this.game.update();
    }

    public void draw(){
        this.bs = this.canvas.getBufferStrategy();
        if(bs == null){
            this.canvas.createBufferStrategy(3);
            return;
        }
        this.g = bs.getDrawGraphics();

        //------------ Comienza el dibujo

        this.g.drawImage(imageBackground,0,0,AsteroidsGame.SCREEN_WIDTH,AsteroidsGame.SCREEN_HEIGHT,null);

        this.game.draw(this.g);


        this.g.setColor(Color.WHITE);
        this.g.drawString("FPS: "+this.PROMFPS,0,10);

        //------------ Finaliza el dibujo

        this.g.dispose();
        this.bs.show();
    }

    public void init(){
    this.loadGame();

    }

    @Override
    public void run() {
        long nowTime = 0;
        long lastTime = System.nanoTime();
        int frames = 0;
        long time = 0;
        this.init();
        while (this.active) {
            nowTime = System.nanoTime();
            this.deltaTime += (nowTime-lastTime)/this.ATOMTIME;
            time += (nowTime-lastTime);
            lastTime = nowTime;
            if (this.deltaTime >= 1){
                this.update();
                this.draw();
                this.deltaTime--;
                frames++;
            }
            if(time >= 1000000000){
                this.PROMFPS = frames;
                frames = 0;
                time=0;
            }
        }

        this.stop();
    }

    public void start(){
        this.thread = new Thread(this);
        this.thread.start();
        this.active = true;
    }

    public void stop(){
        try {
            thread.join();
            this.active = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}