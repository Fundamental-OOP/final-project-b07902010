package unit.enemy;

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
            return;
        }

        // attack
        List<Ally> allies = this.levelWorld.getAllies();
        boolean hasDamage = false;
        for (Ally ally : allies){
            if ( this.touch(ally) ) {
                this.damage(ally);
                hasDamage = true;
            }
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

    protected boolean touch(Ally a) {  // TODO: set diff
        return Math.abs(a.getPosX() - this.posX) < 10;
    }

}