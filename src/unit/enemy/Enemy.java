package unit.enemy;

import castle.Castle;
import unit.ally.Ally;
import unit.State;
import unit.Unit;

import java.util.List;
import model.*;

public abstract class Enemy extends Unit {
    protected int dx;
    protected int attackCycle;
    protected int attackCycleCnt;
    public Enemy (String Name, int HP, int ATK, int posX, int posY, int lane, int deadDelay, int attackCycle, LevelWorld levelWorld, int dx) {
        super(Name, "enemy", HP, ATK, posX, posY, lane, deadDelay, levelWorld);
        this.dx = dx;
        this.state = State.Walk;
        this.attackCycle = attackCycle;
        this.attackCycleCnt = 0;
    }

    public void update() {
        if(HP <= 0){ state = State.Dead; }
        List<Ally> allies = this.levelWorld.getAllies();
        switch(state){
            case Walk:
                posX -= dx;
                if(posX < 367){
                    posX = 367;
                    state = State.Attack;
                }
                for (Ally ally : allies){
                    if ( this.touch(ally) ) {
                        state = State.Attack;
                        break;
                    }
                }
                break;
            case Attack:
                attackCycleCnt = (attackCycleCnt + 1) % attackCycle;
                if(attackCycleCnt == 0){
                    boolean hasDamaged = false;
                    for (Ally ally : allies){
                        if ( this.touch(ally) ) {
                            this.damage(ally);
                            hasDamaged = true;
                        }
                    }
                    if (!hasDamaged && this.posX <= 367) {
                        this.damageCastle();
                        hasDamaged = true;
                    }
                    if(!hasDamaged){ state = State.Walk; }
                }
                else{
                    boolean touched = false;
                    for (Ally ally : allies){
                        if(touched = this.touch(ally)){
                            break;
                        }
                    }
                    if(!touched){ state = State.Walk; }
                }
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
    }

    protected void damage(Ally a) {
        int newHP = a.getHP() - this.ATK;
        a.setHP(Math.max(newHP, 0));
    }

    protected void damageCastle() {
        Castle castle = levelWorld.getCastle();
        int newHP =  castle.getHP() - this.ATK;
        castle.setHP(Math.max(newHP, 0));
    }

    protected boolean touch(Ally a) {  // TODO: set diff
        if(a.getLane() == lane){
            return Math.abs(a.getPosX() - this.posX) < 30;
        }
        return false;
    }

}