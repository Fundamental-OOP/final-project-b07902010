package unit.ally;

import java.util.List;

import model.LevelWorld;
import unit.State;
import unit.enemy.Enemy;

public abstract class Warrior extends Ally{
    public Warrior(String Name, int HP, int ATK, int posX, int posY, int lane, int column, int deadDelay, int attackCycle, int attackDelay, LevelWorld levelWorld, int cost){
        super(Name, HP, ATK, posX, posY, lane, column, deadDelay, attackCycle, attackDelay, levelWorld, cost);
    }

    @Override
    public void update() {
        super.update();
        if(this.HP <= 0){ state = State.Dead; }
        List<Enemy> enemies = this.levelWorld.getEnemies();
        switch(state){
            case Idle:
                attackCycleCnt = 0;
                for (Enemy enemy : enemies){
                    if ( canSee(enemy) ) {
                        state = State.WaitForAttack;
                        attackCycleCnt = mutableAttackCycle - 1;
                        break;
                    }
                }
                break;
            case WaitForAttack:
                for (Enemy enemy : enemies){
                    if ( canSee(enemy) ) {
                        attackCycleCnt = (attackCycleCnt + 1) % mutableAttackCycle;
                        if(attackCycleCnt == 0){ state = State.Attack; }
                        break;
                    }
                }
                state = State.Idle;
                break;
            case Attack:
                if(--attackCountDown > 0){ break; }
                HIT(enemies);
                state = State.WaitForAttack;
                attackCountDown = attackDelay;
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
    }
    protected abstract void HIT(List< Enemy > enemies);
    protected abstract boolean canSee(Enemy e);
}
