package version;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import battletype.BattleType;

public class GameVersion {
    private static final Dictionary< String, LevelList > levelLists = new Hashtable< String, LevelList>();
    private static int maxLevelNum; // for one type of battle
    // private static final Set< String > initializedBattleTypes = new HashSet< String >();
    public static boolean loadVersion(BattleType[] battleTypes, String versionName){
        maxLevelNum = 0;
        String versionPath = "../versions/";
        String[] infos;
        // construct levelList
        // get every battle types' name
        for(BattleType type : battleTypes){
            // availableBattleTypeNames.add(type.getName());
            // initializedBattleTypes.add(type.getName());
            levelLists.put(type.getName(), new LevelList(type.getName()));
        }
        try{
            BufferedReader fr = new BufferedReader(new FileReader(versionPath + versionName + ".txt"));
            
            // types of battle
            infos = fr.readLine().split(":");
            
            if(!infos[0].equals("Battle Types")){
                System.out.println("[Version] Version file format error : Battle Types.");
                fr.close();
                return false;
            }
            int battleTypesNum = Integer.parseInt(infos[1]);
            for(int i = 0; i < battleTypesNum; i++){
                infos = fr.readLine().split(":");
                String battleTypeName = infos[0];
                LevelList levelList = levelLists.get(battleTypeName);
                if(levelList != null){
                    int levelNum = infos.length;
                    for(int j = 1; j < levelNum; j++){
                        // read in level names
                        levelList.addLevel(infos[j]);
                    }
                }
                else{
                    System.out.println("[Version] Battle type " + battleTypeName + " not supported, stop game.");
                    fr.close();
                    return false;
                }
            }
            fr.close();
        }
        catch(FileNotFoundException e){
            System.out.println("[Version] File " + versionPath + versionName + ".txt"+ " does not exist.");
            return false;
        }
        catch(IOException e){
            System.out.println("[Version] Reach unexpected EOF.");
            return false;
        }
        return true;
    }
    public static boolean supportBattleType(String battleTypeName){
        if(levelLists.get(battleTypeName) == null) { return false;}
        return true;
    }
    public static String nextLevel(String battleTypeName, String levelName){
        LevelList levelList = levelLists.get(battleTypeName);
        return levelList.searchNextLevel(levelName);
    }
    public static String getHigherLevelOf(String battleTypeName, String levelName1, String levelName2){
        LevelList levelList = levelLists.get(battleTypeName);
        int levelOrder1 = levelList.getLevelOrder(levelName1);
        int levelOrder2 = levelList.getLevelOrder(levelName2);
        if(levelOrder1 >= levelOrder2){ return levelName1; }
        else{ return levelName2; }
    }
    public static ArrayList< String > getAvailableLevels(String battleTypeName, String levelName){
        LevelList levelList = levelLists.get(battleTypeName);
        return levelList.getAvailableLevels(levelName);
    }
    public static int getMaxLevelNum(){ return maxLevelNum; }
    public static String getHighestAvailableLevel(String battleTypeName, String checkLevelName){
        LevelList levelList = levelLists.get(battleTypeName);
        return levelList.getHighestAvailableLevel(checkLevelName);
    }
}
