package level;

import model.LevelWorld;
import record.Record;
import unit.AllyConstructor;
import unit.EnemyConstructor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import battletype.BattleType;

public class LevelConstructor {
    private BattleType[] battleTypes;
    LevelWorld world;
    public LevelConstructor(BattleType[] battleTypes){
        this.battleTypes = battleTypes;
    }
    public void setWorld(LevelWorld world){
        this.world = world;
    }
    public Level constructLevel(){
        int enemyNum = -1;
        ArrayList< EnemyInfo > enemySchedule = new ArrayList< EnemyInfo >();
        String levelName = "";
        BattleType battleType = null;
        try{
            BufferedReader fr = new BufferedReader(new FileReader("../level_data/" + Record.getCurrentBattleTypeName() + "/" + Record.getCurrentLevel() + ".txt"));
            // first line will be level name
            levelName = fr.readLine();
            String[] infos = fr.readLine().split(":");
            String battleTypeName = infos[0];
            enemyNum = Integer.parseInt(infos[1]);
            // second line will be battle type (define battle status checker) and enemy number

            if(!battleTypeName.equals(Record.getCurrentBattleTypeName())){
                System.out.println("[LevelContructor] Battle type not match:" + battleTypeName + "in file, " + Record.getCurrentBattleTypeName() + "in record.");
            }
            
            // third line will be background info
            // background

            for(BattleType Type : battleTypes){
                if(Type.getName().equals(battleTypeName)){
                    battleType = Type;
                }
            }
            if(battleType == null){
                System.out.println("Battle Type doesn't exist.");
                fr.close();
                return null;
            }
            // read in every evemy's type, appearing time, and lane
            for(int i = 0; i < enemyNum; i++){
                String line = fr.readLine();
                String[] enemyInfo = line.split(", ");
                enemySchedule.add(new EnemyInfo(enemyInfo[0], Integer.parseInt(enemyInfo[1]), Integer.parseInt(enemyInfo[2])));
            }
            fr.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File " + Record.getCurrentLevel() + " does not exist.");
        }
        catch(IOException e){
            System.out.println("Reach unexpected EOF.");
        }
        Level level = new Level(levelName, world, enemyNum, enemySchedule , battleType, new AllyConstructor(world), new EnemyConstructor(world));
        System.out.println("[LevelConstructor] " + Record.getCurrentBattleTypeName() + ":" + Record.getCurrentLevel() + " constructed.");
        return level;
    }
}
