package com.asteroids;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Clase encargada de cargar algunas imagenes estrategicas
 * de los elementos dentro del juego y reproducción de sonidos
 */
public class Loader {
    public static BufferedImage speed = ImageLoader("/com/img/speed.png");
    public static BufferedImage count3 = ImageLoader("/com/img/count3.png");
    public static BufferedImage count2 = ImageLoader("/com/img/count2.png");
    public static BufferedImage count1 = ImageLoader("/com/img/count1.png");
    public static BufferedImage count0 = ImageLoader("/com/img/count0.png");
    public static BufferedImage ImageLoader(String path){
        try {
            return ImageIO.read(Loader.class.getResource(path));
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo encargado de respoducir un sonido cuando un boton es seleccionado
     */
    public static void playSoundSelect(){
        try {
            Clip sound = AudioSystem.getClip();
            sound.open(AudioSystem.getAudioInputStream(new File(AsteroidsGame.soundSelect)));
            sound.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo encargado de reproducir un sonido cuando la nave explota
     */
    public static void playSoundExplosion(){
        try {
            Clip sound = AudioSystem.getClip();
            sound.open(AudioSystem.getAudioInputStream(new File(AsteroidsGame.soundExplosion)));
            sound.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

}
