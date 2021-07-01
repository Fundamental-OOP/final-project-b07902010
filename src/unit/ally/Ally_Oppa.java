package unit.ally;

import java.util.List;

import model.LevelWorld;
import unit.enemy.Enemy;
import utils.UnitImage;

public class Ally_Oppa extends Bomb{
    static private int hp = 10;
    static private int atk = 999;
    private static int deaddelay = UnitImage.getUnitAnimation("Oppa", "dead").size();
    private static int cost = 150;
    private static int explosiondelay = deaddelay ;
    public Ally_Oppa(int posX, int posY, int lane, int column, LevelWorld levelWorld){
        super("Oppa", hp, atk, posX, posY, lane, column, deaddelay, levelWorld, cost, explosiondelay);
    }
    @Override
    protected boolean canSee(Enemy e) {
        if(Math.abs(e.getLane() - lane) <= 1 && Math.abs(e.getPosX() - posX) <= 60){
            return true;
        }
        return false;
    }
    @Override
    protected boolean isTimeToExplode(List< Enemy > enemies) {
        for(Enemy enemy : enemies){
            if(canSee(enemy)){ return true; }
        }
        return false;
    }
    @Override
    protected void BOOM(List< Enemy > enemies) {
        for(Enemy enemy : enemies){
            if(canSee(enemy)){
                enemy.damaged(this, mutableATK);
            }
        }
    }
    public static int getCost(){ return cost; }
}
