package controller;

import model.World;

import game.*;

public class GameFlow {
    private final GameView view = new GameView(1440, 900);
    private final GameLoop gameLoop = new GameLoop(view, this);
    World[] worlds;
    World currentWorld;
    String currentWorldType;

    public GameFlow(World[] worlds){
        this.worlds = worlds;
        currentWorldType = "MainMenu";

    }

    public void launchGame(){
        while(true){
            setCurrentWorld();
            if( currentWorld == null ) { break; }
            gotoWorld();
            getNextWorld();
        }
    }
    private void setCurrentWorld(){
        for(World world : worlds){
            if(world.getMyWorldType().equals(currentWorldType)){
                currentWorld = world;
                currentWorld.resetWorld();
                return;
            }
        }
        if(!currentWorldType.equals("None")){
            System.out.println("World " + currentWorldType + " not found. Exit game.");
        }
        System.out.println("Exit game, last world: " + currentWorld.getMyWorldType() );
        currentWorld = null;
        return;
    }
    private void gotoWorld(){
        gameLoop.setWorld(currentWorld);
        gameLoop.start();
    }
    private void getNextWorld(){
        currentWorldType = currentWorld.getNextWorldType();
    }
}
