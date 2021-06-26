package level;

import model.World;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LevelConstructor {
    BattleStatusChecker[] battleTypes;
    public LevelConstructor(BattleStatusChecker[] battleTypes){
        this.battleTypes = battleTypes;
    }
    public Level constructLevel(String levelName, World world){
        int enemyNum;
        ArrayList< Integer > timeLine = new ArrayList< Integer >();
        ArrayList< String > enemyUnitName = new ArrayList< String >();
        ArrayList< Integer > enemyLane = new ArrayList< Integer >();
        BattleStatusChecker battleStatusChecker = null;
        try{
            BufferedReader fr = new BufferedReader(new FileReader(levelName + ".txt"));
            // first line will be enemy number
            enemyNum = Integer.parseInt(fr.readLine());
            // second line will be battle type (define battle status checker)
            String battleTypeName = fr.readLine();
            for(BattleStatusChecker battleType : battleTypes){
                if(battleType.getName().equals(battleTypeName)){
                    battleStatusChecker = battleType;
                }
            }
            if(battleStatusChecker == null){
                System.out.println("Battle Type doesn't exist.");
                return null;
            }
            // read in every evemy's type, appearing time, and lane
            for(int i = 0; i < enemyNum; i++){
                String line = fr.readLine();
                String[] enemyInfo = line.split(",");
                timeLine.add(Integer.parseInt(enemyInfo[0]));
                enemyUnitName.add(enemyInfo[1]);
                enemyLane.add(Integer.parseInt(enemyInfo[2]));
            }
            fr.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File " + levelName + " does not exist.");
        }
        catch(IOException e){
            System.out.println("Reach unexpected EOF.");
        }
        Level level = new Level(levelName, world, enemyNum, timeLine, enemyUnitName, battleStatusChecker);
        return level;
    }
}
