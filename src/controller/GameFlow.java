package controller;

import battletype.BattleStatusChecker;
import level.LevelConstructor;
import model.World;

public class GameFlow {
    private final GameLoop gameLoop = new GameLoop();
    // private LevelConstructor levelConstructor;
    // BattleStatusChecker[] battleTypes;
    World[] worlds;
    World currentWorld;
    String currentWorldType;
    public GameFlow(World[] worlds){
        this.worlds = worlds;
        currentWorldType = "MainMenu";
        // this.battleTypes = battleTypes;
        // levelConstructor = new LevelConstructor(battleTypes);
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
