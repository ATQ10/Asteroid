package com.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Credits extends JFrame {
    public Container background;

    public Credits(){
        this.setSize(new Dimension(800, 600));
        this.setTitle("Asteroids - Créditos");
        background = new Container();
        this.background.setLayout(null);
        this.setContentPane(this.background);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    class Container extends JPanel
    {
        private Image image;

        public void paint(Graphics g){
            image = new ImageIcon(getClass().getResource("/com/img/credits.png")).getImage();

            g.drawImage(image,0,0,getWidth(),getHeight(),this);

            setOpaque(false);

            super.paint(g);
        }
    }
}