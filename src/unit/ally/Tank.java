package unit.ally;
import unit.State;
import model.*;

public abstract class Tank extends Ally {
    public Tank (String Name, int HP, int posX, int posY, int lane, int column, int deadDelay, LevelWorld levelWorld, int cost) {
        super(Name, HP, 0, posX, posY, lane, column, deadDelay, levelWorld, cost);
    }

    public void update() {
        if(HP <= 0){ state = State.Dead; }
        switch(state){
            case Idle:
                break;
            case Dead:
                if (deadCountDown == deadDelay){
                    levelWorld.moveAllyToGraveYard(this);
                }
                else if (deadCountDown <= 0) {
                    levelWorld.reallyKillAlly(this);
                }
                deadCountDown--;
                break;
            default:
        }
        // // dead
        // if (this.HP <= 0 ) {
        //     this.setState(State.Dead);
        //     if (deadCycle == 0)
        //         levelWorld.moveAllyToGraveYard(this);
        //     deadCycle++;
        //     if (deadCycle >= 5) {
        //         levelWorld.reallyKillAlly(this);
        //     }
        //     return;
        // }

        // // idle
        // else {
        //     this.setState(State.Idle);
        //     return;
        // }

    }

}
