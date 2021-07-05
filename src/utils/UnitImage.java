package utils;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UnitImage {

    private static Map<String, List<Image>> animations = new HashMap<String, List<Image>>();
    private static Map<String, Image> icons = new HashMap<String, Image>();
    private static Map<String, Image> previews = new HashMap<String, Image>();
    private static Map<String, Image> bullets = new HashMap<String, Image>();

    private String path = FilePath.getImageFolderPath();

    static private String[] states = new String[] {"walk", "idle", "attack", "beAttack", "dead"};
    static private String[] allies = new String[] {"MiMiMaoMao", "Oppa", "Saxophone", "Pooper", "Chihuahua", "Doge", "Pingu", "RainbowCat"};
    static private String[] shooters = new String[] {"MiMiMaoMao", "Chihuahua", "RainbowCat"};
    static private String[] enemies = new String[] {"ADA", "CNLab", "DSA", "FOOP"};
    
    public UnitImage() {
        String path_to_images, prefix, image_path;
        /** read ally animation */
        for (String ally: allies) {
            for (String state: states) {
                path_to_images = path + "/ally/" + ally + "/" + state;
                prefix = state;
                animations.put(ally+ ":" + state, ImageReader.readImagesFromPrefix(path_to_images, prefix));
            }   
        }
        /** read enemy animation */
        for (String enemy: enemies) {
            for (String state: states) {
                path_to_images = path + "/enemy/" + enemy + "/" + state;
                prefix = state;
                animations.put(enemy+ ":" + state, ImageReader.readImagesFromPrefix(path_to_images, prefix));
            }   
        }
        /** read icons image */
        for (String ally: allies) {
            image_path = path + "/level/icon/icon_" + ally + ".png";
            icons.put(ally, ImageReader.readImageFromPath(image_path));   
        }

        /** read preview image */
        for (String ally: allies) {
            image_path = path + "/ally/" + ally + "/preview.png";
            previews.put(ally, ImageReader.readImageFromPath(image_path));   
        }

        for (String shooter: shooters) {
            image_path = path + "/bullet/" + shooter + "/bullet.png";
            bullets.put(shooter, ImageReader.readImageFromPath(image_path));   
        }


        
    }

    static public List<Image> getUnitAnimation (String unit, String state) {
        return animations.get(unit + ":" + state);
    }

    static public Image getPreview (String unit) {
        return previews.get(unit);
    }

    static public Image getIcon (String unit) {
        return icons.get(unit);
    }
    
    static public Image getBullet (String unit) {
        return bullets.get(unit);
    }
    
}