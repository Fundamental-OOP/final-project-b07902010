package unit.enemy;

import castle.Castle;
import unit.ally.Ally;
import unit.State;
import unit.Unit;

import java.util.List;
import model.*;

public abstract class Enemy extends Unit {
    int dx;

    public Enemy (int HP, int ATK, int posX, int posY, int lane, LevelWorld levelWorld, int dx) {
        super(HP, ATK, posX, posY, lane, levelWorld);
        this.dx = dx;
        this.state = State.Walk;
    }

    public void update() {
        // dead
        if (this.HP <= 0 ) {
            this.setState(State.Dead);
            if (deadCycle == 0)
                levelWorld.moveEnemyToGraveYard(this);
            deadCycle++;
            if (deadCycle >= 15) {
                levelWorld.reallyKillEnemy(this);
            }
            return;
        }

        // attack
        // attack ally
        List<Ally> allies = this.levelWorld.getAllies();
        boolean hasDamage = false;
        for (Ally ally : allies){
            if ( this.touch(ally) ) {
                this.damage(ally);
                hasDamage = true;
            }
        }
        // attack castle
        if (!hasDamage && this.posX <= 300) {
            this.damageCastle();
            hasDamage = true;
        }
        if (hasDamage) {
            this.setState(State.Attack);
            return;
        }

        // walk
        else {
            this.posX -= dx;
            this.setState(State.Walk);
            return;
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
        if(this.lane == a.getLane()){
            return Math.abs(a.getPosX() - this.posX) < 50;
        }
        return false;
    }

}