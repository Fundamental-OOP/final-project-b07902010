package view;


import javax.swing.*;

import java.awt.Container;
import model.*;
import java.util.HashMap;
import java.awt.Toolkit;
import utils.*;

public class GameView extends JFrame {
    
    private World world;
    private HashMap<String, Canvas> canvases = new HashMap<String, Canvas>();
    public Canvas canvas;
    private Container container;
    private boolean isPaused;

    int width = 1440, height = 900;

    public GameView ()  {
        // this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        // this.setUndecorated(true);

        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.container = this.getContentPane();
        this.container.setLayout(null);
    }

    public void setWorld(World world) {
        System.out.println(world.getMyWorldType());
        this.world = world;
        this.setCanvas(world.getMyWorldType());
    }

    /** 新增一個 canvas 到 gameview */
    public void addCanvas(Canvas canvas) {
        canvases.put(canvas.getType(), canvas);
    }

    /** 設定 gameview 當前畫布 */
    public void setCanvas(String canvas_name) {
        //if (this.canvas != null)
        //    this.canvas.setVisible(false);
        this.canvas = canvases.get(canvas_name);
        this.setContentPane(this.canvas);
        this.canvas.setVisible(true);
        if (canvas_name.equals("Level") && this.canvas instanceof LevelCanvas) {
            ((LevelCanvas) this.canvas).visibleCanvas();
            ((LevelCanvas) this.canvas).enableCanvas();
            
        }
            
    }
    
    /** 更新 gameview 當前畫布 */
    public void update () {
        if (!isPaused)
            this.canvas.renderNextFrame();
    }
    
    public World getWorld() {
        return this.world;
    }

    /** 取得 gameview 當前畫布 */
    public Canvas getCanvas() {
        return this.canvas;
    }

    public void pause() {
        this.isPaused = true;
    }

    public void antiUnAnDeImPause() {
        this.isPaused = false;
    }

}