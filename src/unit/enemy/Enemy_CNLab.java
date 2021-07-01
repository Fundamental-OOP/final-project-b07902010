package unit.enemy;

import unit.ally.Ally;
import utils.UnitImage;
import model.*;

public class Enemy_CNLab extends Enemy {
    private static int deaddelay = UnitImage.getUnitAnimation("CNLab", "Dead").size();;
    private static int atkCycle = 3;
    private static int atkDelay = UnitImage.getUnitAnimation("CNLab", "Attack").size();
    public Enemy_CNLab (int posX, int posY, int lane, LevelWorld levelWorld ) {
        super("CNLab", 50, 5, posX, posY, lane, deaddelay, atkCycle, atkDelay, levelWorld, 20);
    }
    @Override
    protected void damage(Ally a) {
        super.damage(a);
        a.setAttackCycle(a.getAttackCycle()/2+1, 500);
    }
}
