import controller.GameLoop;
import level.LevelConstructor;
import model.LevelWorld;
import level.Level;

import java.awt.*;
import java.io.File;

import battletype.BattleStatusChecker;
import battletype.NormalBattleStatusChecker;

// import static media.AudioPlayer.addAudioByFilePath;

public class LevelMain {
    public static void main(String[] args) {
        // Initialization
        BattleStatusChecker[] battleTypes = {
            new NormalBattleStatusChecker()
        };
        LevelConstructor levelConstructor = new LevelConstructor(battleTypes);

        // Start a level
        LevelWorld world = new LevelWorld();
        String levelName = "";
        Level level = levelConstructor.constructLevel(levelName, world);
        GameLoop gameLoop = new GameLoop();  // controller
        gameLoop.setWorld(world);
        gameLoop.start();  // run the game and the game loop
    }
}