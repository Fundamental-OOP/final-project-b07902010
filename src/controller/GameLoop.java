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
            // World world = getWorld();
            // view.setWorld(world);
            running = world.update();
            view.update();
            delay(30);
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


//     public void moveKnight(int playerNumber, Direction direction) {
//         getPlayer(playerNumber).move(direction);
//     }

//     public void stopKnight(int playerNumber, Direction direction) {
//         getPlayer(playerNumber).stop(direction);
//     }

//     public void attack(int playerNumber) {
//         getPlayer(playerNumber).attack();
//     }

//     public Knight getPlayer(int playerNumber) {
//         return playerNumber == 1 ? p1 : p2;
//     }
