package unit.enemy;


import model.*;
import utils.UnitImage;

public class Enemy_FOOP extends Enemy {
    private static int deaddelay = UnitImage.getUnitAnimation("FOOP", "dead").size();;
    private static int atkCycle = 5;
    private static int atkDelay = UnitImage.getUnitAnimation("FOOP", "attack").size();
    public Enemy_FOOP (int posX, int posY, int lane, LevelWorld levelWorld ) {
        super("FOOP", 100, 10, posX, posY, lane, deaddelay, atkCycle, atkDelay, levelWorld, 2);
    }
}
