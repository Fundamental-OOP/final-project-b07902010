package selector;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.*;
import javax.swing.event.MouseInputListener;

import model.LevelSelectionWorld;
import utils.*;
import view.LevelSelectionCanvas;

public class LevelButton extends JLabel implements MouseInputListener {
    
    private LevelSelector levelSelector;
    private int index;
    private Image image;
    private ImageIcon icon;

    public LevelButton (LevelSelector levelSelector, int index) {
        this.levelSelector = levelSelector;
        this.index = index;
        this.setOpaque(false);
        this.addMouseListener(this);
        this.image = ImageReader.readImageFromPath( "../img/levelbotton/level_0" + (index+1) + ".png" );
        this.icon = new ImageIcon(image);
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("[LevelButton] mouseClicked(): Button " + (index+1) + " was selected");
        this.levelSelector.selectLevel(this.index);
    }

    public void enable() {
        this.setIcon(icon);
    }


}