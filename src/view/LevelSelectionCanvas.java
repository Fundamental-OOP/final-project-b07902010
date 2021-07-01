package view;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import model.LevelSelectionWorld;
import model.LevelWorld;
import selector.Selector;

import java.awt.*;
import utils.*;

import java.awt.event.*;
import selector.Button;

import utils.*;
import selector.*;



public class LevelSelectionCanvas extends Canvas {


    private final LevelSelector levelSelector;
    private LevelButtonCanvas[] levelButtonCanvases = new LevelButtonCanvas[10];
    private LevelButtonCanvas currentLevelButtonCanvas;
    private int max_page = 3, num = 0, current_page = 0;
    private LevelSelectionWorld world;

    public LevelSelectionCanvas (GameView view, LevelSelectionWorld world) {
        super(view, "Level Selection", "../img/levelselection.png");
        
        this.add(new BackButton(view));
        this.add(new LeftButton(this));
        this.add(new RightButton(this));
        this.world = world;
        this.levelSelector = world.getSelector();
        
        LevelButton[] buttons = levelSelector.getButtons();
        for (int i = 0; i < buttons.length; i+=3) {
            this.levelButtonCanvases[i/3] = new LevelButtonCanvas(view, buttons[i], buttons[i+1], buttons[i+2]);
            this.levelButtonCanvases[i/3].setBounds(200, 200, 600, 200);
            this.add(levelButtonCanvases[i/3]);
        }
        
        this.currentLevelButtonCanvas = this.levelButtonCanvases[0];
        this.currentLevelButtonCanvas.setVisible(true);
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



    public void scrollLeft() {
        if (current_page > 0) this.current_page --;
        this.changeLevelButtonCanvas(this.current_page);
    }

    public void scrollRight() {
        if (current_page < max_page-1) this.current_page ++;
        this.changeLevelButtonCanvas(this.current_page);
    }

    public void changeLevelButtonCanvas (int index) {
        if (index >= 0 && index < this.max_page) {
            this.currentLevelButtonCanvas.setVisible(false);
            this.currentLevelButtonCanvas = this.levelButtonCanvases[index];
            this.currentLevelButtonCanvas.setVisible(true);
        }
    }


}



class LevelButtonCanvas extends Canvas {

    public LevelButtonCanvas (GameView view, LevelButton... buttons) {
        super(view, "Level Selection");
        this.setLayout(new FlowLayout());
        this.setVisible(false);
        this.setOpaque(false);
        for (LevelButton button: buttons)
            this.add(button);
    }

}

class BackButton extends CanvasButton {
    GameView view;
    public BackButton (GameView view) {
        super("../img/home.png", 600, 200);
        this.view = view;
    }
    public void actionPerformed(ActionEvent e) {
        view.getWorld().setNextWorld("Home");
    }
}

class LeftButton extends JLabel implements MouseInputListener {
    Image image = ImageReader.readImageFromPath("../img/left.png");
    ImageIcon icon = new ImageIcon(image); 
    LevelSelectionCanvas canvas;
    public LeftButton (LevelSelectionCanvas canvas) {
        this.setIcon(icon);
        this.setBounds(40, 700, 100, 100);
        this.setOpaque(false);
        this.addMouseListener(this);
        this.canvas = canvas;
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("left button");
        this.canvas.scrollLeft();
    }
}

class RightButton extends JLabel implements MouseInputListener {
    Image image = ImageReader.readImageFromPath("../img/right.png");
    ImageIcon icon = new ImageIcon(image); 
    LevelSelectionCanvas canvas;
    public RightButton (LevelSelectionCanvas canvas) {
        this.setIcon(icon);
        this.setBounds(1000, 700, 100, 100);
        this.setOpaque(false);
        this.addMouseListener(this);
        this.canvas = canvas;
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("right button");
        this.canvas.scrollRight();
    }

}