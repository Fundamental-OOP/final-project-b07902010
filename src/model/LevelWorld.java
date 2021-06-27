package model;

import level.Level;


import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


import resource.Poop;
import battletype.BattleStatus;



public class LevelWorld extends World{

    private Level level;

    protected final List< Ally > allies = new CopyOnWriteArrayList< Ally >();
    protected final List< Enemy > enemies = new CopyOnWriteArrayList< Enemy >();
    protected final List< Ally > dyingAllies = new CopyOnWriteArrayList< Ally >();
    protected final List< Enemy > dyingEnemies = new CopyOnWriteArrayList< Enemy >();
    protected final List < Bullet > bullets = new CopyOnWriteArrayList< Bullet >();
    protected Castle castle;
    protected Poop poopPurse;
    // protected Background background;

    @Override
    public boolean update() {
        level.update();
        for (Ally ally : allies) {
            ally.update();
        }
        for(Ally dyingAlly : dyingAllies){
            dyingAlly.update();
        }
        for(Bullet bullet : bullets){
            bullet.update();
        }
        for(Enemy enemy : enemies){
            enemy.update();
        }
        for(Enemy dyingEnemy : dyingEnemies){
            dyingEnemy.update();
        }
        
        return checkGameOver();
    }

    public void setLevel(Level level){
        this.level = level;
        castle = new Castle();
        Poop = new Poop();
        // background = level.getBackground();
    }

    // adjust units
    public void addAlly(Ally freshman){
        allies.add(freshman);
        freshman.setWorld(this);
        // addRenderee((Renderee)freshman);
    }
    public void moveAllyToGraveYard(Ally victim){
        allies.remove(victim);
        dyingAllies.add(victim);
    }
    public void reallyKillAlly(Ally theRealVictim){
        dyingAllies.remove(theRealVictim);
        // removeRenderee((Renderee)theRealVictim);
        theRealVictim.setWorld(null);
    }
    public void addBullet(Bullet bullet){
        bullets.add(bullet);
        bullet.setWorld(this);
    }
    public void removeBullet(Bullet bullet){
        bullets.remove(bullet);
        bullet.setWorld(null);
    }
    public void addEnemy(Enemy freshman){
        enemies.add(freshman);
        freshman.setWorld(this);
        // addRenderee((Renderee)freshman);
    }
    public void moveEnemyToGraveYard(Enemy victim){
        enemies.remove(victim);
        dyingEnemies.add(victim);
    }
    public void reallyKillEnemy(Enemy theRealVictim){
        dyingEnemies.remove(theRealVictim);
        // removeRenderee((Renderee)theRealVictim);
        theRealVictim.setWorld(null);
    }


    // get units
    public List< Ally > getAllies(){
        return allies;
    }
    public List< Enemy > getEnemies(){
        return enemies;
    }
    public List< Ally > getDyingAllies(){
        return dyingAllies;
    }
    public List< Enemy > getDyingEnemies(){
        return dyingEnemies;
    }
    public Castle getCastle(){
        return castle;
    }
    public Poop getPoop(){
        return poopPurse;
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
