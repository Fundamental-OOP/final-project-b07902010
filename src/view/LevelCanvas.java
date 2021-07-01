package view;

import javax.swing.*;
import java.awt.*;

import model.*;
import graphics.*;

import java.awt.event.*;
import java.util.List;

import javax.swing.event.*;

import battletype.BattleStatus;
import java.util.Map;
import java.util.HashMap;

import utils.*;
import selector.*;
import selector.Button;

import utils.*;

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
    
    // private AnimationRenderer lose_animation = new AnimationRenderer("../img/YouLose", "you_lose");
    // private AnimationRenderer win_animation = new AnimationRenderer("../img/YouWin", "you_win");

    private GameOverCanvas gameOverCanvas;

    public LevelCanvas (GameView view, LevelWorld world) {
        super(view, "Level", "../img/level/background.png");
        this.world = world;
        this.selector = world.getAllySelector();
        this.setSelector();
        this.setLayout(null);
        this.view = view;
        this.setBounds(0, 0, 1440, 900);
        this.addMouseListener(this);
        this.menu_canvas = new MenuCanvas(view, this);
        this.add(menu_canvas);
        this.menu_button = new MenuButton(this, menu_canvas);
        this.add(menu_button);
        this.gameOverCanvas = new GameOverCanvas(this);
        this.add(gameOverCanvas);
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
            g.drawImage(preview_image, p.x-50, p.y-60, null);
    }

    public void renderNextFrame () {
        if (this.world.checkBattleStatus() == BattleStatus.battleContinue) {
            this.gameOverCanvas.setVisible(false);
            this.menu_canvas.setVisible(false);
            repaint();
        }
        else if (this.world.checkBattleStatus() == BattleStatus.lose) {
            this.gameOverCanvas.play("lose");
        }
        else if (this.world.checkBattleStatus() == BattleStatus.win) {
            this.gameOverCanvas.play("win");
        }
    }

    public void renderBackground(Graphics g) {
        g.drawImage(background, 0, 0, null);
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
        int lane = (p.y-150)/120, column = (p.x-366)/99;
        createNewAlly(lane, column);
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
        // this.setEnabled(true);
        for (Component component: this.getComponents())
            component.setEnabled(true);
        this.selector.update();
        this.view.antiUnAnDeImPause();
        this.world.antiUnAnDeImPause();
    }

    public void disableCanvas () {
        // this.setEnabled(false);
        for (Component component: this.getComponents())
            component.setEnabled(false);
        this.world.pause();
        this.view.pause();
    }

    public void invisibleCanvas () {
        for (Component c: this.getComponents())
            c.setVisible(false);
    }

    
    public void visibleCanvas () {
        for (Component c: this.getComponents())
            c.setVisible(true);
        this.gameOverCanvas.setVisible(false);
        this.menu_canvas.setVisible(false);
    }

    public LevelWorld getWorld() {
        return this.world;
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
        Image icon_image = ImageReader.readImageFromPath("../img/level/menu_button.png");
        this.setIcon(new ImageIcon(icon_image));
        this.setBounds(1194, 12, icon_image.getWidth(null), icon_image.getHeight(null));
    }

    public void actionPerformed(ActionEvent e) {
        this.level_canvas.disableCanvas();
        this.menu_canvas.popUp();
    }

}





