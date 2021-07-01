package unit.ally;

import bullet.Bullet_RainbowCat;
import model.LevelWorld;
import unit.enemy.Enemy;
import utils.UnitImage;

public class Ally_RainbowCat extends Shooter{
    static private int hp = 100;
    static private int atk = 10;
    static private int cost = 325;
    static private int deaddelay =  UnitImage.getUnitAnimation("RainbowCat", "dead").size();
    static private int attackcycle = 10;
    static private int bulletSpeed = 50;
    private static int atkDelay = UnitImage.getUnitAnimation("RainbowCat", "attack").size();
    public Ally_RainbowCat(int posX, int posY, int lane, int column, LevelWorld levelWorld){
        super("RainbowCat", hp, atk, posX, posY, lane, column, deaddelay, attackcycle, atkDelay, levelWorld, cost);
        
    }
    @Override
    public boolean canSee(Enemy e) {
        if(Math.abs(e.getLane() - lane) <= 1 && e.getPosX() - posX >= -20){
            return true;
        }
        return false;
    }
    @Override
    void shoot() {
        if(lane > 0){
            levelWorld.addBullet(new Bullet_RainbowCat(mutableATK, posX, posY-120, lane-1, bulletSpeed, levelWorld, this));
        }
        if(lane < 4){
            levelWorld.addBullet(new Bullet_RainbowCat(mutableATK, posX, posY, lane+1, bulletSpeed, levelWorld, this));
        }
        levelWorld.addBullet(new Bullet_RainbowCat(mutableATK, posX, posY+120, lane, bulletSpeed, levelWorld, this));
    }
    public static int getCost(){ return Ally_RainbowCat.cost; }
}
