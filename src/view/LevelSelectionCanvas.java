package view;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import model.LevelWorld;
import selector.Selector;

import java.awt.*;
import utils.*;

import java.awt.event.*;
import selector.Button;

import utils.*;



public class LevelSelectionCanvas extends Canvas {


    private final LevelSelector levelSelector = new LevelSelector(3);
    private LevelButtonCanvas[] levelButtonCanvases = new LevelButtonCanvas[10];
    private LevelButtonCanvas currentLevelButtonCanvas;
    private int max_page = 3, num = 0, current_page = 0;

    public LevelSelectionCanvas (GameView view) {
        super(view, "Level Selection", "../img/levelselection.png");
        this.add(new BackButton(view));
        this.add(new LeftButton(this));
        this.add(new RightButton(this));
        
        Button[] buttons = levelSelector.getButtons();
        for (int i = 0; i < buttons.length; i++) {
            System.out.println(i);
            this.levelButtonCanvases[i] = new LevelButtonCanvas(view, buttons[i]);
            this.levelButtonCanvases[i].setBounds(200, 200, 600, 200);
            this.levelButtonCanvases[i].add(buttons[i]);
            this.add(levelButtonCanvases[i]);
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


class LevelSelector extends Selector {

    private Image default_icon_image = ImageReader.readImageFromPath("../img/level_1.png");

    private String[] level_names;
    
    public LevelSelector (int max_selections) {
        super(max_selections);
        level_names = new String[max_selections];
        addSelection("../img/level_1.png");
        addSelection("../img/exit.png");
        addSelection("../img/export.png");
    }

    @Override
    public void addSelection(Object... o) {
        Image image = ImageReader.readImageFromPath( (String)o[0] );
        super.buttons[num_selections].setIcon(new ImageIcon(image));
        num_selections++;
    }

    @Override
    public void setLayout() {
        int w = this.default_icon_image.getWidth(null), h = this.default_icon_image.getHeight(null);
        for (int i = 0; i < max_selections; i++)
            buttons[i].setBounds(i*100, 20, w, h);
    }

}

class LevelButtonCanvas extends Canvas {

    public LevelButtonCanvas (GameView view, Button... buttons) {
        super(view, "Level Selection");
        this.setLayout(new FlowLayout());
        this.setVisible(false);
        for (Button button: buttons)
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