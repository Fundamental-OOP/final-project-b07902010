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
                newAlly = new Ally_MiMiMaoMao(300 + 99 * column, 160 + 120 * lane, lane, column, world);
                break;
            case "Pooper":
                newAlly = new Ally_Pooper(300 + 99 * column, 160 + 120 * lane, lane, column, world);
                break;
            case "Saxophone":
                newAlly = new Ally_Saxophone(300 + 99 * column, 160 + 120 * lane, lane, column, world);
                break;
            default:
                System.out.println("[AllyConstructor] Ally type " + allyType + " not found.");
        }
        return newAlly;
    }
    static public int getNeededPoop(String allyType) {
        int cost = 0;
        switch(allyType){
            case "MiMiMaoMao":
                cost = Ally_MiMiMaoMao.getCost();
                break;
            case "Pooper":
                cost = Ally_Pooper.getCost();
                break;
            case "Saxophone":
                cost = Ally_Saxophone.getCost();               
                break;
            default:
                System.out.println("Ally type " + allyType + " not found.");
        }
        return cost;
    }
}
