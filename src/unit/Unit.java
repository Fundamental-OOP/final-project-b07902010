package unit;
import graphics.*;
import model.*;
import utils.*;

public abstract class Unit implements Renderee {
    protected int HP;
    protected final int ATK;
    protected int posX, posY;
    protected int lane;
    protected LevelWorld levelWorld;
    protected State state;
    protected final int deadDelay;
    protected int deadCountDown;
    protected final int attackCycle;
    protected int attackCycleCnt;
    protected final int attackDelay;
    protected int attackCountDown;
    
    protected int mutableATK;
    protected int ATKrecoverTime;
    protected int mutableAttackCycle;
    protected int attackCycleRecoverTime;

    protected AnimationRenderer walkRenderer, idleRenderer, attackRenderer, beAttackedRenderer, deadRenderer;
    
    public Unit(String name, String type, int HP, int ATK, int posX, int posY, int lane, int deadDelay, int attackCycle, int attackDelay, LevelWorld levelWorld) {
        this.HP = HP;
        this.ATK = ATK;
        this.mutableATK = ATK;
        this.posX = posX;
        this.posY = posY;
        this.lane = lane;
        this.levelWorld = levelWorld;
        this.deadDelay = deadDelay;
        this.deadCountDown = deadDelay;
        this.attackCycle = attackCycle;
        this.attackDelay = attackDelay;
        this.attackCountDown = attackDelay;
        this.mutableAttackCycle = attackCycle;
        this.attackCycleCnt = 0;
        this.ATKrecoverTime = 0;
        this.walkRenderer = new AnimationRenderer(UnitImage.getUnitAnimation(name, "walk"));
        this.idleRenderer = new AnimationRenderer(UnitImage.getUnitAnimation(name, "idle"));
        this.attackRenderer = new AnimationRenderer(UnitImage.getUnitAnimation(name, "attack"));
        this.beAttackedRenderer = new AnimationRenderer(UnitImage.getUnitAnimation(name, "beAttack"));
        this.deadRenderer = new AnimationRenderer(UnitImage.getUnitAnimation(name, "dead"));
    }

    public int getHP() { return HP; }
    public void setHP(int HP) { this.HP = HP; }
    public void damaged(Unit attacker, int dmg){
        HP -= dmg;
    }

    public int getATK() { return ATK; }
    public void setATK(int ATK, int time) {
        ATKrecoverTime = time;
        this.mutableATK = ATK;
    }
    public void recoverATK(){
        if(ATKrecoverTime > 0){
            ATKrecoverTime--;
        }
        else{
            mutableATK = ATK;
        }
    }

    public int getAttackCycle(){ return mutableAttackCycle; }
    public void setAttackCycle(int attackCycle, int time){
        mutableAttackCycle = attackCycle;
        attackCycleRecoverTime = time;
    }
    protected void recoverAttackCycle(){
        if(attackCycleRecoverTime > 0){
            attackCycleRecoverTime--;
        }
        else{
            mutableAttackCycle = attackCycle;
        }
    }
    
    public int getLane() { return lane; }
    public void setLane(int lane) {
        this.lane = lane;
    }

    public int getPosX() { return posX; }

    public int getPosY() { return posY; }

    public void setPosX(int x) { this.posX = x; }

    public void setPosY(int y) { this.posY = y; }

    public State getState() { return state; }

    public void setState(State state) { this.state = state; }

    public LevelWorld getLevelWorld() { return levelWorld; }

    public void setLevelWorld(LevelWorld levelWorld) { this.levelWorld = levelWorld; }

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
        else if( state == State.WaitForAttack) {
            idleRenderer.setPosition(posX, posY);
            return idleRenderer;
        }
        return null;
    }

    public void update(){
        recoverATK();
        recoverAttackCycle();
    }
}

