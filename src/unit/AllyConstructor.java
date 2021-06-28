package unit;

import model.LevelWorld;
import unit.ally.*;


public class AllyConstructor {
    LevelWorld world;
    public AllyConstructor(LevelWorld world){
        this.world = world;
    }
    public Ally constructAlly(String allyType, int lane, int column){     // and other attributes
        Ally newAlly = null;
        switch(allyType){
            case "MiMiMaoMao":
                newAlly = new Ally_MiMiMaoMao(300 + 99 * column, 160 + 120 * lane, lane, world);
                break;
            case "Pooper":
                newAlly = new Ally_Pooper(300 + 99 * column, 160 + 120 * lane, lane, world);
                break;
            case "Saxophone":
                newAlly = new Ally_Saxophone(300 + 99 * column, 160 + 120 * lane, lane, world);
                break;
            default:
                System.out.println("Ally type " + allyType + " not found.");
        }
        return newAlly;
    }
}