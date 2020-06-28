package com.asteroids;

import java.awt.*;

public class Timer extends Thread{
    public int seg;
    public static boolean game;

    public Timer(){
        this.seg = 0;
        this.start();
    }
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
    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.drawString(this.getMin()+":"+this.getSeg(),350,15);
    }
    public void drawScore(Graphics g){
        g.setColor(Color.BLACK);
        g.drawString(this.getMin()+":"+this.getSeg(),285,505);
    }

    public String getMin(){
        int min = this.seg / 60;
        if(min<10)
            return "0"+min;
        else
            return ""+min;
    }
    public String getSeg(){
        int s;
            s = this.seg % 60;
        if(s<10)
            return "0"+s;
        else
            return ""+s;
    }
}
