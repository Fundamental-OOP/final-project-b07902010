package unit.ally;

import model.LevelWorld;
import unit.enemy.Enemy;

public class Ally_RainbowCat extends Shooter{
    static private int hp = 100;
    static private int atk = 100;
    static private int cost = 100;
    static private int deaddelay = 20;
    static private int shootcycle = 10;
    public Ally_RainbowCat(int posX, int posY, int lane, int column, LevelWorld levelWorld){
        super("RainbowCat", hp, atk, posX, posY, lane, column, deaddelay, levelWorld, cost, shootcycle);
        
    }
    @Override
    public boolean canSee(Enemy e) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    void shoot() {
        // TODO Auto-generated method stub
        
    }
    public static int getCost(){ return Ally_RainbowCat.cost; }
}
