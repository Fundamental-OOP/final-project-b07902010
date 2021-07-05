package unit.enemy;

import utils.UnitImage;

import model.*;

public class Enemy_ADA extends Enemy {
    private static int deaddelay = UnitImage.getUnitAnimation("ADA", "dead").size();
    private static int atkCycle = 100;
    private static int atkDelay = UnitImage.getUnitAnimation("ADA", "attack").size();
    public Enemy_ADA (int posX, int posY, int lane, LevelWorld levelWorld ) {
        super("ADA", 300, 999, posX, posY, lane, deaddelay, atkCycle, atkDelay, levelWorld, 1);
    }

}
