package unit.ally;

import bullet.Bullet_Chihuahua;
import model.LevelWorld;
import unit.enemy.Enemy;
import utils.UnitImage;

public class Ally_Chihuahua extends Shooter{
    static private int hp = 80;
    static private int atk = 3;
    static private int cost = 225;
    static private int deaddelay = UnitImage.getUnitAnimation("Chihuahua", "dead").size();
    static private int attackcycle = 5;
    static private  int bulletSpeed = 5;
    private static int atkDelay = UnitImage.getUnitAnimation("Saxophone", "attack").size();
    private static double deBuff = 0.8;
    private static int deBuffDuration = 100;
    public Ally_Chihuahua(int posX, int posY, int lane, int column, LevelWorld levelWorld){
        super("Chihuahua", hp, atk, posX, posY, lane, column, deaddelay, attackcycle, atkDelay, levelWorld, cost);
    }
    @Override
    public boolean canSee(Enemy e) {
        if(e.getLane() == this.lane){
            return e.getPosX() - posX > -20;
        }
        return false;
    }
    @Override
    void shoot() {
        this.levelWorld.addBullet(new Bullet_Chihuahua(mutableATK, posX, posY, lane, bulletSpeed,  levelWorld, this, deBuff, deBuffDuration));
    }
    public static int getCost(){ return Ally_Chihuahua.cost; }
}
