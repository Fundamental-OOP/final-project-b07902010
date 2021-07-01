package unit.enemy;


import model.*;

public class Enemy_FOOP extends Enemy {
    private static int deaddelay = 10;
    private static int atkCycle = 5;
    private static int atkDelay = 4;
    public Enemy_FOOP (int posX, int posY, int lane, LevelWorld levelWorld ) {
        super("FOOP", 100, 10, posX, posY, lane, deaddelay, atkCycle, atkDelay, levelWorld, 2);
    }
}
