package unit.ally;

import bullet.Bullet_RainbowCat;
import model.LevelWorld;
import unit.enemy.Enemy;

public class Ally_RainbowCat extends Shooter{
    static private int hp = 100;
    static private int atk = 100;
    static private int cost = 100;
    static private int deaddelay = 20;
    static private int shootcycle = 10;
    static private int bulletSpeed = 10;
    public Ally_RainbowCat(int posX, int posY, int lane, int column, LevelWorld levelWorld){
        super("RainbowCat", hp, atk, posX, posY, lane, column, deaddelay, levelWorld, cost, shootcycle);
        
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
            levelWorld.addBullet(new Bullet_RainbowCat(ATK, posX, posY, lane-1, bulletSpeed, levelWorld));
        }
        if(lane < 4){
            levelWorld.addBullet(new Bullet_RainbowCat(ATK, posX, posY, lane+1, bulletSpeed, levelWorld));
        }
        levelWorld.addBullet(new Bullet_RainbowCat(ATK, posX, posY, lane, bulletSpeed, levelWorld));
    }
    public static int getCost(){ return Ally_RainbowCat.cost; }
}
