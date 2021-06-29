package level;

import model.LevelWorld;
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
    public Level constructLevel(String levelName){
        int enemyNum = -1;
        ArrayList< EnemyInfo > enemySchedule = new ArrayList< EnemyInfo >();
        BattleType battleType = null;
        try{
            BufferedReader fr = new BufferedReader(new FileReader("../level_data/" + levelName + ".txt"));
            // first line will be enemy number
            enemyNum = Integer.parseInt(fr.readLine());
            // second line will be battle type (define battle status checker)


            String battleTypeName = fr.readLine();
            
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
            System.out.println("File " + levelName + " does not exist.");
        }
        catch(IOException e){
            System.out.println("Reach unexpected EOF.");
        }
        Level level = new Level(levelName, world, enemyNum, enemySchedule , battleType, new AllyConstructor(world), new EnemyConstructor(world));
        return level;
    }
}
