package unit.enemy;


import model.*;
import utils.UnitImage;

public class Enemy_FOOP extends Enemy {
    private static int deaddelay = UnitImage.getUnitAnimation("FOOP", "Dead").size();;
    private static int atkCycle = 5;
    private static int atkDelay = UnitImage.getUnitAnimation("FOOP", "Attack").size();
    public Enemy_FOOP (int posX, int posY, int lane, LevelWorld levelWorld ) {
        super("FOOP", 100, 30, posX, posY, lane, deaddelay, atkCycle, atkDelay, levelWorld, 2);
    }
}
