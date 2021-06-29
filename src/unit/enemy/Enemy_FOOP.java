package unit.enemy;

import unit.State;
import graphics.*;

import model.*;

public class Enemy_FOOP extends Enemy {
    public Enemy_FOOP (int posX, int posY, int lane, LevelWorld levelWorld ) {
        super(100, 10, posX, posY, lane, levelWorld, 1);
        this.walkRenderer = new AnimationRenderer("../img/enemy/FOOP/walk", "walk");
        this.idleRenderer = new AnimationRenderer("../img/enemy/FOOP/idle", "idle");
        this.attackRenderer = new AnimationRenderer("../img/enemy/FOOP/attack", "attack");
        this.beAttackedRenderer = new AnimationRenderer("../img/enemy/FOOP/beAttack", "beAttack");
        this.deadRenderer = new AnimationRenderer("../img/enemy/FOOP/dead", "dead");
    }
}
