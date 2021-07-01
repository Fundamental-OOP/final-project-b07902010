package unit.enemy;

import castle.Castle;
import unit.ally.Ally;
import unit.State;
import unit.Unit;

import java.util.List;
import model.*;

public abstract class Enemy extends Unit {
    protected final int dx;
    protected int mutableDx;
    protected int dxRecoverTime;
    public Enemy (String Name, int HP, int ATK, int posX, int posY, int lane, int deadDelay, int attackCycle, int attackDelay, LevelWorld levelWorld, int dx) {
        super(Name, "enemy", HP, ATK, posX, posY, lane, deadDelay, attackCycle, attackDelay, levelWorld);
        this.dx = dx;
        this.state = State.Walk;
        this.mutableDx = dx;
        this.dxRecoverTime = 0;
    }

    public void update() {
        super.update();
        if(HP <= 0){ state = State.Dead; }
        List<Ally> allies = this.levelWorld.getAllies();
        boolean touched = false;
        switch(state){
            case WaitForAttack:
                touched = false;
                if(posX <= 367){ touched = true; }
                else{
                    for (Ally ally : allies){
                        if ( this.touch(ally) ) {
                            touched = true;
                            break;
                        }
                    }
                }
                if(touched){
                    attackCycleCnt = (attackCycleCnt + 1) % mutableAttackCycle;
                    if(attackCycleCnt == 0){ state = State.Attack; }
                }
                else{ state = State.Walk; }
                break;
            case Walk:
                touched = false;
                posX -= mutableDx;
                if(posX < 367){
                    posX = 367;
                    touched = true;
                }
                else{
                    for (Ally ally : allies){
                        if ( this.touch(ally) ) {
                            touched = true;
                            break;
                        }
                    }
                }
                if(touched){
                    state = State.WaitForAttack;
                    attackCycleCnt = mutableAttackCycle - 1;
                }
                break;
            case Attack:
                if(--attackCountDown > 0){ break; }
                boolean hasDamaged = false;
                for (Ally ally : allies){
                    if ( this.touch(ally) ) {
                        this.damage(ally);
                        hasDamaged = true;
                    }
                }
                if (!hasDamaged && posX <= 367) { damageCastle(); }
                state = State.WaitForAttack;
                attackCountDown = attackDelay;
                break;
            case Dead:
                if (deadCountDown == deadDelay){
                    levelWorld.moveEnemyToGraveYard(this);
                    deadCountDown--;
                }
                else if (deadCountDown <= 0) {
                    levelWorld.reallyKillEnemy(this);
                }
                else{
                    deadCountDown--;
                }
                break;
            default:

        }
        recoverDx();
    }

    protected void damage(Ally a) {
        int newHP = a.getHP() - mutableATK;
        a.setHP(Math.max(newHP, 0));
    }

    protected void damageCastle() {
        Castle castle = levelWorld.getCastle();
        int newHP =  castle.getHP() - mutableATK;
        castle.setHP(Math.max(newHP, 0));
    }

    protected boolean touch(Ally a) {  // TODO: set diff
        if(a.getLane() == lane){
            return Math.abs(a.getPosX() - this.posX) < 30;
        }
        return false;
    }
    public int getDx(){ return dx; }
    public void setDx(int dx, int time){
        mutableDx = dx;
        dxRecoverTime = time;
    }
    protected void recoverDx(){
        if(dxRecoverTime > 0){
            dxRecoverTime--;
        }
        else{
            mutableDx = dx;
        }
    }
}