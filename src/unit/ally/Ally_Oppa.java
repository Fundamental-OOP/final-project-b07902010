package unit.ally;

import java.util.List;

import model.LevelWorld;
import unit.enemy.Enemy;

public class Ally_Oppa extends Bomb{
    static private int hp = 10;
    static private int atk = 300;
    private static int deaddelay = 20;
    private static int cost;
    private static int explosiondelay = 10;
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
                enemy.setHP(enemy.getHP() - ATK);
            }
        }
    }
    public static int getCost(){ return cost; }
}
