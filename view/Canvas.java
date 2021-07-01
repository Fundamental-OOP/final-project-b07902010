package view;

import javax.swing.*;
import java.awt.*;
import utils.*;

public class Canvas extends JPanel {
    protected GameView view;
    protected Image background = null;
    protected String type;

    public Canvas (GameView view,  String type, String background_path, int x, int y) {
        this.view = view;
        this.type = type;
        this.setLayout(null);
        this.background = ImageReader.readImageFromPath(background_path);
        this.setBounds(x, y, this.background.getWidth(null), this.background.getHeight(null));
    }
    
    public Canvas (GameView view,  String type, String background_path) {
        this.view = view;
        this.type = type;
        this.setLayout(null);
        this.background = ImageReader.readImageFromPath(background_path);
        this.setBounds(0, 0, this.background.getWidth(null), this.background.getHeight(null));
    }
    
    public Canvas (GameView view, String type) {
        this.view = view;
        this.type = type;
        this.setLayout(null);
        this.setBounds(0, 0, 1440, 900);
    }
    
    public String getType () {
        return this.type;
    }

    // public void paintComponent(Graphics g) {}

    public void renderNextFrame () {
        repaint();  /** calliing paintComponent() */
    }

    public void setBackground (String background_path) {
        this.background = ImageReader.readImageFromPath(background_path);
    }
    public void setBackground (Image background) {
        this.background = background;
    }
    
}
