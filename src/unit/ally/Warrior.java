package unit.ally;

import java.util.List;

import model.LevelWorld;
import unit.State;
import unit.enemy.Enemy;

public abstract class Warrior extends Ally{
    private final int hitCycle;
    private int hitCycleCnt;
    protected int mutableHitCycle;
    protected int hitCycleRecoverTime;
    public Warrior(String Name, int HP, int posX, int posY, int lane, int column, int deadDelay, LevelWorld levelWorld, int cost, int hitCycle){
        super(Name, HP, 0, posX, posY, lane, column, deadDelay, levelWorld, cost);
        this.hitCycle = hitCycle;
    }

    @Override
    public void update() {
        super.update();
        if(this.HP <= 0){ state = State.Dead; }
        List<Enemy> enemies = this.levelWorld.getEnemies();
        switch(state){
            case Idle:
                hitCycleCnt = 0;
                for (Enemy enemy : enemies){
                    if ( canSee(enemy) ) {
                        state = State.Attack;
                        break;
                    }
                }
                break;
            case Attack:
                hitCycleCnt = (hitCycleCnt + 1) % hitCycle;
                if(hitCycleCnt == 0){
                    if(!HIT(enemies)){
                        state = State.Idle;
                    }
                }
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
        recoverHitCycle();
    }
    protected abstract boolean HIT(List< Enemy > enemies);
    protected abstract boolean canSee(Enemy e);
    public void setHitCycle(int hitCycle, int time){
        mutableHitCycle = hitCycle;
        hitCycleRecoverTime = time;
    }
    protected void recoverHitCycle(){
        if(hitCycleRecoverTime > 0){
            hitCycleRecoverTime--;
        }
        else{
            mutableHitCycle = hitCycle;
        }
    }
    
}
