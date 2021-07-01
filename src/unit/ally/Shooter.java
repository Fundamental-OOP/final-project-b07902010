package unit.ally;

import unit.enemy.Enemy;
import unit.State;
import unit.Unit;
import model.*;
import java.util.List;

public abstract class Shooter extends Ally {
    public Shooter (String Name, int HP, int ATK, int posX, int posY, int lane, int column, int deadDelay, int attackCycle, int attackDelay, LevelWorld levelWorld, int cost) {
        super(Name, HP, ATK, posX, posY, lane, column, deadDelay, attackCycle, attackDelay, levelWorld, cost);
    }

    public void update() {
        super.update();
        if(this.HP <= 0){ state = State.Dead; }
        List<Enemy> enemies = this.levelWorld.getEnemies();
        switch(state){
            case Idle:
                attackCycleCnt = 0;
                for (Enemy enemy : enemies){
                    if ( this.aim(enemy) ) {
                        state = State.WaitForAttack;
                        attackCycleCnt = mutableAttackCycle -1;
                        break;
                    }
                }
                break;
            case WaitForAttack:
                for (Enemy enemy : enemies){
                    if ( this.aim(enemy) ) {
                        attackCycleCnt = (attackCycleCnt + 1) % mutableAttackCycle;
                        if(attackCycleCnt == 0){ state = State.Attack; }
                        break;
                    }
                }
                break;
            case Attack:
                if(--attackCountDown > 0){ break; }
                for (Enemy enemy : enemies){
                    if ( this.aim(enemy) ) {
                        this.shoot();
                        break;
                    }
                }
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

    public boolean aim(Unit u) {
        return u instanceof Enemy && this.canSee((Enemy) u);
    }

    abstract public boolean canSee(Enemy e);
    abstract void shoot();
}
