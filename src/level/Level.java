package level;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import battletype.BattleStatus;
import battletype.BattleStatusChecker;
import model.LevelWorld;
import unit.AllyConstructor;
import unit.EnemyConstructor;

public class Level {
    private LevelWorld world;
    private String levelName; // input file name for this stage
    private int enemyNum;
    private ArrayList< EnemyInfo > enemySchedule;
    private int time;
    private int appearedEnemyNum;
    private BattleStatus status;
    private BattleStatusChecker battleStatusChecker;
    // private Background background;
    private AllyConstructor allyConstructor;
    private EnemyConstructor enemyConstructor;
    public Level(String levelName, LevelWorld world, int enemyNum, ArrayList< EnemyInfo > enemySchedule, BattleStatusChecker battleStatusChecker, AllyConstructor allyConstructor, EnemyConstructor enemyConstructor){     // and enemy types
        time = 0;
        appearedEnemyNum = 0;
        status = BattleStatus.battleContinue;

        this.world = world;
        this.levelName = levelName;
        this.enemyNum = enemyNum;
        this.enemySchedule = enemySchedule;
        this.battleStatusChecker = battleStatusChecker;
        battleStatusChecker.setLevel(this);
        battleStatusChecker.setWorld(world);
        // this.background = background;

        this.allyConstructor = allyConstructor;
        this.enemyConstructor = enemyConstructor;
    }
    // public void setWorld(LevelWorld world){
    //     this.world = world;
    //     battleStatusChecker.setWorld(world);
    // }
    public void update(){
        time += 1;
        // to get the time of the game should be call by world
        // gameloop -> world.update -> stage.update
        for(int i = appearedEnemyNum; i < enemySchedule.size(); i++){
            EnemyInfo nextEnemy = enemySchedule.get(appearedEnemyNum);
            if( nextEnemy.getTime() == time){
                // an enemy should appear now
                // find correct enemy type and inject into world (add sprite)
                Enemy newEnemy = enemyConstructor.constructEnemy(nextEnemy.getType(), nextEnemy.getLane());
                world.addEnemy(newEnemy);
                appearedEnemyNum += 1;
            }
            else{
                break;
            }
        }
    }
    public BattleStatus checkBattleStatus(){
        return battleStatusChecker.checkBattleStatus();
    }
    public boolean allEnemyAppeared(){
        return enemyNum == appearedEnemyNum;
    }
    // public Background getBackground(){
    //     return background;
    // }
    
}
