package view;

import javax.swing.*;
import java.awt.*;
import utils.*;

public class Canvas extends JPanel {
    protected GameView view;
    protected Image background = null;
    protected String type;

    public Canvas (GameView view,  String type, String background_path) {
        this.view = view;
        this.type = type;
        this.background = ImageReader.readImageFromPath(background_path);
    }
    
    public Canvas (GameView view, String type) {
        this.view = view;
        this.type = type;
    }
    
    public String getType () {
        return this.type;
    }

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
