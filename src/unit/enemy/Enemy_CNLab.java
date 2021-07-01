package unit.enemy;

import unit.ally.Ally;

import model.*;

public class Enemy_CNLab extends Enemy {
    private static int deaddelay = 5;
    private static int atkCycle = 3;
    private static int atkDelay = 4;
    public Enemy_CNLab (int posX, int posY, int lane, LevelWorld levelWorld ) {
        super("CNLab", 10, 1, posX, posY, lane, deaddelay, atkCycle, atkDelay, levelWorld, 20);
    }
    @Override
    protected void damage(Ally a) {
        super.damage(a);
        a.setAttackCycle(a.getAttackCycle()/2+1, 500);
    }
}
