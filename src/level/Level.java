package level;

import java.util.ArrayList;

import battletype.BattleStatus;
import battletype.BattleType;
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
    private BattleType battleType;
    // private Background background;
    public Level(String levelName, LevelWorld world, int enemyNum, ArrayList< EnemyInfo > enemySchedule, BattleType battleType, AllyConstructor allyConstructor, EnemyConstructor enemyConstructor){     // and enemy types
        time = 0;
        appearedEnemyNum = 0;
        status = BattleStatus.battleContinue;

        this.world = world;
        this.levelName = levelName;
        this.enemyNum = enemyNum;
        this.enemySchedule = enemySchedule;
        this.battleType = battleType;
        battleType.setLevel(this);
        battleType.setWorld(world);
        // this.background = background;
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
                
                world.addEnemy(nextEnemy.getType(), nextEnemy.getLane());
                appearedEnemyNum += 1;
            }
            else{
                break;
            }
        }
    }
    public BattleStatus checkBattleStatus(){
        return battleType.checkBattleStatus();
    }
    public boolean allEnemyAppeared(){
        return enemyNum == appearedEnemyNum;
    }
    // public Background getBackground(){
    //     return background;
    // }
    
}
