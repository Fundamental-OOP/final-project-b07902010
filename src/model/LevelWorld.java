package model;

import level.Level;
import level.LevelConstructor;

import java.util.ArrayList;
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
import record.Record;

import selector.AllySelector;


public class LevelWorld extends World{
    private LevelConstructor levelConstructor;
    private Level level;
    private final AllyConstructor allyConstructor = new AllyConstructor(this);
    private final EnemyConstructor enemyConstructor = new EnemyConstructor(this);
    private final AllySelector selector = new AllySelector(this);

    private final List< Ally > allies = new CopyOnWriteArrayList< Ally >();
    private final List< Enemy > enemies = new CopyOnWriteArrayList< Enemy >();
    private final List< Ally > dyingAllies = new CopyOnWriteArrayList< Ally >();
    private final List< Enemy > dyingEnemies = new CopyOnWriteArrayList< Enemy >();
    private final List < Bullet > bullets = new CopyOnWriteArrayList< Bullet >();
    private Castle castle;
    private Poop poopPurse;
    private final boolean[][] grid = new boolean[5][9];
    
    // protected Background background;
    public LevelWorld(LevelConstructor levelConstructor){
        super("Level");
        this.nextWorldType = "Home";
        this.levelConstructor = levelConstructor;
        levelConstructor.setWorld(this);
    }
    @Override
    public boolean update() {
        if(pause){ return true; }
        level.update();
        renderees.clear();
        // ally
        for (Ally ally : allies) { ally.update(); }
        for (Ally ally : allies) { addRenderee((Renderee) ally); }
        // dying ally
        for(Ally dyingAlly : dyingAllies){ dyingAlly.update(); }
        for(Ally dyingAlly : dyingAllies){ addRenderee((Renderee) dyingAlly); }
        // enemy
        for(Enemy enemy : enemies){ enemy.update(); }
        for(Enemy enemy : enemies){ addRenderee((Renderee) enemy); }
        // dying enemy
        for(Enemy dyingEnemy : dyingEnemies){ dyingEnemy.update(); }
        for(Enemy dyingEnemy : dyingEnemies){ addRenderee((Renderee) dyingEnemy); }
        //bullet
        for(Bullet bullet : bullets){ bullet.update(); }
        for(Bullet bullet : bullets){ addRenderee((Renderee) bullet); }
        poopPurse.update();
        selector.update();
        checkGameOver();
        return running;
    }
    public void reset(){   // be called when this world is the next one to run
        running = true;
        pause = false;
        allies.clear();
        enemies.clear();
        dyingAllies.clear();
        dyingEnemies.clear();
        bullets.clear();
        castle = null;
        poopPurse = null;
        selector.clear();
        resetGrid();
        setLevel(levelConstructor.constructLevel());
    }
    private void resetGrid(){
        for(int row = 0; row < 5; row++){
            for(int col = 0; col < 9; col++){
                grid[row][col] = false;
            }
        }
    }
    public void setLevel(Level level){
        this.level = level;
        castle = new Castle();
        poopPurse = new Poop(100, 100, 25);
        setUpSelector();
        // background = level.getBackground();
    }

    private void setUpSelector(){
        ArrayList< String > chosenAllyTypes = Record.getChosenAllyTypes();
        for(String allyType : chosenAllyTypes){
            selector.addSelection(allyType, "../img/ally/button.png", "../img/ally/button.png");
        }
    }

    public AllySelector getAllySelector() {
        return this.selector;
    }

    // adjust units
    public void addAlly(String allyType, int lane, int column){
        if(grid[lane][column]){ return; }
        int cost = AllyConstructor.getNeededPoop(allyType);
        if(poopPurse.enough(cost)){
            Ally freshman = allyConstructor.constructAlly(allyType, lane, column);
            allies.add(freshman);
            freshman.setLevelWorld(this);
            poopPurse.Use(cost);
            grid[lane][column] = true;
        }
        // addRenderee((Renderee)freshman);
    }
    public void moveAllyToGraveYard(Ally victim){
        allies.remove(victim);
        dyingAllies.add(victim);
    }
    public void reallyKillAlly(Ally theRealVictim){
        dyingAllies.remove(theRealVictim);
        grid[theRealVictim.getLane()][theRealVictim.getColumn()] = false;
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
        // theRealVictim.setLevelWorld(null);
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

    public BattleStatus checkBattleStatus(){
        return level.checkBattleStatus();
    }
    private boolean checkGameOver(){     // return running or not
        BattleStatus status = checkBattleStatus();
        if(status == BattleStatus.battleContinue){
            return true;
        }
        else if(status == BattleStatus.win){
            win();
            Record.gotoNextLevel();
            Record.writeRecord();
        }
        else if(status == BattleStatus.lose){
            lose();
        }
        else{
            System.out.println("[LevelWorld] Undefined battle status.");
        }
        
        return false;
    }
    private void win(){
        running = false;
    }
    private void lose(){
        running = false;
    }
}
