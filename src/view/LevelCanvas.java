package view;

import javax.swing.*;
import java.awt.*;

import model.*;
import graphics.*;

import java.awt.event.*;
import javax.swing.event.*;

import utils.*;
import selector.*;
import selector.Button;

/** Draw some baseline */
/** Gamecanvas */

public class LevelCanvas extends Canvas implements MouseInputListener {
    
    final private Selector selector;
    
    private LevelWorld world;
    private GameView view;
    private static Point lastClickedPosition;
    
    private JButton menu_button;    
    private MenuCanvas menu_canvas;
    

    public LevelCanvas (GameView view, LevelWorld world) {
        super(view, "Level", "../img/background.png");
        this.world = world;
        this.selector = world.getSelector();
        this.setSelector();
        this.setLayout(null);
        this.view = view;
        this.setBounds(0, 0, 1440, 900);
        this.addMouseListener(this);
        this.menu_canvas = new MenuCanvas(view, world);
        this.add(menu_canvas);
        this.menu_button = new MenuButton(menu_canvas);
        this.add(menu_button);
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
        if (preview_image != null);
            g.drawImage(preview_image, p.x-50, p.y-100, null);
    }

    public void renderNextFrame () {
        if (menu_canvas.isPopUp()) {
            while (menu_canvas.isPopUp())
                menu_canvas.renderNextFrame();
        }
        else
            repaint();   
    }

    public void renderBackground(Graphics g) {
        g.drawImage(background, 0, 0, null);
        // g.setColor(Color.GRAY);
        // g.fillRect(300, 150, 900, 600);
        g.setColor(Color.BLACK);
        for (int i = 0; i < 9; i ++) {
            for (int j =  0; j < 5; j++) {
                g.drawRect(300+i*100, 150+j*120, 100, 120);
            }
        }
        // g.setColor(Color.PINK);
        // g.fillRect(20, 150, 260, 600);
        // g.setColor(Color.BLACK);
        // g.fillRect(300, 20, 900, 120);
    }

    public static Point getLastClickedPosition() {
        return lastClickedPosition;
    }

    public void setWorld(LevelWorld world) {
        this.world = world;
    }
 

    public void setSelector() {
        for (Button button: this.selector.getButtons())
            this.add(button);
    }

    public void mouseClicked(MouseEvent e) {
        Point p = MouseInfo.getPointerInfo().getLocation();
        System.out.println("Clicked! " + p.x + " " + p.y);
        String current_selection = this.selector.getCurrentSelection();
        if (current_selection != null) {
            world.addAlly(current_selection, 1, 2);
            this.selector.cleanSelection();
        }
            
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


class MenuButton extends JButton implements ActionListener {

    // boolean selected = false;
    GameView view;
    MenuCanvas menu_canvas;

    public MenuButton(MenuCanvas menu_canvas) {
        this.menu_canvas = menu_canvas;
        this.setLayout(null);
        this.addActionListener(this);
        Image icon_image = ImageReader.readImageFromPath("../img/menu_button.png");
        this.setIcon(new ImageIcon(icon_image));
        this.setBounds(1220, 20, icon_image.getWidth(null), icon_image.getHeight(null));
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Menu!!!");
        //view.setCanvas("Menu");
        this.menu_canvas.popUp();
    }
}