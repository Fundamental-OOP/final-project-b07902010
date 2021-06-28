package unit;

import model.LevelWorld;
import unit.ally.Ally;


public class AllyConstructor {
    LevelWorld world;
    public AllyConstructor(LevelWorld world){
        this.world = world;
    }
    public Ally constructAlly(String allyType){     // and other attributes
        Ally newAlly = null;
        switch(allyType){
            case "MiMiMaoMao":
                newAlly = new Ally_MiMiMaoMao(300, 300, 1, world);
                break;
            case "MimiMaoMao":
                break;
            default:
                System.out.println("Ally type " + allyType + " not found.");
        }
        return newAlly;
    }
}
