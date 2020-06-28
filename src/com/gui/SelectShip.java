package com.gui;

import com.asteroids.Loader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectShip extends JFrame implements Runnable{
    public Container background;
    public JButton ship1Button;
    public JButton ship2Button;
    public JButton ship3Button;
    public JButton retButton;
    public static boolean close;
    int ejeY = 200;
    Thread thread;

    public SelectShip(){
        this.close = false;
        this.setSize(new Dimension(800, 600));
        this.setTitle("Asteroids - Selección de nave");
        background = new Container();
        this.background.setLayout(null);
        insertButtons();
        this.setContentPane(this.background);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.thread = new Thread(this);
        this.thread.start();
    }

    private void insertButtons() {
        //Agregamos boton de nave 1
        ship1Button = new JButton();
        ship1Button.setOpaque(false);
        try {
            Image img = ImageIO.read(getClass().getResource("/com/img/ovni.png"));
            ship1Button.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        ship1Button.setBounds(150,ejeY,99,75);
        ship1Button.setEnabled(true);
        ship1Button.setMnemonic('1'); // Alt + 1
        this.background.add(ship1Button);

        ship1Button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                WindowGame wg = new WindowGame("/com/img/ovni.png");
                wg.start();
                Loader.playSoundSelect();
                SelectShip.close = true;
            }
        });

        //Agregamos boton de nave 2
        ship2Button = new JButton();
        ship2Button.setOpaque(false);
        try {
            Image img = ImageIO.read(getClass().getResource("/com/img/playerShip1_orange.png"));
            ship2Button.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        ship2Button.setBounds(350,ejeY,99,75);
        ship2Button.setEnabled(true);
        ship2Button.setMnemonic('2'); // Alt + 2
        this.background.add(ship2Button);

        ship2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WindowGame wg = new WindowGame("/com/img/playerShip1_orange.png");
                wg.start();
                Loader.playSoundSelect();
                SelectShip.close = true;
            }
        });

        //Agregamos boton de nave 3
        ship3Button = new JButton();
        ship3Button.setOpaque(false);
        try {
            Image img = ImageIO.read(getClass().getResource("/com/img/playerShip2_red.png"));
            ship3Button.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        ship3Button.setBounds(550,ejeY,99,75);
        ship3Button.setEnabled(true);
        ship3Button.setMnemonic('3'); // Alt + 3
        this.background.add(ship3Button);

        ship3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WindowGame wg = new WindowGame("/com/img/playerShip2_red.png");
                wg.start();
                Loader.playSoundSelect();
                SelectShip.close = true;
            }
        });

        //Agregamos boton regresar
        retButton = new JButton();
        retButton.setOpaque(false);
        try {
            Image img = ImageIO.read(getClass().getResource("/com/img/return.png"));
            retButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        retButton.setBounds(500,450,238,55);
        retButton.setEnabled(true);
        retButton.setMnemonic('r'); // Alt + r
        this.background.add(retButton);

        retButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Loader.playSoundSelect();
                SelectShip.close = true;
            }
        });

    }

    @Override
    public void run() {
        SelectShip.close = false;
        while (!SelectShip.close){
            //Esperando para cerrar
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        SelectShip.close = false;
        this.dispose();
    }


    class Container extends JPanel
    {
        private Image image;

        public void paint(Graphics g){
            image = new ImageIcon(getClass().getResource("/com/img/selectShip.png")).getImage();

            g.drawImage(image,0,0,getWidth(),getHeight(),this);

            setOpaque(false);

            super.paint(g);
        }
    }
}

