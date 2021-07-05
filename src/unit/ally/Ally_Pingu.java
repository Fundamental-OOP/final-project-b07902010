package unit.ally;

import java.util.List;

import model.LevelWorld;
import unit.enemy.Enemy;
import utils.UnitImage;

public class Ally_Pingu extends Warrior{
    private static int hp = 100;
    private static int atk = 5;
    private static int cost = 200;
    private static int deaddelay = UnitImage.getUnitAnimation("Pingu", "dead").size();
    private static int attackcycle = 50;
    private static int atkDelay = UnitImage.getUnitAnimation("Pingu", "attack").size();
    public Ally_Pingu(int posX, int posY, int lane, int column, LevelWorld levelWorld){
        super("Pingu", hp, atk, posX, posY, lane, column, deaddelay, attackcycle, atkDelay, levelWorld, cost);
    }
    @Override
    protected void HIT(List<Enemy> enemies) {
        for(Enemy enemy : enemies){
            if(canSee(enemy)){
                enemy.damaged(this, mutableATK);
                enemy.setPosX(enemy.getPosX() + 50);
                break;
            }
        }
    }
    @Override
    protected boolean canSee(Enemy e) {
        if(e.getLane() == lane && e.getPosX() - posX >= -50 && e.getPosX() - posX <= 150){
            return true;
        }
        return false;
    }
    public static int getCost(){ return cost; }
}

