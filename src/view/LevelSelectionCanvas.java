package view;

import javax.swing.*;

import model.LevelWorld;
import selector.Selector;

import java.awt.*;
import utils.*;

import java.awt.event.*;
import selector.Button;

public class LevelSelectionCanvas extends Canvas {

    private LevelSelector levelSelector = new LevelSelector(3);

    public LevelSelectionCanvas (GameView view) {
        super(view, "Level Selection", "../img/levelselection.png");
        this.setLayout(new FlowLayout());
        Image img = ImageReader.readImageFromPath( "../img/menu.png" );
        for (Button button: levelSelector.getButtons())
            this.add(button);
    }

    public void paintComponent(Graphics g) {
        System.out.println(levelSelector.getCurrentSelection());
        renderBackground(g);
    }

    public void renderNextFrame () {
        repaint(); 
    }

    public void renderBackground(Graphics g) {
        g.drawImage(background, 0, 0, null);
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