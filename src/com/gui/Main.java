package com.gui;

import com.asteroids.AsteroidsGame;
import com.asteroids.Loader;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {
    public static Clip sound;
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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playSound();
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
                Loader.playSoundSelect();
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
                Loader.playSoundSelect();
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

    public void playSound(){
        try {
            sound = AudioSystem.getClip();
            sound.open(AudioSystem.getAudioInputStream(new File(AsteroidsGame.soundMain)));
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

