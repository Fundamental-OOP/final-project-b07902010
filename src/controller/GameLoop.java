package controller;

import model.World;

public abstract class GameLoop {
    private boolean running;
    private World world;

    public GameLoop(World world){
        this.world = world;
        running = true;
    }

    public void start() {
        new Thread(this::gameLoop).start();
    }

    private void gameLoop() {
        running = true;
        while (running) {
            World world = getWorld();
            running = world.update();
            delay(15);
        }
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
