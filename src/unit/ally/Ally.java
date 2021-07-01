package unit.ally;

import unit.State;
import unit.Unit;

import model.*;

public abstract class Ally extends Unit {
    protected int cost;
    int column;

    public Ally (String Name, int HP, int ATK, int posX, int posY, int lane, int column, int deadDelay, LevelWorld levelWorld, int cost) {
        super(Name, "ally", HP, ATK, posX, posY, lane, deadDelay, levelWorld);
        this.cost = cost;
        this.state = State.Idle;
        this.column = column;
    }

    // static public int getCost() { return Ally.cost; }
    public void setCost(int cost) { this.cost = cost; }
    public int getColumn(){ return column; }
    abstract public void update();
}