package unit.ally;

import unit.State;
import unit.Unit;

import model.*;

public abstract class Ally extends Unit {
    protected static int cost;
    int column;

    public Ally (int HP, int ATK, int posX, int posY, int lane, int column, LevelWorld levelWorld, int cost) {
        super(HP, ATK, posX, posY, lane, levelWorld);
        Ally.cost = cost;
        this.state = State.Idle;
        this.column = column;
    }

    static public int getCost() { return cost; }
    public void setCost(int cost) { Ally.cost = cost; }
    public int getColumn(){ return column; }
    abstract public void update();
}