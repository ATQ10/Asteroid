package com.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    public menuPrincipal menu;
    public JButton playButton;
    public JButton creditsButton;

    public Main(){
        this.setSize(new Dimension(800, 600));
        this.setTitle("Asteroids - MENU");
        menu = new menuPrincipal();
        this.menu.setLayout(null);
        insertButtons();
        this.setContentPane(this.menu);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    private void insertButtons() {
        //Agregamos boton de play
        playButton = new JButton();
        playButton.setOpaque(false);
        try {
            Image img = ImageIO.read(getClass().getResource("/com/img/buttonplay.png"));
            playButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        playButton.setBounds(270,270,238,55);
        playButton.setEnabled(true);
        playButton.setMnemonic('p'); // Alt + p
        this.menu.add(playButton);

        playButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new SelectShip().setVisible(true);
            }
        });

        //Agregamos boton de creditos
        creditsButton = new JButton();
        creditsButton.setOpaque(false);
        try {
            Image img = ImageIO.read(getClass().getResource("/com/img/buttoncredits.png"));
            creditsButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        creditsButton.setBounds(270,360,238,55);
        creditsButton.setEnabled(true);
        creditsButton.setMnemonic('c'); // Alt + c
        this.menu.add(creditsButton);

        creditsButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new Credits().setVisible(true);
            }
        });
    }


    class menuPrincipal extends JPanel
    {
        private Image image;

        public void paint(Graphics g){
            image = new ImageIcon(getClass().getResource("/com/img/asteroids.png")).getImage();

            g.drawImage(image,0,0,getWidth(),getHeight(),this);

            setOpaque(false);

            super.paint(g);
        }
    }
}

