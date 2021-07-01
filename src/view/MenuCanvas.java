package view;

import javax.swing.*;

import model.LevelWorld;

import java.awt.*;
import utils.*;

import java.awt.event.*;


public class MenuCanvas extends Canvas {


    private boolean pop_up = false;
    
    private LevelCanvas levelCanvas;
    private GameView view;

    public MenuCanvas(GameView view, LevelCanvas levelCanvas) {
        super(view, "Menu", "../img/menu/menu.png");
        this.levelCanvas = levelCanvas;
        this.view = view;
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
        // int window_width = 1440, window_height = 900;
        // int w = background.getWidth(null), h = background.getHeight(null);
        // int x = (window_width - w) / 2, y = (window_height - h) / 2 ;
        g.drawImage(background, 360, 225, null);
    }

    public void popUp () {
        pop_up = true;
        this.setVisible(true);
    }

    public void setInvisible () {
        pop_up = false;
        this.setVisible(false);
        this.view.antiUnAnDeImPause();
        this.levelCanvas.getWorld().antiUnAnDeImPause();
        this.levelCanvas.enableCanvas();
    }
    public boolean isPopUp() {
        return pop_up;
    }
}

class CancelButton extends CanvasButton {
    MenuCanvas menu_canvas;
    GameView view;
    public CancelButton (GameView view, MenuCanvas menu_canvas) {
        super("../img/menu/x_button.png", 1032, 243);
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
        super("../img/menu/restart_button.png", 471, 534);
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
        super("../img/menu/home_button.png", 744, 534);
        this.menu_canvas = menu_canvas;
        this.view = view;
    }
    public void actionPerformed(ActionEvent e) {
        this.view.antiUnAnDeImPause();
        this.view.getWorld().antiUnAnDeImPause();
        view.getWorld().setNextWorld("Home");
    }
}