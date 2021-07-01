package unit.ally;

import bullet.Bullet;
import bullet.Bullet_MiMiMaoMao;
import unit.enemy.Enemy;
import model.*;


public class Ally_MiMiMaoMao extends Shooter {
    static private int hp = 100;
    static private int atk = 100;
    static private int cost = 100;
    static private int deaddelay = 20;
    static private int shootcycle = 10;
    static private int bulletspeed = 10;
    public Ally_MiMiMaoMao (int posX, int posY, int lane, int column, LevelWorld levelWorld) {
        super("MiMiMaoMao", hp, atk, posX, posY, lane, column, deaddelay, levelWorld, cost, shootcycle); // TODO:寫死
    }

    public void shoot() {
        Bullet bullet = new Bullet_MiMiMaoMao(ATK, posX, posY, bulletspeed, lane, levelWorld);
        this.levelWorld.addBullet(bullet);
    }

    public boolean canSee(Enemy e) { 
        if(e.getLane() == this.lane){
            return e.getPosX() - posX > -20;
        }
        return false;
    }
    public static int getCost(){ return Ally_MiMiMaoMao.cost; }
}
