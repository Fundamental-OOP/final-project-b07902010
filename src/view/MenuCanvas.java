package view;

import javax.swing.*;

import model.LevelWorld;

import java.awt.*;
import utils.*;

import java.awt.event.*;


public class MenuCanvas extends Canvas {


    private boolean pop_up = false;
    
    private LevelWorld world;

    public MenuCanvas(GameView view, LevelWorld world) {
        super(view, "Menu", "../img/menu.png");
        this.world = world;
        this.setLayout(null);
        this.setOpaque(false);
        this.setBounds(0, 0, 1440, 900);
        this.add(new CancelButton(view, this));
        this.add(new RestartButton(view, this));
        this.add(new HomeButton(view, this));
        
        this.setVisible(false);
    }

    public void paintComponent(Graphics g) {
        renderBackground(g);
    }

    public void renderNextFrame () {
        repaint();  /** calliing paintComponent() */
    }

    public void renderBackground(Graphics g) {
        int window_width = 1440, window_height = 900;
        int w = background.getWidth(null), h = background.getHeight(null);
        int x = (window_width - w) / 2, y = (window_height - h) / 2 ;
        g.drawImage(background, x, y, null);
    }

    public void popUp () {
        pop_up = true;
        this.setVisible(true);
    }

    public void setInvisible () {
        view.getCanvas().setEnabled(true);
        for (Component component: view.getCanvas().getComponents())
            component.setEnabled(true);
        pop_up = false;
        this.setVisible(false); 
    }

    public boolean isPopUp() {
        return pop_up;
    }
}

class CancelButton extends CanvasButton {
    MenuCanvas menu_canvas;
    GameView view;
    public CancelButton (GameView view, MenuCanvas menu_canvas) {
        super("../img/cancel.png", 600, 500);
        this.menu_canvas = menu_canvas;
    }
    public void actionPerformed(ActionEvent e) {
        menu_canvas.setInvisible();
    }
}

class RestartButton extends CanvasButton {
    MenuCanvas menu_canvas;
    GameView view;

    public RestartButton (GameView view, MenuCanvas menu_canvas) {
        super("../img/restart.png", 300, 500);
        this.menu_canvas = menu_canvas;
        this.view = view;

    }
    public void actionPerformed(ActionEvent e) {
        menu_canvas.setInvisible();
        ((LevelWorld) view.getWorld() ).reset();
    }
}

class HomeButton extends CanvasButton {
    MenuCanvas menu_canvas;
    GameView view;
    public HomeButton (GameView view, MenuCanvas menu_canvas) {
        super("../img/home.png", 600, 200);
        this.menu_canvas = menu_canvas;
        this.view = view;
    }
    public void actionPerformed(ActionEvent e) {
        menu_canvas.setInvisible();
        view.getWorld().setNextWorld("Home");
    }
}