package unit;
import graphics.*;
import model.*;

public abstract class Unit implements Renderee {
    protected int HP;
    protected int ATK;
    protected int posX, posY;
    protected int lane;
    protected  LevelWorld levelWorld;
    protected State state;
    protected int deadCycle = 0;

    protected AnimationRenderer walkRenderer, idleRenderer, attackRenderer, beAttackedRenderer, deadRenderer;

    public Unit(int HP, int ATK, int posX, int posY, int lane, LevelWorld levelWorld) {
        this.HP = HP;
        this.ATK = ATK;
        this.posX = posX;
        this.posY = posY;
        this.lane = lane;
        this.levelWorld = levelWorld;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getATK() {
        return ATK;
    }

    public void setATK(int ATK) {
        this.ATK = ATK;
    }

    public int getLane() {
        return lane;
    }

    public void setLane(int lane) {
        this.lane = lane;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int x) {
        this.posX = x;
    }

    public void setPosY(int y) {
        this.posY = y;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public LevelWorld getLevelWorld() {
        return levelWorld;
    }

    public void setLevelWorld(LevelWorld levelWorld) {
        this.levelWorld = levelWorld;
    }

    public Renderer getRenderer() {

        if (state == State.Walk) {
            walkRenderer.setPosition(posX, posY);
            return walkRenderer;
        }
        else if (state == State.Idle) {
            idleRenderer.setPosition(posX, posY);
            return idleRenderer;
        }
        else if (state == State.Attack) {
            attackRenderer.setPosition(posX, posY);
            return attackRenderer;
        }
        else if (state == State.BeAttacked) {
            beAttackedRenderer.setPosition(posX, posY);
            return beAttackedRenderer;
        }
        else if (state == State.Dead) {
            deadRenderer.setPosition(posX, posY);
            return deadRenderer;
        }
        return null;
    }

    abstract public void update();
}

