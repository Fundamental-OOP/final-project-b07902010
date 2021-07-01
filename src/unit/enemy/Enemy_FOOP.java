package unit.enemy;

import unit.State;
import graphics.*;

import model.*;

public class Enemy_FOOP extends Enemy {
    private static int deaddelay = 10;
    private static int atkCycle = 5;
    public Enemy_FOOP (int posX, int posY, int lane, LevelWorld levelWorld ) {
        super("FOOP", 100, 10, posX, posY, lane, deaddelay, atkCycle, levelWorld, 2);
    }
}
