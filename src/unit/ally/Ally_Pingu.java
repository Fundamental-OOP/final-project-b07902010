package unit.ally;

import java.util.List;

import model.LevelWorld;
import unit.enemy.Enemy;

public class Ally_Pingu extends Warrior{
    private static int hp;
    private static int atk;
    private static int cost = 100;
    private static int deaddelay = 10;
    private static int attackcycle = 10;
    private static int atkDelay = 8;
    public Ally_Pingu(int posX, int posY, int lane, int column, LevelWorld levelWorld){
        super("Pingu", hp, atk, posX, posY, lane, column, deaddelay, attackcycle, atkDelay, levelWorld, cost);
    }
    @Override
    protected void HIT(List<Enemy> enemies) {
        for(Enemy enemy : enemies){
            if(canSee(enemy)){
                enemy.setHP(enemy.getHP() - mutableATK);
                break;
            }
        }
    }
    @Override
    protected boolean canSee(Enemy e) {
        if(e.getLane() == lane && e.getPosX() - posX >= -20 && e.getPosX() - posX <= 240){
            return false;
        }
        return false;
    }
    public static int getCost(){ return cost; }
}

