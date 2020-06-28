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
    public static String skin;
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
    Clip sound;
    ArrayList<Bullet> bullets;

    public WindowGame(String ship){
        //Definimos ruta de skin
        this.skin = ship;
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
        this.g.drawString("FPS: "+this.PROMFPS,AsteroidsGame.SCREEN_WIDTH-80,10);

        //------------ Finaliza el dibujo

        this.g.setColor(Color.WHITE);
        this.g.drawString("SCORE: "+ this.game.score,AsteroidsGame.SCREEN_WIDTH-780,10);

        this.g.dispose();
        this.bs.show();
    }

    public void init(){
    this.loadGame();
    this.playSound();
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
                if(Game.takeAbreak)
                    Game.newRound--;
                if(Game.newRound<0)
                    Game.takeAbreak = false;
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