package view;

import javax.swing.*;

import model.HomeWorld;

import java.awt.*;
import utils.*;

import java.awt.event.*;

public class HomeCanvas extends Canvas {

    private HomeWorld world;

    public HomeCanvas(GameView view, HomeWorld world) {
        super(view, "Home", "../img/main.png");
        this.world = world;
        this.add(new StartButton(this, world));
        this.add(new ExitButton(world));
        this.add(new LevelButton(view, world));
        this.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        renderBackground(g);
    }

    public void renderNextFrame () {
        repaint(); 
    }

    public void renderBackground(Graphics g) {
        g.drawImage(background, 0, 0, null);
    } 

}


class StartButton extends CanvasButton {

    private HomeCanvas home_canvas;
    private HomeWorld home_world;

    public StartButton (HomeCanvas home_canvas, HomeWorld home_world) {
        super("../img/start.png", 800, 600);
        this.home_canvas = home_canvas;
        this.home_world = home_world;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("start:)");
        this.home_canvas.setVisible(false);
        this.home_world.setNextWorld("Level");
    }
}


class ExitButton extends CanvasButton {

    HomeWorld homeWorld;
    public ExitButton (HomeWorld homeWorld) {
        super("../img/exit.png", 300, 600);
        this.homeWorld = homeWorld;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.homeWorld.setNextWorld("None");
    }

}

class LevelButton extends CanvasButton {

    GameView view;
    HomeWorld homeWorld;
    public LevelButton (GameView view, HomeWorld homeWorld) {
        super("../img/level.png", 300, 400);
        this.view = view;
        this.homeWorld = homeWorld;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.view.setCanvas("Level Selection");
    }

}
