package model;

import level.Level;


import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


import resource.Poop;
import battletype.BattleStatus;



public class LevelWorld extends World{

    private Level level;

    protected final List< Unit > allies = new CopyOnWriteArrayList< Unit >();
    protected final List< Unit > enemies = new CopyOnWriteArrayList< Unit >();
    protected final List< Unit > dyingAllies = new CopyOnWriteArrayList< Unit >();
    protected final List< Unit > dyingEnemies = new CopyOnWriteArrayList< Unit >();

    protected Castle castle;
    protected Poop poop;
    protected Background background;

    @Override
    public boolean update() {
        level.update();
        for (Unit ally : allies) {
            ally.update();
        }
        for(Unit enemy : enemies){
            enemy.update();
        }
        for(Unit dyingAlly : dyingAllies){
            dyingAlly.updaye();
        }
        for(Unit dyingEnemy : dyingEnemies){
            dyingEnemy.update();
        }
        return checkGameOver();
    }

    public void setLevel(Level level){
        this.level = level;
        castle = new Castle();
        Poop = new Poop();
        background = level.getBackground();
    }

    // adjust units
    public void addAlly(Ally freshman){
        allies.add(freshman);
        // freshman.setWorld(this);
        // addRenderee((Renderee)freshman);
    }
    public void moveAllyToGraveYard(Unit victim){
        allies.remove(victim);
        dyingAllies.add(victim);
    }
    public void reallyKillAlly(Unit theRealVictim){
        dyingAllies.remove(theRealVictim);
        // removeRenderee((Renderee)theRealVictim);
        // theRealVictim.setWorld(null);
    }
    public void addEnemy(Unit freshman){
        enemies.add(freshman);
        // freshman.setWorld(this);
        // addRenderee((Renderee)freshman);
    }
    public void moveEnemyToGraveYard(Unit victim){
        enemies.remove(victim);
        dyingEnemies.add(victim);
    }
    public void reallyKillEnemy(Unit theRealVictim){
        dyingEnemies.remove(theRealVictim);
        // removeRenderee((Renderee)theRealVictim);
        // theRealVictim.setWorld(null);
    }


    // get units
    public List< Unit > getAllies(){
        return allies;
    }
    public List< Unit > getEnemies(){
        return enemies;
    }
    public List< Unit > getDyingAllies(){
        return dyingAllies;
    }
    public List< Unit > getDyingEnemies(){
        return dyingEnemies;
    }
    public Castle getCastle(){
        return castle;
    }
    public Poop getPoop(){
        return Poop;
    }

    private BattleStatus checkBattleStatus(){
        return level.checkBattleStatus();
    }
    public boolean checkGameOver(){     // return running or not
        if(checkBattleStatus() == BattleStatus.battleContinue){
            return true;
        }
        else{
            return false;
        }
    }

}
