package model;

import level.Level;
import level.LevelConstructor;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


import resource.Poop;
import battletype.BattleStatus;
import unit.*;
import unit.enemy.Enemy;
import unit.ally.Ally;
import bullet.*;
import resource.*;
import castle.Castle;
import graphics.Renderee;

import selector.Selector;
import selector.Button;


public class LevelWorld extends World{
    private LevelConstructor levelConstructor;
    private Level level;
    private final AllyConstructor allyConstructor = new AllyConstructor(this);
    private final EnemyConstructor enemyConstructor = new EnemyConstructor(this);
    private Selector selector;

    private final List< Ally > allies = new CopyOnWriteArrayList< Ally >();
    private final List< Enemy > enemies = new CopyOnWriteArrayList< Enemy >();
    private final List< Ally > dyingAllies = new CopyOnWriteArrayList< Ally >();
    private final List< Enemy > dyingEnemies = new CopyOnWriteArrayList< Enemy >();
    private final List < Bullet > bullets = new CopyOnWriteArrayList< Bullet >();
    private Castle castle;
    private Poop poopPurse;

    
    // protected Background background;
    public LevelWorld(LevelConstructor levelConstructor){
        super("Level");
        this.levelConstructor = levelConstructor;
        levelConstructor.setWorld(this);
    }

    @Override
    public boolean update() {
        level.update();
        renderees.clear();
        for (Ally ally : allies) {
            addRenderee((Renderee) ally);
            ally.update();
        }
        for(Ally dyingAlly : dyingAllies){
            addRenderee((Renderee) dyingAlly);
            dyingAlly.update();
        }
        for(Enemy enemy : enemies){
            addRenderee((Renderee) enemy);
            enemy.update();
        }
        for(Enemy dyingEnemy : dyingEnemies){
            addRenderee((Renderee) dyingEnemy);
            dyingEnemy.update();
        }
        for(Bullet bullet : bullets){
            addRenderee((Renderee) bullet);
            bullet.update();
        }
        // renderees.addAll( (List<Renderee>) allies );
        // renderees.addAll( (List<Renderee>) dyingAlly );
        // renderees.addAll( (List<Renderee>) allies );
        return checkGameOver();
    }

    public void resetWorld(){   // be called when this world is the next one to run
        allies.clear();
        enemies.clear();
        dyingAllies.clear();
        dyingEnemies.clear();
        castle = null;
        poopPurse = null;
        selector = null;
        setLevel(levelConstructor.constructLevel(loadData()));
    }
    public String loadData(){
        String levelName = "level_test";    // for testing
        // read in record and get next level name
        return levelName;
    }
    public void setLevel(Level level){
        this.level = level;
        castle = new Castle();
        poopPurse = new Poop();
        // background = level.getBackground();
    }

    private void setUpSelector(){
        int allyTypeNum = 1;
        
        selector = new Selector(allyTypeNum);
        // for(int i = 0; i < allyTypeNum; i++){
        //     selector.addSelection("", "", "")
        // }
        selector.addSelection("MiMiMaoMao", "./img/MiMiMaoMao/icon.png", "./img/MiMiMaoMao/preview.png");
    }

    // adjust units
    public void addAlly(String allyType, int lane, int column){
        Ally freshman = allyConstructor.constructAlly(allyType, lane, column);
        allies.add(freshman);
        freshman.setLevelWorld(this);
        // addRenderee((Renderee)freshman);
    }
    public void moveAllyToGraveYard(Ally victim){
        allies.remove(victim);
        dyingAllies.add(victim);
    }
    public void reallyKillAlly(Ally theRealVictim){
        dyingAllies.remove(theRealVictim);
        // removeRenderee((Renderee)theRealVictim);
        theRealVictim.setLevelWorld(null);
    }
    public void addBullet(Bullet bullet){
        bullets.add(bullet);
    }
    public void removeBullet(Bullet bullet){
        bullets.remove(bullet);
    }
    public void addEnemy(String enemyType, int lane){
        Enemy freshman = enemyConstructor.constructEnemy(enemyType, lane);
        enemies.add(freshman);
        freshman.setLevelWorld(this);
        // addRenderee((Renderee)freshman);
    }
    public void moveEnemyToGraveYard(Enemy victim){
        enemies.remove(victim);
        dyingEnemies.add(victim);
    }
    public void reallyKillEnemy(Enemy theRealVictim){
        dyingEnemies.remove(theRealVictim);
        // removeRenderee((Renderee)theRealVictim);
        theRealVictim.setLevelWorld(null);
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
    private boolean checkGameOver(){     // return running or not
        if(checkBattleStatus() == BattleStatus.battleContinue){
            return true;
        }
        else{
            return false;
        }
    }

}
