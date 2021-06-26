package level;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.World;
// import enemy
// defining a stage's condition

public class Level {
    private World world;
    private String levelName; // input file name for this stage
    private int enemyNum;
    private ArrayList< Integer > timeLine;
    private ArrayList< String > enemyUnitName; // timeline and enemyUnitName decide when and which enemy character should appear
    private int time;
    private int appearedEnemy;
    private BattleStatus status;
    private BattleStatusChecker battleStatusChecker;
    public Level(String levelName, World world, int enemyNum, ArrayList< Integer > timeLine, ArrayList< String > enemyUnitName, BattleStatusChecker battleStatusChecker){     // and enemy types
        time = 0;
        appearedEnemy = 0;
        status = BattleStatus.battleContinue;

        this.world = world;
        this.levelName = levelName;
        this.enemyNum = enemyNum;
        this.timeLine = timeLine;
        this.enemyUnitName = enemyUnitName;
        this.battleStatusChecker = battleStatusChecker;
    }
    public void update(){
        time += 1;
        // to get the time of the game should be call by world
        // gameloop -> world.update -> stage.update
        int nextAppearTime = timeLine.get(appearedEnemy);
        if( nextAppearTime == time){
            // an enemy should appear now
            String enemyType = enemyUnitName.get(appearedEnemy);
            // find correct enemy type and inject into world (add sprite)
        }
    }
    public BattleStatus checkBattleStatus(){
        return battleStatusChecker.checkBattleStatus();
    }
    
}
