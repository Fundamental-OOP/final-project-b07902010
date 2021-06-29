package unit.ally;

import unit.State;
import unit.Unit;

import model.*;

public abstract class Ally extends Unit {
    protected int cost;
    int column;

    public Ally (int HP, int ATK, int posX, int posY, int lane, int column, LevelWorld levelWorld, int cost) {
        super(HP, ATK, posX, posY, lane, levelWorld);
        this.cost = cost;
        this.state = State.Idle;
        this.column = column;
    }

    public int getCost() { return cost; }
    public void setCost(int cost) { this.cost = cost; }
    public int getColumn(){ return column; }
    abstract public void update();
}