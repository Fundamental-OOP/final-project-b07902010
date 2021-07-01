package unit.ally;

import unit.enemy.Enemy;
import unit.State;
import unit.Unit;
import model.*;
import java.util.List;

public abstract class Shooter extends Ally {
    private final int shootCycle;
    private int shootCycleCnt;
    protected int mutableShootCycle;
    protected int shootCycleRecoverTime;
    public Shooter (String Name, int HP, int ATK, int posX, int posY, int lane, int column, int deadDelay, LevelWorld levelWorld, int cost, int shootCycle) {
        super(Name, HP, ATK, posX, posY, lane, column, deadDelay, levelWorld, cost);
        this.shootCycle = shootCycle;
        this.shootCycleCnt = 0;
        mutableShootCycle = shootCycle;
        shootCycleRecoverTime = 0;
    }

    public void update() {
        super.update();
        if(this.HP <= 0){ state = State.Dead; }
        List<Enemy> enemies = this.levelWorld.getEnemies();
        switch(state){
            case Idle:
                shootCycleCnt = 0;
                for (Enemy enemy : enemies){
                    if ( this.aim(enemy) ) {
                        state = State.Attack;
                        break;
                    }
                }
                break;
            case Attack:
                boolean hasShot = false;
                for (Enemy enemy : enemies){
                    if ( this.aim(enemy) ) {
                        hasShot = true;
                        shootCycleCnt = (shootCycleCnt + 1) % shootCycle;
                        if(shootCycleCnt == 0){ this.shoot(); }
                    }
                }
                if (!hasShot) { state = State.Idle; }
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
                state = State.Idle;
        }
        recoverShootCycle();
    }

    public boolean aim(Unit u) {
        return u instanceof Enemy && this.canSee((Enemy) u);
    }

    abstract public boolean canSee(Enemy e);
    abstract void shoot();
    public void setShootCycle(int shootCycle, int time){
        mutableShootCycle = shootCycle;
        shootCycleRecoverTime = time;
    }
    protected void recoverShootCycle(){
        if(shootCycleRecoverTime > 0){
            shootCycleRecoverTime--;
        }
        else{
            mutableShootCycle = shootCycle;
        }
    }
}
