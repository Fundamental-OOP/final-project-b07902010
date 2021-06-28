package unit.ally;

import unit.State;
import unit.Unit;

import model.*;

public abstract class Ally extends Unit {
    protected int cost;

    public Ally (int HP, int ATK, int posX, int posY, int lane, LevelWorld levelWorld, int cost) {
        super(HP, ATK, posX, posY, lane, levelWorld);
        this.cost = cost;
        this.state = State.Idle;
    }

    public int getCost() { return cost; }
    public void setCost(int cost) { this.cost = cost; }

    abstract public void update();
}