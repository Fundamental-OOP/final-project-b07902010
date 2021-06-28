package unit.enemy;

import unit.State;
import graphics.*;

import model.*;

public class Enemy_DSA extends Enemy {
    public Enemy_DSA (int posX, int posY, int lane, LevelWorld levelWorld ) {
        super(100, 10, posX, posY, lane, levelWorld, 10);
        this.walkRenderer = new AnimationRenderer("../img/enemy/DSA", "walk");
        this.idleRenderer = new AnimationRenderer("../img/enemy/DSA", "idle");
        this.attackRenderer = new AnimationRenderer("../img/enemy/DSA", "attack");
        this.beAttackedRenderer = new AnimationRenderer("../img/enemy/DSA", "beAttack");
        this.deadRenderer = new AnimationRenderer("../img/enemy/DSA", "dead");
    }

}
