package com.gui;
import com.asteroids.*;
import com.asteroids.Timer;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Clase encargada de mostrar el juego, progreso y acciones interactivas.
 */
public class WindowGame extends JFrame implements Runnable{
    public static String skin;
    public static Clip sound;
    public static boolean play = true;
    Canvas canvas;
    Image imageBackground;
    Image imageLoser;
    Image imageWinner;
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
    ArrayList<Bullet> bullets;
    //Timer
    Timer timer;

    public WindowGame(String ship){
        //Definimos ruta de skin
        this.skin = ship;
        this.timer = new Timer();
        //Titulamos la ventana
        this.setTitle("Asteriods");
        //Redimencionamos la ventana
        this.setSize(AsteroidsGame.SCREEN_WIDTH,AsteroidsGame.SCREEN_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.canvas = new Canvas();
        this.canvas.setPreferredSize(new Dimension(AsteroidsGame.SCREEN_WIDTH,AsteroidsGame.SCREEN_HEIGHT));
        this.canvas.setMaximumSize(new Dimension(AsteroidsGame.SCREEN_WIDTH,AsteroidsGame.SCREEN_HEIGHT));
        this.canvas.setMinimumSize(new Dimension(AsteroidsGame.SCREEN_WIDTH,AsteroidsGame.SCREEN_HEIGHT));
        this.add(canvas);
        this.setVisible(true);
        this.setFocusable(true);
    }

    /**
     * Metodo encargado de cargar todos los elementos iniciales del juego.
     */
    private void loadGame() {
        //Elegimos nuestro fondo
        try {
            this.imageBackground = ImageIO.read(new File("src/com/img/galaxy.jpg"));
            this.imageLoser = ImageIO.read(new File("src/com/img/loser.png"));
            this.imageWinner = ImageIO.read(new File("src/com/img/win.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.game = new Game();
        keyBoard = new KeyBoard();
        this.addKeyListener(this.keyBoard);
    }

    /**
     * Metodo encargado de reproducir el fondo sonoro del juego
     */
    public void playSound(){
        try {
            Main.sound.stop();
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

    /**
     * Metodo encargado de actualizar el juego y las teclas presionadas
     */
    public void update(){
        this.keyBoard.update();
        this.game.update();
    }

    /**
     * Metodo encargado de dibujar todos los elementos dentro del canvas
     */
    public void draw(){
        this.bs = this.canvas.getBufferStrategy();
        if(bs == null){
            this.canvas.createBufferStrategy(3);
            return;
        }
        this.g = bs.getDrawGraphics();

        //------------ Comienza el dibujo
        if(this.active && !Game.loser && !Game.winner){
            this.g.setFont(new Font ("Arial", 1, 20));
            this.g.drawImage(imageBackground,0,0,AsteroidsGame.SCREEN_WIDTH,AsteroidsGame.SCREEN_HEIGHT,null);

            this.game.draw(this.g);

            this.g.setColor(Color.WHITE);
            this.g.drawString("FPS: "+this.PROMFPS,AsteroidsGame.SCREEN_WIDTH-90,15);

            this.g.setColor(Color.WHITE);
            this.g.drawString("SCORE: "+ this.game.score,AsteroidsGame.SCREEN_WIDTH-780,15);
            this.timer.draw(g);
        }else{
            if (Game.winner){
                this.g.drawImage(imageWinner,0,0,AsteroidsGame.SCREEN_WIDTH,AsteroidsGame.SCREEN_HEIGHT,null);
                this.g.setColor(Color.BLACK);
                this.g.setFont(new Font ("Arial", 1, 50));
                this.g.drawString(""+this.game.score,310,435);
                this.timer.drawScore(g);
                if(KeyBoard.ENTER)
                    this.active = !Game.winner;
            }
            else
                if(Game.loser){
                    this.g.drawImage(imageLoser,0,0,AsteroidsGame.SCREEN_WIDTH,AsteroidsGame.SCREEN_HEIGHT,null);
                    this.g.setColor(Color.BLACK);
                    this.g.setFont(new Font ("Arial", 1, 50));
                    this.g.drawString(""+this.game.score,310,435);
                    this.timer.drawScore(g);
                    if(KeyBoard.ENTER)
                        this.active = !Game.loser;
                }

        }
        //------------ Finaliza el dibujo
        this.g.dispose();
        this.bs.show();
    }

    /**
     * Metodo encargado de realizar procesos como inicializador del juego
     */
    public void init(){
        this.loadGame();
        this.playSound();
    }

    /**
     * Metodo implementado de un hilo encargado de controlar los ciclos lógicos
     * del juego, permitiendo al usuario una interaccion fluida y sin sobrecargar el
     * hilo principal
     */
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
            Game.winner = false;
            Game.loser = false;
            this.dispose();
        this.stop();
    }
    /**
     * Metodo encargado de lanzar el hilo correspondiente al juego
     */
    public void start(){
        this.thread = new Thread(this);
        this.thread.start();
        this.active = true;
    }

    /**
     * Metodo encargado de detener el hilo correspondiente al juego
     */
    public void stop(){
        try {
            thread.join();
            this.active = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}