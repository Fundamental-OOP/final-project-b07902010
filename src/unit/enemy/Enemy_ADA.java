package unit.enemy;

import unit.State;
import graphics.*;

import model.*;

public class Enemy_ADA extends Enemy {
    private static int deadDelay;
    public Enemy_ADA (int posX, int posY, int lane, LevelWorld levelWorld ) {
        super("ADA", 300, 10, posX, posY, lane, deadDelay, levelWorld, 1);
    }

}
