package unit.ally;

import model.LevelWorld;

public class Ally_Doge extends Tank{
    static private int hp = 400;
    static private int cost = 200;
    static private int deaddelay = 20;
    public Ally_Doge(int posX, int posY, int lane, int column, LevelWorld levelWorld){
        super("Doge", hp, posX, posY, lane, column, deaddelay, levelWorld, cost);
    }
    public static int getCost(){ return Ally_Doge.cost; }
}
