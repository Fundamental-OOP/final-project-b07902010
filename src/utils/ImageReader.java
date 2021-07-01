package utils;

import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/** Utilities for Image Input **/
public class ImageReader {
    /** directory 結尾不可以加 "/"! */
    /** 從 path_to_images 去讀底下的所有開頭是 prefix 的圖片 (限 .png) **/
    public static List<Image> readImagesFromPrefix (String path_to_images, String prefix) {
        File dir = new File(path_to_images);
        if (dir == null) {
            System.out.println("<Error> [Image Reader] folder " + path_to_images + " does not exsit.");
            return null;
        }
        int max_index = dir.listFiles().length;
        List<Image> images = new ArrayList<>();
        String image_path = path_to_images + "/" + prefix + "_" + 0 + ".png";
        File tmp = new File(image_path);
        boolean flag = tmp.exists();
        for (int i = 0; i < max_index; i++) {
            if (!flag) image_path = path_to_images + "/" + prefix + "_" + i/10 + i%10 + ".png";
            else       image_path = path_to_images + "/" + prefix + "_" + i + ".png";
            File f = new File(image_path);
            if (f.exists()) {
                try {
                    images.add(ImageIO.read(f));
                } catch (IOException e) {
                    System.out.println("<Error> [Image Reader] readImagesFromPrefix(): file " + image_path + " does not exsit.");
                }
            }
        }
        System.out.println("[ImageReader] Successfully read image " + path_to_images 
                            + ", prefix = " + prefix 
                            + ", max index = " + max_index 
                            + ", image size = " + images.size());
        return images;
    }

    /** 從 path 去讀一張圖片 **/
    public static Image readImageFromPath (String image_path) {
        File f = new File(image_path);
        try {
            Image image = ImageIO.read(f);
            System.out.println("[Image Reader] Successfully read image " + image_path); 
            return image;
        } catch (IOException e) {
            System.out.println("<Error> [Image Reader] readImageFromPath(): file " + image_path + " does not exsit.");
            return null;
        }
    }
}
