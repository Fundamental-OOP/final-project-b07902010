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
    private Selector selector;
    public Canvas canvas;

    int width = 1440, height = 900;

    public GameView (int width, int height)  {
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.selector = new Selector();
        this.canvas = new Canvas(this, selector);
        this.selector.addSelection("Corgi", "./img/corgi_button_2.png", "./img/corgi_preview.png");
        this.setContentPane(canvas);
        this.setVisible(true);
    }

    public void setWorld(World world) {
        this.world = world;
        canvas.setWorld(world);
    }

    public void update () {
        this.canvas.renderNextFrame();
    }
    public World getWorld() {
        return this.world;
    }

    public void changeCanvas() {}

}

/** Draw some baseline */
/** Gamecanvas */
class Canvas extends JPanel implements MouseInputListener {
    private World world;
    private GameView view;
    private static Point lastClickedPosition;
    private Selector selector;    

    public Canvas (GameView view) {
        this.setLayout(null);
        this.view = view;
        this.addMouseListener(this);
    }

    public Canvas (GameView view, Selector selector) {
        this.setLayout(null);
        this.view = view;
        this.selector = selector;
        this.addMouseListener(this);
        for (Button button: selector.getButtons())
            this.add(button);
    }

    public void paintComponent(Graphics g) {  
        renderBackground(g);
        for (Renderee renderee : view.getWorld().getRenderees() ) {
            renderee.getRenderer().render(g);
        }
        renderPreview(g);
    }

    public void renderPreview(Graphics g) {
        Point p = MouseInfo.getPointerInfo().getLocation();
        Image preview_image = this.selector.getSelectionPreview();
        if (preview_image != null)
            g.drawImage(preview_image, p.x-50, p.y-100, null);
    }

    public void renderNextFrame () {
        repaint();  /** calliing paintComponent() */
    }

    public void renderBackground(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(300, 150, 900, 600);
        g.setColor(Color.BLACK);
        for (int i = 0; i < 9; i ++) {
            for (int j =  0; j < 5; j++) {
                g.drawRect(300+i*100, 150+j*120, 100, 120);
            }
        }
        g.setColor(Color.PINK);
        g.fillRect(20, 150, 260, 600);
        g.setColor(Color.BLACK);
        g.fillRect(300, 20, 900, 120);
        g.fillRect(1220, 20, 180, 120);
    }

    public static Point getLastClickedPosition() {
        return lastClickedPosition;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    public void mouseClicked(MouseEvent e) {
        Point p = MouseInfo.getPointerInfo().getLocation();
        System.out.println("Clicked! " + p.x + " " + p.y);
        // world.addAlly(this.selector.getCurrentSelection(), 1, 2);
        lastClickedPosition = p;
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}

}




class Background implements Renderee {
    
    ImageRenderer renderer = new ImageRenderer("./img/background.png");
    
    public Renderer getRenderer() {
        return renderer;
    }
}