package unit.enemy;

import unit.State;
import graphics.*;

import model.*;

public class Enemy_CNLab extends Enemy {
    public Enemy_CNLab (int posX, int posY, int lane, LevelWorld levelWorld ) {
        super(100, 10, posX, posY, lane, levelWorld, 10);
        this.walkRenderer = new AnimationRenderer("../img/enemy/CNLab", "walk");
        this.idleRenderer = new AnimationRenderer("../img/enemy/CNLab", "idle");
        this.attackRenderer = new AnimationRenderer("../img/enemy/CNLab", "attack");
        this.beAttackedRenderer = new AnimationRenderer("../img/enemy/CNLab", "beAttack");
        this.deadRenderer = new AnimationRenderer("../img/enemy/CNLab", "dead");
    }

}
