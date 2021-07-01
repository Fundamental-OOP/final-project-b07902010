package unit.ally;

import bullet.Bullet_MiMiMaoMao;
import unit.enemy.Enemy;
import model.*;


public class Ally_MiMiMaoMao extends Shooter {
    static private int hp = 100;
    static private int atk = 100;
    static private int cost = 100;
    static private int deaddelay = 20;
    static private int attackcycle = 10;
    static private int bulletSpeed = 50;
    private static int atkDelay = 8;
    public Ally_MiMiMaoMao (int posX, int posY, int lane, int column, LevelWorld levelWorld) {
        super("MiMiMaoMao", hp, atk, posX, posY, lane, column, deaddelay, attackcycle, atkDelay, levelWorld, cost);
    }

    public void shoot() {
        this.levelWorld.addBullet(new Bullet_MiMiMaoMao(mutableATK, posX, posY, lane, bulletSpeed, levelWorld));
    }

    public boolean canSee(Enemy e) { 
        if(e.getLane() == this.lane){
            return e.getPosX() - posX > -20;
        }
        return false;
    }
    public static int getCost(){ return Ally_MiMiMaoMao.cost; }
}
