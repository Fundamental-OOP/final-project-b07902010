import battletype.BattleType;
import battletype.NormalBattleType;
import controller.GameFlow;
import level.LevelConstructor;
import model.LevelWorld;
import model.HomeWorld;
import model.World;
import view.*;
// import record.RecordIO;

public class Main {
    public static void main(String[] args) {
        BattleType[] battleTypes = {
            new NormalBattleType()
        };
        
        // RecordIO recordIO = new RecordIO(battleTypes);
        World[] worlds = {
            new LevelWorld(new LevelConstructor(battleTypes)),
            new HomeWorld()
        };
        
        // initialize gameview
        GameView view = new GameView();

        // initialize canvas
        Canvas[] canvases = {
            new HomeCanvas(view,  (HomeWorld)worlds[1]),
            new LevelCanvas(view, (LevelWorld)worlds[0])
        };

        for (Canvas canvas : canvases)
            view.addCanvas(canvas);

        GameFlow gameFlow = new GameFlow(worlds, view);
        gameFlow.launchGame();
    }   

}