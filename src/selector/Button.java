package selector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import java.awt.event.*;

import utils.*;

public class Button extends JButton implements ActionListener {

    private final Selector selector;
    private Image default_image = ImageReader.readImageFromPath("../img/default_button.png");
    private Image icon_image;
    private Image preview_image;
    private String name;
    private int index;
    private boolean selected;


    public Button (Selector selector, int index) {
        this.index = index;
        this.icon_image = default_image;
        this.selector = selector;
        this.setIcon(new ImageIcon(this.icon_image));
        this.setOpaque(false);
        this.addActionListener(this);
        this.setPosition(300+index*120, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(this.name + " is selected!");
        if (!this.selected) {
            this.selected = true;
            this.selector.setCurrentSelection(this.index);
        }
        else {
            this.selected = false;
            this.selector.cleanSelection();
        }
    }

    public void set(String name, String image_path, String preview_path) {
        this.name = name;
        this.icon_image = ImageReader.readImageFromPath(image_path);
        this.preview_image = ImageReader.readImageFromPath(preview_path);
        ImageIcon icon = new ImageIcon(this.icon_image);
        this.setIcon(icon);
    }

    public void reset() {
        this.setIcon(new ImageIcon(default_image));
        this.preview_image = null;
        this.selected = false;
    }

    public boolean isSelected() {
        return selected;
    }

    public void cancel() {
        this.selected = false;
    }

    public void setPosition(int x, int y) {
        this.setBounds(x, y, icon_image.getWidth(null), icon_image.getHeight(null));
    }

    public Image getPreviewImage() {
        return this.preview_image;
    }
}
