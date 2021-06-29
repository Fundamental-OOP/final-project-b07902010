package game;


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

import selector.*;
import selector.Button;

public class GameView extends JFrame {
    
    private World world;
    public Canvas canvas;

    int width = 1440, height = 900;

    public GameView (int width, int height)  {
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
        this.setContentPane(canvas);
        this.setVisible(true);
        this.canvas.setVisible(true);
    }
    public void update () {
        this.canvas.renderNextFrame();
    }
    public World getWorld() {
        return this.world;
    }

    public void changeCanvas() {}

}




// class Background implements Renderee {
    
//     ImageRenderer renderer = new ImageRenderer("./img/background.png");
    
//     public Renderer getRenderer() {
//         return renderer;
//     }
// }