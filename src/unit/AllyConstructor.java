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
        int posX = 367 + 90 * column;
        int posY = 160 + 120 * lane;
        switch(allyType){
            case "MiMiMaoMao":
                newAlly = new Ally_MiMiMaoMao(posX, posY, lane, column, world);
                break;
            case "Pooper":
                newAlly = new Ally_Pooper(posX, posY, lane, column, world);
                break;
            case "Saxophone":
                newAlly = new Ally_Saxophone(posX, posY, lane, column, world);
                break;
            case "Oppa":
                newAlly = new Ally_Oppa(posX, posY, lane, column, world);
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
            case "Oppa":
                cost = Ally_Oppa.getCost();
                break;
            default:
                System.out.println("Ally type " + allyType + " not found.");
        }
        return cost;
    }
}
