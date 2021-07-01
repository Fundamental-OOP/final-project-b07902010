package unit.enemy;

import unit.State;
import graphics.*;

import model.*;

public class Enemy_ADA extends Enemy {
    private static int deaddelay = 5;
    private static int atkCycle = 50;
    public Enemy_ADA (int posX, int posY, int lane, LevelWorld levelWorld ) {
        super("ADA", 300, 10, posX, posY, lane, deaddelay, atkCycle, levelWorld, 1);
    }

}
