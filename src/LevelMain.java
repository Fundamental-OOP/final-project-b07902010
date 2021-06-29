import controller.GameLoop;
import game.GameView;
import level.LevelConstructor;
import model.LevelWorld;
import battletype.BattleType;
import battletype.NormalBattleType;

// import static media.AudioPlayer.addAudioByFilePath;

public class LevelMain {
    public static void main(String[] args) {
        // Initialization
        BattleType[] battleTypes = {
            new NormalBattleType()
        };
        LevelConstructor levelConstructor = new LevelConstructor(battleTypes);

        // Start a level
        LevelWorld world = new LevelWorld(levelConstructor);
        // String levelName = "level_test";
        // Level level = levelConstructor.constructLevel(levelName, world);
        // world.setLevel(level);
        world.resetWorld();
        
        GameView view = new GameView(1440, 900);
        GameLoop gameLoop = new GameLoop(view);  // controller
        gameLoop.setWorld(world);
        gameLoop.start();  // run the game and the game loop
    }
}