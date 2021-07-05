package graphics;

import java.awt.*;
import utils.*;

public class ImageRenderer implements Renderer {
    private Image image;
    public int x = 0, y = 0;

    public ImageRenderer (Image image) {
        this.image = image;
    }

    public ImageRenderer (String image_path) {
        this.image = ImageReader.readImageFromPath(image_path);
    }
    
    public void render (Graphics g) {
        g.drawImage(image, this.x, this.y, null);
    }

    public void setPosition (int x, int y) {
        this.x = x;
        this.y = y;
    }
}
