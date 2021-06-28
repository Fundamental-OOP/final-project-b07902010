package utils;

import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/** Utilities for Image Input **/
public class ImageReader {
    
    /** 從 path_to_images 去讀底下的所有開頭是 prefix 的圖片 (限 .png) **/
    public static List<Image> readImagesFromPrefix (String path_to_images, String prefix) {
        
        int max_index = new File(path_to_images).listFiles().length;
        List<Image> images = new ArrayList<>();
        
        for (int i = 0; i < max_index; i++) {
            /** directory 結尾不可以加 "/"! */
            String image_path;
            if (max_index > 10)
                image_path = path_to_images + "/" + prefix + "_" + i/10 + i%10 + ".png";
            else
                image_path = path_to_images + "/" + prefix + "_" + i + ".png";
            File f = new File(image_path);
            if (f.exists()) {
                try {
                    images.add(ImageIO.read(f));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return images;
    }

    /** 從 path 去讀一張圖片 **/
    public static Image readImageFromPath (String image_path) {
        File f = new File(image_path);
        try {
            return ImageIO.read(f);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}