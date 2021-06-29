package controller;

import model.World;
import view.*;

public class GameFlow {
    private final GameView view;
    private final GameLoop gameLoop;
    World[] worlds;
    World currentWorld;
    String currentWorldType;

    public GameFlow(World[] worlds, GameView view){
        this.view = view;
        this.worlds = worlds;
        this.gameLoop = new GameLoop(view);
        currentWorldType = "Home";
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
            System.out.println(world.getMyWorldType());
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
