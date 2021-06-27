package graphics;

import java.awt.*;


public class ImageRenderer implements Renderer {
    private Image image;
    public int x = 0, y = 0;

    public ImageRenderer (Image image) {
        this.image = image;
    }
    
    public void render (Graphics g) {
        g.drawImage(image, this.x, this.y, null);
    }

    public void setPosition (int x, int y) {
        this.x = x;
        this.y = y;
    }
}
