package view;

import javax.swing.*;

import model.HomeWorld;

import java.awt.*;
import utils.*;

import java.awt.event.*;

public class HomeCanvas extends Canvas {

    private int w = background.getWidth(null), h = background.getHeight(null);
    private StartButton button;
    private boolean pop_up = true;
    private HomeWorld world;

    public HomeCanvas(GameView view, HomeWorld world) {
        super(view, "Home", "../img/main.png");
        this.world = world;
        this.setLayout(null);
        this.setBounds(0, 0, w, h);
        button = new StartButton(this, world);
        this.add(button);
        this.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        renderBackground(g);
    }

    public void renderNextFrame () {
        while (pop_up)
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

}


class StartButton extends JButton implements ActionListener {
    private Image icon_image = ImageReader.readImageFromPath("../img/start.png");
    private HomeCanvas home_canvas;
    private HomeWorld home_world;
    private boolean selected = false;

    public StartButton (HomeCanvas home_canvas, HomeWorld home_world) {
        this.addActionListener(this);
        this.home_canvas = home_canvas;
        this.home_world = home_world;
        this.setIcon(new ImageIcon(icon_image));
        this.setBounds(700, 600, 300, 100);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("start:)");
        this.home_canvas.setInvisible();
        this.home_world.setNextWorld("Level");
    }

}