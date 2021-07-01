package unit.enemy;

import unit.State;
import graphics.*;

import model.*;

public class Enemy_CNLab extends Enemy {
    private static int deadDelay = 10;
    public Enemy_CNLab (int posX, int posY, int lane, LevelWorld levelWorld ) {
        super("CNLab", 10, 1, posX, posY, lane, deadDelay, levelWorld, 20);
    }

}
