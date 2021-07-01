package view;

import javax.swing.*;

import java.awt.*;
import utils.*;

import java.awt.event.*;

/** 從一個 canvas 按下後 可以前往另一張 canvas */
public abstract class CanvasButton extends JButton implements ActionListener {

    protected Image icon_image;
    protected ImageIcon icon;
    protected boolean selected = false;

    public CanvasButton (String icon_path, int x, int y) {
        this.addActionListener(this);
        this.icon_image = ImageReader.readImageFromPath(icon_path);
        this.icon = new ImageIcon(icon_image);
        this.setIcon(icon);
        this.setOpaque(false);
        this.setBounds(x, y, this.icon_image.getWidth(null), this.icon_image.getHeight(null));
    }

    public abstract void actionPerformed(ActionEvent e);

    public boolean isSelected() {
        return selected;
    }

    public void cancel() {
        this.selected = false;
    }
}