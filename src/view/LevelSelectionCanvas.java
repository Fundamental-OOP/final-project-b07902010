package view;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import model.LevelWorld;
import selector.Selector;

import java.awt.*;
import utils.*;

import java.awt.event.*;
import selector.Button;



public class LevelSelectionCanvas extends Canvas implements MouseInputListener {


    private final LevelSelector levelSelector = new LevelSelector(3);
    private LevelButtonCanvas[] levelButtonCanvases = new LevelButtonCanvas[10];
    private LevelButtonCanvas currentLevelButtonCanvas;
    private int max = 3, num = 0;

    public LevelSelectionCanvas (GameView view) {
        super(view, "Level Selection", "../img/levelselection.png");
        this.addMouseListener(this);
        this.add(new BackButton(view));
        Button[] buttons = levelSelector.getButtons();
        for (int i = 0; i < buttons.length; i++) {
            System.out.println(i);
            this.levelButtonCanvases[i] = new LevelButtonCanvas(view, buttons[i]);
            this.levelButtonCanvases[i].setBounds(200, 200*i, 600, 200);
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


    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        this.currentLevelButtonCanvas.setVisible(false);
        num = ( num+1 ) % max;
        
        this.currentLevelButtonCanvas = this.levelButtonCanvases[num];
        this.currentLevelButtonCanvas.setVisible(true);
    }


}


class LevelSelector extends Selector {

    private Image default_icon_image = ImageReader.readImageFromPath("../img/level_1.png");

    private String[] level_names;
    

    public LevelSelector (int max_selections) {
        super(max_selections);
        level_names = new String[max_selections];
        addSelection("../img/level_1.png");
        addSelection("../img/level_1.png");
        addSelection("../img/level_1.png");
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