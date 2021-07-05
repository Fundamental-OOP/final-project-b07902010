package unit.ally;
import model.*;
import utils.UnitImage;

public class Ally_Saxophone extends Tank {
    static private int hp = 500;
    static private int cost = 50;
    static private int deaddelay = UnitImage.getUnitAnimation("Saxophone", "dead").size();
    public Ally_Saxophone(int posX, int posY, int lane, int column, LevelWorld levelWorld) {
        super("Saxophone", hp, posX, posY, lane, column, deaddelay, levelWorld, cost);
    }
    public static int getCost(){ return Ally_Saxophone.cost; }

}
