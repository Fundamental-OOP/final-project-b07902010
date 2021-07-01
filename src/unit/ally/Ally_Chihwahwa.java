package unit.ally;

import model.LevelWorld;
import unit.enemy.Enemy;

public class Ally_Chihwahwa extends Shooter{
    static private int hp = 50;
    static private int atk = 30;
    static private int cost = 10;
    static private int deaddelay = 20;
    static private int shootcycle = 5;
    public Ally_Chihwahwa(int posX, int posY, int lane, int column, LevelWorld levelWorld){
        super("Chihwahwa", hp, atk, posX, posY, lane, column, deaddelay, levelWorld, cost, shootcycle);
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
    public static int getCost(){ return Ally_Chihwahwa.cost; }
}
