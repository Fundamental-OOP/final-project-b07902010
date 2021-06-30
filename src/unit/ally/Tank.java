package unit.ally;
import unit.State;
import model.*;

public abstract class Tank extends Ally {
    public Tank (int HP, int posX, int posY, int lane, int column, LevelWorld levelWorld, int cost) {
        super(HP, 0, posX, posY, lane, column, levelWorld, cost);
    }

    public void update() {
        // dead
        if (this.HP <= 0 ) {
            this.setState(State.Dead);
            if (deadCycle == 0)
                levelWorld.moveAllyToGraveYard(this);
            deadCycle++;
            if (deadCycle >= 5) {
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
