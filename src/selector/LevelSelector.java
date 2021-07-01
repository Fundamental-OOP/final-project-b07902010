package selector;


import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.*;
import javax.swing.event.MouseInputListener;

import model.LevelSelectionWorld;
import utils.*;
import view.LevelSelectionCanvas;


public class LevelSelector {

    private final String[] levelNames;
    private LevelSelectionWorld world;
    private int num_selections = 0, max_selections = 0, current_selection = -1;
    private final LevelButton[] levelButtons;
    private Image defaultImage =  ImageReader.readImageFromPath( "../img/selectLevel/level_button/level_00_0.png" );
    private ImageIcon defaultIcon = new ImageIcon(defaultImage);

    public LevelSelector (LevelSelectionWorld world, int max_selections) {
        this.world = world;
        this.max_selections = max_selections;
        this.levelNames = new String[max_selections];
        this.levelButtons = new LevelButton[max_selections];
        for (int i = 0; i < max_selections; i++)
            levelButtons[i] = new LevelButton(this, i);
        this.setLayout();
    }

    public void addSelection(String name) {
        System.out.println("[LevelSelector] addSelection(): " + num_selections + " " + name);
        this.levelNames[num_selections] = name;
        this.levelButtons[num_selections].enable();
        num_selections++;
    }

    public void setLayout() {
        int w = this.defaultImage.getWidth(null), h = this.defaultImage.getHeight(null);
        for (int i = 0; i < max_selections; i++)
            levelButtons[i].setBounds((i%3)*300, 0, w, h);
    }
    
    public LevelButton[] getButtons() {
        return this.levelButtons;
    }

    public void clear() {
        num_selections = 0;
        current_selection = -1;
        for (LevelButton button: levelButtons)
            button.setIcon(defaultIcon);
    }

    public void selectLevel(int index) {
        System.out.println("[LevelSelector] selectLevel(): levelName " + levelNames[index] + " was selected");
        if (index < num_selections)
            this.world.selectLevel(levelNames[index]);
    }
}
