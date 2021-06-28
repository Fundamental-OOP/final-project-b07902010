package selector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


import model.*;
import graphics.*;

import java.awt.*;
import java.io.File;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.text.GapContent;

import java.util.ArrayList;
import java.util.List;

import graphics.*;
import graphics.Renderer;
import utils.*;

import java.util.Map;
import java.util.HashMap;

public class Button extends JButton implements ActionListener {

    private Selector selector;
    private Image default_image = ImageReader.readImageFromPath("./img/default_button.png");
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
            this.selector.setCurrentSelection(-1);
        }
    }

    public void set(String name, String image_path, String preview_path) {
        this.icon_image = ImageReader.readImageFromPath(image_path);
        this.preview_image = ImageReader.readImageFromPath(preview_path);
        ImageIcon icon = new ImageIcon(this.icon_image);
        this.setIcon(icon);
        this.name = name;
    }


    public boolean isSelected() {
        return selected;
    }

    public void cancel() {
        this.selected = false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(int x, int y) {
        System.out.println("!!!!!");
        this.setBounds(x, y, icon_image.getWidth(null), icon_image.getHeight(null));
    }

    public Image getPreviewImage() {
        return this.preview_image;
    }
}
