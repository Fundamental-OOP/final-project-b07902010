import battletype.BattleType;
import battletype.NormalBattleType;
import controller.GameFlow;
import level.LevelConstructor;
import model.LevelWorld;
import model.MainMenuWorld;
import model.World;
import record.RecordIO;

public class Main {
    public static void main(String[] args) {
        BattleType[] battleTypes = {
            new NormalBattleType()
        };
        
        RecordIO recordIO = new RecordIO(battleTypes);
        World[] worlds = {
            new LevelWorld(new LevelConstructor(battleTypes)),
            new MainMenuWorld()
        };
        // initialize canvas
        GameFlow gameFlow = new GameFlow(worlds);
        gameFlow.launchGame();
    }   

}