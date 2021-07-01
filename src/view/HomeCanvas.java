package view;

import javax.swing.*;

import graphics.AnimationRenderer;
import model.HomeWorld;

import java.awt.*;
import utils.*;

import java.awt.event.*;

public class HomeCanvas extends Canvas {

    private HomeWorld world;

    private AnimationRenderer renderer = new AnimationRenderer("../img/home/background", "background");

    public HomeCanvas(GameView view, HomeWorld world) {
        super(view, "Home", "../img/home/background/background_00.png");
        this.world = world;
        this.add(new StartButton(this, world));
        this.add(new ExitButton(world));
        this.add(new LevelSelectionButton(view, world));
        this.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        renderBackground(g);
    }

    public void renderNextFrame () {
        repaint(); 
    }

    public void renderBackground(Graphics g) {
        this.renderer.render(g);
    } 

}


class StartButton extends CanvasButton {

    private HomeCanvas home_canvas;
    private HomeWorld home_world;

    public StartButton (HomeCanvas home_canvas, HomeWorld home_world) {
        super("../img/../img/home/start_button.png", 438, 606);
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
        super("../img/home/quit_button.png", 897, 612);
        this.homeWorld = homeWorld;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.homeWorld.setNextWorld("None");
    }

}

class LevelSelectionButton extends CanvasButton {

    GameView view;
    HomeWorld homeWorld;
    public LevelSelectionButton (GameView view, HomeWorld homeWorld) {
        super("../img/home/select_level_button.png", 666, 609);
        this.view = view;
        this.homeWorld = homeWorld;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.homeWorld.setNextWorld("Level Selection");
    }

}
