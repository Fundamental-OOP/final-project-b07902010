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


public class GameOverCanvas extends JPanel implements MouseInputListener {
    private Map<String, AnimationRenderer> animation = new HashMap<>();
    private String status;
    private boolean isPlayed = false;
    private LevelCanvas levelCanvas;

    public GameOverCanvas (LevelCanvas levelCanvas) {
        this.levelCanvas = levelCanvas;
        this.addMouseListener(this);
        this.setBounds(0, 0, 1440, 900);
        this.setOpaque(false);
        this.setVisible(false);
        this.animation.put("win", new AnimationRenderer("../img/YouWin", "you_win"));
        this.animation.put("lose", new AnimationRenderer("../img/YouLose", "you_lose"));
    }
    
    public void paintComponent(Graphics g) {
        animation.get(this.status).render(g);
    }
    
    public void play (String status) {
        if (!isPlayed) {
            this.status = status;
            this.levelCanvas.invisibleCanvas();
            this.setVisible(true);
            this.isPlayed = true;
        }
        while (isPlayed) {
            try {
                Thread.sleep(100);
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        this.levelCanvas.getWorld().setNextWorld("Home");
        this.isPlayed = false;
    }
}