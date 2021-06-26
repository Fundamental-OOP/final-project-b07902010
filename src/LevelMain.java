import controller.GameLoop;
import level.LevelConstructor;
import model.LevelWorld;
import level.Level;

import java.awt.*;
import java.io.File;

import battletype.BattleStatusChecker.*;


// import static media.AudioPlayer.addAudioByFilePath;

public class LevelMain {
    public static void main(String[] args) {
        // addAudioByFilePath(Walking.AUDIO_STEP1, new File("assets/audio/step1.wav"));
        // addAudioByFilePath(Walking.AUDIO_STEP2, new File("assets/audio/step2.wav"));
        // addAudioByFilePath(Attacking.AUDIO_SWORD_CLASH_1, new File("assets/audio/sword-clash1.wav"));
        // addAudioByFilePath(Attacking.AUDIO_SWORD_CLASH_2, new File("assets/audio/sword-clash2.wav"));
        // addAudioByFilePath(HealthPointSprite.AUDIO_DIE, new File("assets/audio/die.wav"));

        // Initialization
        BattleStatusChecker[] battleTypes = {
            new NormalBattleStatusChecker()
        };
        LevelConstructor levelConstructor = new LevelConstructor(battleTypes);

        // Start a level
        LevelWorld world = new LevelWorld();
        String levelName = "";
        Level level = levelConstructor.constructLevel(levelName, world);
        GameLoop game = new GameLoop(world);  // controller
        GameView view = new GameView(game);  // GUI
        game.start();  // run the game and the game loop
        view.launch(); // launch the GUI
    }
}