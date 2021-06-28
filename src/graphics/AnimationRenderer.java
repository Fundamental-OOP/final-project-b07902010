package graphics;

import java.awt.*;
import java.util.List;
import utils.*;


public class AnimationRenderer implements Renderer {
    private int index = 0, x = 0, y = 0;
    private List<Image> images;
    String tmp;
    public AnimationRenderer (String path_to_images, String prefix) {
        this.images = ImageReader.readImagesFromPrefix(path_to_images, prefix);
        this.tmp = path_to_images + prefix;
    }
    public AnimationRenderer (String path_to_images, String prefix, int x, int y) {
        this.images = ImageReader.readImagesFromPrefix(path_to_images, prefix);
        this.x = x;
        this.y = y;
        this.tmp = path_to_images + prefix;
    }
    public AnimationRenderer (List<Image> images) {
        this.images = images;
    }
    public AnimationRenderer (List<Image> images, int x, int y) {
        this.images = images;
        this.x = x;
        this.y = y;
    }


    /** args[0] = new x, 
     *  args[1] = new y, 
     *  args[2] = new index, index = 0 if is not given  */
    public void restart (int... args) {
        this.index = 0;
        if (args.length >= 1) this.x = args[0];
        if (args.length >= 2) this.y = args[1];
        if (args.length >= 3) this.index = args[2];
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void render(Graphics g) {        if (images.size() > 0) {
            g.drawImage(images.get(index), x, y, null);
            index = (index + 1) % images.size();
        }

    }
}
