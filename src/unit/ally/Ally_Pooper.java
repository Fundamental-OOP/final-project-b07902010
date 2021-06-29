package unit.ally;
import unit.State;
import model.*;
import resource.*;
import graphics.*;


public class Ally_Pooper extends Ally {
    int poopCycle = 10;
    int poopAmount = 25;
    int nowCycle = 0;

    public Ally_Pooper(int posX, int posY, int lane, int column, LevelWorld levelWorld) {
        super(100, 0, posX, posY, lane, column, levelWorld, 100);
        this.walkRenderer = new AnimationRenderer("../img/ally/Pooper", "walk");
        this.idleRenderer = new AnimationRenderer("../img/ally/Pooper", "idle");
        this.attackRenderer = new AnimationRenderer("../img/ally/Pooper", "attack");
        this.beAttackedRenderer = new AnimationRenderer("../img/ally/Pooper", "beAttack");
        this.deadRenderer = new AnimationRenderer("../img/ally/Pooper", "dead");
    }

    public void update() {
        // dead
        if (this.HP <= 0 ) {
            this.setState(State.Dead);
            deadCycle++;
            if (deadCycle >= 5) {
                levelWorld.reallyKillAlly(this);
            }
            return;
        }

        // idle
        nowCycle++;
        if (nowCycle % poopCycle == 0) {
            this.setState(State.Idle);
            levelWorld.getPoop().pickUp(poopAmount);
        }
    }

}
