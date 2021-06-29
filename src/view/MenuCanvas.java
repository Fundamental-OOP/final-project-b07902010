package view;

import javax.swing.*;

import model.LevelWorld;

import java.awt.*;
import utils.*;

import java.awt.event.*;


public class MenuCanvas extends Canvas {


    private boolean pop_up = false;
    private CancelButton button;
    private LevelWorld world;

    public MenuCanvas(GameView view, LevelWorld world) {
        super(view, "Menu", "../img/menu.png");
        this.world = world;
        this.setLayout(null);
        this.setOpaque(false);
        this.setPosition();
        button = new CancelButton(this);
        this.add(button);
        this.setVisible(false);
    }

    public void paintComponent(Graphics g) {
        renderBackground(g);
    }

    public void renderNextFrame () {
        repaint();  /** calliing paintComponent() */
    }

    public void renderBackground(Graphics g) {
        g.drawImage(background, 0, 0, null);
    }

    public void popUp () {
        pop_up = true;
        this.setVisible(true);
    }

    public void setInvisible () {
        pop_up = false;
        this.setVisible(false); 
    }

    public boolean isPopUp() {
        return pop_up;
    }

    private void setPosition() {
        int window_width = 1440, window_height = 900;
        int w = background.getWidth(null), h = background.getHeight(null);
        int x = (window_width - w) / 2, y = (window_height - h) / 2 ;
        this.setBounds(x, y, w, h);
    }

}

class CancelButton extends JButton implements ActionListener {

    private Image icon_image = ImageReader.readImageFromPath("../img/cancel.png");
    private MenuCanvas menu_canvas;
    private boolean selected = false;

    public CancelButton (MenuCanvas menu_canvas) {
        this.addActionListener(this);
        this.menu_canvas = menu_canvas;
        this.setIcon(new ImageIcon(icon_image));
        this.setBounds(400, 300, 120, 40);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        menu_canvas.setInvisible();
    }

}
