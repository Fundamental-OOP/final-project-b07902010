package game;

import javax.lang.model.util.ElementScanner14;
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

/** Draw some baseline */
/** Gamecanvas */
public class Canvas extends JPanel implements MouseInputListener {
    private LevelWorld world;
    private GameView view;
    private static Point lastClickedPosition;
    private Selector selector;
    private Button menu_button = new Button("Menu", "../img/menu_button.png", "../img/menu_button.png");    

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
        if (world.getRenderees().size() > 0) 
            for (Renderee renderee : world.getRenderees() )
                renderee.getRenderer().render(g);
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

    public void setWorld(LevelWorld world) {
        this.world = world;
        this.setSelector(world.getSelector());
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
        for (Button button: selector.getButtons())
            this.add(button);
    }

    public void mouseClicked(MouseEvent e) {
        Point p = MouseInfo.getPointerInfo().getLocation();
        System.out.println("Clicked! " + p.x + " " + p.y);
        String current_selection = this.selector.getCurrentSelection();
        if (current_selection == "Menu")
            System.out.println("Clicked Menu!");
        else if (current_selection != null)
            world.addAlly(current_selection, 1, 2);
        else
            System.out.println("Current selection is null");
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}

}
