import battletype.BattleType;
import battletype.NormalBattleType;
import controller.GameFlow;
import level.LevelConstructor;
import model.LevelWorld;
import model.HomeWorld;
import model.LevelSelectionWorld;
import model.World;
import view.*;
import record.Record;
import utils.UnitImage;
import version.GameVersion;

public class Main {
    public static void main(String[] args) {

    
        UnitImage unitImage = new UnitImage();

        BattleType[] battleTypes = {
            new NormalBattleType()
        };
        if(!GameVersion.loadVersion(battleTypes, "test")) {
            System.out.println("[Main] Error when loading version.");
            return;
        }
        if(!Record.loadLastRecord()){
            System.out.println("[Main] No last record, start a new one.");
            return;
        }

        World[] worlds = {
            new LevelWorld(new LevelConstructor(battleTypes)),
            new HomeWorld(),
            new LevelSelectionWorld()
        };
        
        // initialize gameview
        GameView view = new GameView();

        // initialize canvas
        Canvas[] canvases = {
            new HomeCanvas(view,  (HomeWorld)worlds[1]),
            new LevelCanvas(view, (LevelWorld)worlds[0]),
            new LevelSelectionCanvas(view, (LevelSelectionWorld)worlds[2])
        };

        for (Canvas canvas : canvases)
            view.addCanvas(canvas);

        GameFlow gameFlow = new GameFlow(worlds, view);
        gameFlow.launchGame();
        view.dispose();
    }   

}