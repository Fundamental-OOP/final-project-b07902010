package unit.ally;

import bullet.Bullet_Chihuahua;
import model.LevelWorld;
import unit.enemy.Enemy;

public class Ally_Chihuahua extends Shooter{
    static private int hp = 50;
    static private int atk = 30;
    static private int cost = 10;
    static private int deaddelay = 20;
    static private int attackcycle = 5;
    static private  int bulletSpeed = 5;
    private static int atkDelay = 8;
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
        this.levelWorld.addBullet(new Bullet_Chihuahua(mutableATK, posX, posY, lane, bulletSpeed,  levelWorld));
    }
    public static int getCost(){ return Ally_Chihuahua.cost; }
}
