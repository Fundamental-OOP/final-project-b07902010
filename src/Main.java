import battletype.BattleStatusChecker;
import battletype.NormalBattleStatusChecker;
import controller.GameFlow;
import level.LevelConstructor;
import model.LevelWorld;
import model.MainMenuWorld;
import model.World;
// import record.RecordIO;

public class Main {
    public static void main(String[] args) {
        BattleStatusChecker[] battleTypes = {
            new NormalBattleStatusChecker()
        };
        
        // RecordIO recordIO = new RecordIO(battleTypes);
        World[] worlds = {
            new LevelWorld(new LevelConstructor(battleTypes)),
            new MainMenuWorld()
        };
        // initialize canvas

        GameFlow gameFlow = new GameFlow(worlds);
        gameFlow.launchGame();
    }   

}