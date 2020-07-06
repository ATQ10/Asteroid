package com.gui;

import com.asteroids.Loader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase encargada de mostrar los creditos del juego
 */
public class Credits extends JFrame implements Runnable{
    public Container background;
    public JButton retButton;
    public static boolean close;
    Thread thread;

    public Credits(){
        this.close = false;
        this.setSize(new Dimension(800, 600));
        this.setTitle("Asteroids - Créditos");
        background = new Container();
        this.background.setLayout(null);
        insertButtons();
        this.setContentPane(this.background);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.thread = new Thread(this);
        this.thread.start();
    }

    /**
     * Metodo encargado de insertar el boton de regresar
     */
    private void insertButtons() {
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
                Credits.close = true;
            }
        });

    }

    /**
     * Metodo implementado de un hilo cuyo principal objetivo
     * será dejar de disponer de la ventana de creditos
     */
    @Override
    public void run() {
        Credits.close = false;
        while (!Credits.close){
            //Esperando para cerrar
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Credits.close = false;
        this.dispose();
    }

    /**
     * Clase encargada de colocar el fondo de la ventana de creditos
     */
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