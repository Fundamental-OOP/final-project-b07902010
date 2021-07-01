package unit.ally;
import model.*;
import graphics.*;

public class Ally_Saxophone extends Tank {
    static private int hp = 300;
    static private int cost = 100;
    static private int deaddelay = 20;
    public Ally_Saxophone(int posX, int posY, int lane, int column, LevelWorld levelWorld) {
        super("Saxophone", hp, posX, posY, lane, column, deaddelay, levelWorld, cost);
    }
    public static int getCost(){ return Ally_Saxophone.cost; }

}
