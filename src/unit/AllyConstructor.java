package unit;

import model.LevelWorld;

public class AllyConstructor {
    LevelWorld world;
    public AllyConstructor(LevelWorld world){
        this.world = world;
    }
    public Ally constructAlly(String allyType){     // and other attributes
        Ally newAlly = null;
        switch(allyType){
            case "MiMiMaoMao":
                newAlly = new Ally_MiMiMaoMao();
                break;
            case "MimiMaoMao":
                break;
            default:
                System.out.println("Ally type " + allyType + " not found.");
        }
        return newAlly;
    }
}
