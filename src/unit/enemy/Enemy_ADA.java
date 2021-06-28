package unit.enemy;

import unit.State;
import graphics.*;

import model.*;

public class Enemy_ADA extends Enemy {
    public Enemy_ADA (int posX, int posY, int lane, LevelWorld levelWorld ) {
        super(100, 10, posX, posY, lane, levelWorld, 10);
        this.walkRenderer = new AnimationRenderer("../img/enemy/ADA", "walk");
        this.idleRenderer = new AnimationRenderer("../img/enemy/ADA", "idle");
        this.attackRenderer = new AnimationRenderer("../img/enemy/ADA", "attack");
        this.beAttackedRenderer = new AnimationRenderer("../img/enemy/ADA", "beAttack");
        this.deadRenderer = new AnimationRenderer("../img/enemy/ADA", "dead");
    }

}