package unit.ally;

import bullet.Bullet;
import bullet.Bullet_MiMiMaoMao;
import unit.enemy.Enemy;
import graphics.*;
import model.*;


public class Ally_MiMiMaoMao extends Shooter {
    static private int cost;
    public Ally_MiMiMaoMao (int posX, int posY, int lane, int column, LevelWorld levelWorld) {
        super(100,100, posX, posY, lane, column, levelWorld, 100, 10); // TODO:寫死
        this.walkRenderer = new AnimationRenderer("../img/ally/MiMiMaoMao/walk", "walk");
        this.idleRenderer = new AnimationRenderer("../img/ally/MiMiMaoMao/idle", "idle");
        this.attackRenderer = new AnimationRenderer("../img/ally/MiMiMaoMao/attack", "attack");
        this.beAttackedRenderer = new AnimationRenderer("../img/ally/MiMiMaoMao/beAttack", "beAttack");
        this.deadRenderer = new AnimationRenderer("../img/ally/MiMiMaoMao/dead", "dead");
        cost = 100;
    }

    public void shoot() {
        Bullet bullet = new Bullet_MiMiMaoMao(posX, posY, lane, levelWorld);
        this.levelWorld.addBullet(bullet);
    }

    public boolean canSee(Enemy e) { 
        return e.getLane() == this.lane;
    }
    public static int getCost(){ return Ally_MiMiMaoMao.cost; }
}
