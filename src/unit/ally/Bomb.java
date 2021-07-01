package unit.ally;

import java.util.ArrayList;
import java.util.List;


import model.LevelWorld;
import unit.State;
import unit.enemy.Enemy;

public abstract class Bomb extends Ally{
    int explosionDelay;
    int explosionCountDown;
    public Bomb(String Name, int HP, int ATK, int posX, int posY, int lane, int column, int deadDelay, LevelWorld levelWorld, int cost, int explosionDelay){
        super(Name, HP, ATK, posX, posY, lane, column, deadDelay, 0, 0, levelWorld, cost);
        this.explosionDelay = explosionDelay;
        explosionCountDown = explosionDelay;
    }
    
    @Override
    public void update(){
        super.update();
        if(HP <= 0){ state = State.Dead; }
        List< Enemy > enemies = levelWorld.getEnemies();
        switch(state){
            case Idle:
                if(isTimeToExplode(enemies)){
                    state = State.Dead;
                }
                break;
            case Dead:
                if(explosionCountDown == 0){
                    BOOM(enemies);
                }
                if (deadCountDown == deadDelay){
                    levelWorld.moveAllyToGraveYard(this);
                }
                else if (deadCountDown <= 0) {
                    levelWorld.reallyKillAlly(this);
                }
                deadCountDown--;
                explosionCountDown--;
                break;
            default:
                state = State.Idle;
        }
    }
    protected abstract boolean canSee(Enemy e);
    protected abstract boolean isTimeToExplode(List< Enemy > enemies);
    protected abstract void BOOM(List< Enemy > enemies);
}
