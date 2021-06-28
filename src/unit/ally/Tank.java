package unit.ally;
import unit.State;
import model.*;

public abstract class Tank extends Ally {
    public Tank (int HP, int posX, int posY, int lane, LevelWorld levelWorld, int cost) {
        super(HP, 0, posX, posY, lane, levelWorld, cost);
    }

    public void update() {
        // dead
        if (this.HP <= 0 ) {
            this.setState(State.Dead);
            deadCycle++;
            if(deadCycle >= 5) {
                levelWorld.reallyKillAlly(this);
            }
            return;
        }

        // idle
        else {
            this.setState(State.Idle);
            return;
        }

    }

}
