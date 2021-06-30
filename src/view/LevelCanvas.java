package view;

import javax.swing.*;
import java.awt.*;

import model.*;
import graphics.*;

import java.awt.event.*;
import java.util.List;

import javax.swing.event.*;

import utils.*;
import selector.*;
import selector.Button;

/** Draw some baseline */
/** Gamecanvas */

public class LevelCanvas extends Canvas implements MouseInputListener {
    
    final private AllySelector selector;
    
    private LevelWorld world;
    private boolean mouse_enabled = true;
    private GameView view;
    
    private JButton menu_button;    
    private MenuCanvas menu_canvas;
    private List<Renderee> renderees;
    

    public LevelCanvas (GameView view, LevelWorld world) {
        super(view, "Level", "../img/background.png");
        this.world = world;
        this.selector = world.getAllySelector();
        this.setSelector();
        this.setLayout(null);
        this.view = view;
        this.setBounds(0, 0, 1440, 900);
        this.addMouseListener(this);
        this.menu_canvas = new MenuCanvas(view, world);
        this.add(menu_canvas);
        this.menu_button = new MenuButton(this, menu_canvas);
        this.add(menu_button);
    }


    public void paintComponent(Graphics g) {   
        renderBackground(g);
        if (world.getRenderees().size() > 0) 
            for (Renderee renderee :  this.world.getRenderees())
                renderee.getRenderer().render(g);
        renderPreview(g);
    }


    public void renderPreview(Graphics g) {
        Point p = MouseInfo.getPointerInfo().getLocation();
        Image preview_image = this.selector.getCurrentSelectionPreview();
        if (preview_image != null);
            g.drawImage(preview_image, p.x-50, p.y-100, null);
    }

    public void renderNextFrame () {
        if (!menu_canvas.isPopUp()) repaint();   
    }

    public void renderBackground(Graphics g) {
        g.drawImage(background, 0, 0, null);
        g.setColor(Color.BLACK);
        for (int i = 0; i < 9; i ++) {
            for (int j =  0; j < 5; j++) {
                g.drawRect(300+i*100, 150+j*120, 100, 120);
            }
        }
    }

    public void setWorld(LevelWorld world) {
        this.world = world;
    }
 

    public void setSelector() {
        for (Button button: this.selector.getButtons()) {
            this.add(button);
        }
    }

    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();
        int lane = (p.y-150)/120, column = (p.x-300)/99;
        if (mouse_enabled) createNewAlly(lane, column);
    }
    
    public void createNewAlly(int lane, int column) {
        String current_selection = this.selector.getCurrentSelectionType();
        if (lane < 0 || lane >= 5 || column >= 9 || column < 0)
            System.out.println("[LevelCanvas] Invalid position");
        else if (current_selection == null) 
            System.out.println("[LevelCanvas] Current selection is null");
        else {
            world.addAlly(current_selection, lane, column);
            this.selector.resetSelection();
        }
    }

    public void enableCanvas () {


    }

    public void disableCanvas () {

        
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}

}



class MenuButton extends JButton implements ActionListener {

    // boolean selected = false;
    GameView view;
    LevelCanvas level_canvas;
    MenuCanvas menu_canvas;


    public MenuButton(LevelCanvas level_canvas, MenuCanvas menu_canvas) {
        this.level_canvas = level_canvas;
        this.menu_canvas = menu_canvas;
        this.setLayout(null);
        this.addActionListener(this);
        Image icon_image = ImageReader.readImageFromPath("../img/menu_button.png");
        this.setIcon(new ImageIcon(icon_image));
        this.setBounds(1220, 20, icon_image.getWidth(null), icon_image.getHeight(null));
    }

    public void actionPerformed(ActionEvent e) {
        this.menu_canvas.popUp();
        this.level_canvas.setEnabled(false);
        for (Component component: level_canvas.getComponents())
            component.setEnabled(false);
    }
}