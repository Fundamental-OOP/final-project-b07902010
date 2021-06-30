package unit.enemy;

import unit.State;
import graphics.*;

import model.*;

public class Enemy_ADA extends Enemy {
    public Enemy_ADA (int posX, int posY, int lane, LevelWorld levelWorld ) {
        super(300, 10, posX, posY, lane, levelWorld, 10);
        this.walkRenderer = new AnimationRenderer("../img/enemy/ADA/walk", "walk");
        this.idleRenderer = new AnimationRenderer("../img/enemy/ADA/idle", "idle");
        this.attackRenderer = new AnimationRenderer("../img/enemy/ADA/attack", "attack");
        this.beAttackedRenderer = new AnimationRenderer("../img/enemy/ADA/beAttack", "beAttack");
        this.deadRenderer = new AnimationRenderer("../img/enemy/ADA/dead", "dead");
    }

}
