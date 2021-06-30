import battletype.BattleType;
import battletype.NormalBattleType;
import controller.GameFlow;
import level.LevelConstructor;
import model.LevelWorld;
import model.HomeWorld;
import model.World;
import view.*;
import record.Record;
import version.GameVersion;

public class Main {
    public static void main(String[] args) {
        BattleType[] battleTypes = {
            new NormalBattleType()
        };
        if(!GameVersion.loadVersion(battleTypes, "0.0.0")) {
            System.out.println("[Main] Error when loading version.");
            return;
        }
        if(!Record.loadLastRecord()){
            System.out.println("[Main] No last record, start a new one.");
            return;
        }

        World[] worlds = {
            new LevelWorld(new LevelConstructor(battleTypes)),
            new HomeWorld()
        };
        
        // initialize gameview
        GameView view = new GameView();

        // initialize canvas
        Canvas[] canvases = {
            new HomeCanvas(view,  (HomeWorld)worlds[1]),
            new LevelSelectionCanvas(view),
            new LevelCanvas(view, (LevelWorld)worlds[0])

        };

        for (Canvas canvas : canvases)
            view.addCanvas(canvas);

        GameFlow gameFlow = new GameFlow(worlds, view);
        gameFlow.launchGame();
        view.dispose();
    }   

}