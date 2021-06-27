package unit.ally;

import bullet.Bullet;
import bullet.Bullet_MiMiMaoMao;
import unit.enemy.Enemy;
import graphics.*;
import model.*;


public class Ally_MiMiMaoMao extends Shooter {

    public Ally_MiMiMaoMao (int posX, int posY, int lane, LevelWorld levelWorld) {
        super(100,100, posX, posY, lane, levelWorld, 100); // TODO:寫死
        this.walkRenderer = new AnimationRenderer("./img/shooter/MiMiMaoMao", "walk");
        this.idleRenderer = new AnimationRenderer("./img/shooter/MiMiMaoMao", "idle");
        this.attackRenderer = new AnimationRenderer("./img/shooter/MiMiMaoMao", "attack");
        this.beAttackedRenderer = new AnimationRenderer("./img/shooter/MiMiMaoMao", "beAttack");
        this.deadRenderer = new AnimationRenderer("./img/shooter/MiMiMaoMao", "dead");
    }

    public void shoot() {
        Bullet bullet = new Bullet_MiMiMaoMao(300, 300, 1, levelWorld);
        this.levelWorld.addBullet(bullet);
    }

    public boolean canSee(Enemy e) { return e.getLane() == this.lane; }
}