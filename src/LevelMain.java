import controller.GameLoop;
import game.GameView;
import level.LevelConstructor;
import model.LevelWorld;
import level.Level;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import battletype.BattleStatusChecker;
import battletype.NormalBattleStatusChecker;

import game.*;
// import static media.AudioPlayer.addAudioByFilePath;
import game.Canvas;

public class LevelMain {
    public static void main(String[] args) {
        // Initialization
        BattleStatusChecker[] battleTypes = {
            new NormalBattleStatusChecker()
        };
        LevelConstructor levelConstructor = new LevelConstructor(battleTypes);
        
        // Start a level
        LevelWorld world = new LevelWorld(levelConstructor);
        
        GameView view = new GameView(1440, 900);
        Canvas levelcanvas = new Canvas(view);
        
        world.resetWorld();
        
        levelcanvas.setWorld(world);
        view.setCanvas(levelcanvas);
        view.setWorld(world);
        
        // String levelName = "level_test";
        // Level level = levelConstructor.constructLevel(levelName, world);
        // world.setLevel(level);
        
        
        GameLoop gameLoop = new GameLoop(view);  // controller
        gameLoop.setWorld(world);
        gameLoop.start();  // run the game and the game loop
    }
}