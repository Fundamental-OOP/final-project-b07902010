import controller.GameLoop;
import level.LevelConstructor;
import model.LevelWorld;
import view.*;
import level.Level;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import view.LevelCanvas;
import view.HomeCanvas;

import battletype.*;

public class LevelMain {
    public static void main(String[] args) {
        // Initialization
        BattleType[] battleTypes = {
            new NormalBattleType()
        };
        LevelConstructor levelConstructor = new LevelConstructor(battleTypes);
        
        // Start a level
        LevelWorld world = new LevelWorld(levelConstructor);
        
        GameView view = new GameView(1440, 900);
        HomeCanvas maincanvas = new HomeCanvas(view);
        LevelCanvas levelcanvas = new LevelCanvas(view);
        // Container container = view.getContentPane();
        // container.add(levelcanvas);
        //container.add(maincanvas);

        // view.setContentPane(maincanvas);

        world.resetWorld();
        levelcanvas.setWorld(world);
        // view.addCanvas("Menu", menucanvas);
        view.addCanvas("Main", maincanvas);
        view.addCanvas("Level", levelcanvas);
        
        
        // view.setCanvas("Level");
        view.setWorld(world);
        view.setCanvas("Main");

        
        
        // String levelName = "level_test";
        // Level level = levelConstructor.constructLevel(levelName, world);
        // world.setLevel(level);
        
        
        GameLoop gameLoop = new GameLoop(view);  // controller
        gameLoop.setWorld(world);
        gameLoop.start();  // run the game and the game loop
    }
}