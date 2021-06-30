package selector;

import javax.swing.*;

import model.LevelWorld;
import selector.Selector;

import java.awt.*;
import utils.*;

import java.awt.event.*;
import selector.Button;



class LevelSelector extends Selector {

    private Image default_icon_image = ImageReader.readImageFromPath("../img/level_1.png");

    private String[] level_names;
    

    public LevelSelector (int max_selections) {
        super(max_selections);
        level_names = new String[max_selections];
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
