package controller;

import model.World;
import view.*;

public class GameLoop {
    private boolean running;
    private World world;
    private final GameView view;
    
    public GameLoop(GameView view){
        this.view = view;
    }

    public void setWorld(World world){
        this.world = world;
        this.view.setWorld(world);
        running = true;
    }
    public void start() {
        // new Thread(this::gameLoop).start();
        gameLoop();
    }

    private void gameLoop() {
        running = true;
        while (running) {
            running = world.update();
            view.update();
            delay(60);
        }
        System.out.println("stopped");
    }

    protected World getWorld(){
        return world;
    }

    public void stop() {
        running = false;
    }

    private void delay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}