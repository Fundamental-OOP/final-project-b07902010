package unit.enemy;

import unit.State;
import graphics.*;

import model.*;

public class Enemy_FOOP extends Enemy {
    private static int deadDelay = 10;
    public Enemy_FOOP (int posX, int posY, int lane, LevelWorld levelWorld ) {
        super("FOOP", 100, 10, posX, posY, lane, deadDelay, levelWorld, 2);
    }
}
