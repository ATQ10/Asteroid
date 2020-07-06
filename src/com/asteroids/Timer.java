package com.asteroids;

import java.awt.*;

/**
 * Metodo encargado de manipular un timer y llevar el conteo del tiempo
 */
public class Timer extends Thread{
    public int seg;
    public static boolean game;

    public Timer(){
        this.seg = 0;
        this.start();
    }

    /**
     * Metodo heredado de un hilo para contener el cuerpo del timer
     */
    public void run(){
        this.game = true;
        while (this.game){
            try {
                Thread.currentThread().sleep(1000);
                System.out.println(seg);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            this.seg++;
        }
        this.game = true;
    }

    /**
     * Metodo encargado de dibujar el timer en el canvas del juego
     * @param g
     */
    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.drawString(this.getMin()+":"+this.getSeg(),350,15);
    }

    /**
     * Metodo encargado de dibujar el timer en el JPanel de puntaje
     * @param g
     */
    public void drawScore(Graphics g){
        g.setColor(Color.BLACK);
        g.drawString(this.getMin()+":"+this.getSeg(),285,505);
    }

    /**
     * Metodo encargado de retornar la cantidad de minutos jugados
     * @return
     */
    public String getMin(){
        int min = this.seg / 60;
        if(min<10)
            return "0"+min;
        else
            return ""+min;
    }

    /**
     * Metodo encargado de retornar la cantidad de segundos jugados modulados en 60
     * @return
     */
    public String getSeg(){
        int s;
            s = this.seg % 60;
        if(s<10)
            return "0"+s;
        else
            return ""+s;
    }
}
