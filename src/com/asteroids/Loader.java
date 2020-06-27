package com.asteroids;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

}
