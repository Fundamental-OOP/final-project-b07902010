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
            case "RainbowCat":
                newAlly = new Ally_RainbowCat(posX, posY, lane, column, world);
                break;
            case "Doge":
                newAlly = new Ally_Doge(posX, posY, lane, column, world);
                break;
            case "Chihwahwa":
                newAlly = new Ally_Chihuahua(posX, posY, lane, column, world);
                break;
            case "Pingu":
                newAlly = new Ally_Pingu(posX, posY, lane, column, world);
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
            case "RainbowCat":
                cost = Ally_RainbowCat.getCost();
                break;
            case "Doge":
                cost = Ally_Doge.getCost();
                break;
            case "Chihwahwa":
                cost = Ally_Chihuahua.getCost();
                break;
            case "Pingu":
                cost = Ally_Pingu.getCost();
                break;
            default:
                System.out.println("Ally type " + allyType + " not found.");
        }
        return cost;
    }
}
