package version;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import battletype.BattleType;

public class GameVersion {
    private static final Dictionary< String, LevelList > levelLists = new Hashtable< String, LevelList>();
    // private static final Set< String > initializedBattleTypes = new HashSet< String >();
    public static boolean loadVersion(BattleType[] battleTypes){
        String levelListPath = "./levelList.txt";
        String checkPoint = "";
        // construct levelList
        // get every battle types' name
        for(BattleType type : battleTypes){
            // availableBattleTypeNames.add(type.getName());
            // initializedBattleTypes.add(type.getName());
            levelLists.put(type.getName(), new LevelList(type.getName()));
        }
        try{
            BufferedReader fr = new BufferedReader(new FileReader(levelListPath));
            
            // types of battle
            checkPoint = fr.readLine();
            if(!checkPoint.equals("Battle Types")){
                System.out.println("[Version] Version file format error : Battle Types.");
                fr.close();
                return false;
            }
            int battletypesNum = Integer.parseInt(fr.readLine());

            for(int i = 0; i < battletypesNum; i++){
                String battleTypeName = fr.readLine();
                LevelList levelList = levelLists.get(battleTypeName);
                if(levelList != null){
                    int levelNum = Integer.parseInt(fr.readLine());  // how many level does this type has
                    for(int j = 0; j < levelNum; j++){
                        // read in level names
                        levelList.addLevel(fr.readLine());
                    }
                }
                else{
                    System.out.println("[Version] Battle type " + battleTypeName + " not supported, stop game.");
                    // int levelNum = Integer.parseInt(fr.readLine());
                    // for(int j = 0; j < levelNum; j++){ fr.readLine(); }
                    fr.close();
                    return false;
                }
            }            
            fr.close();
        }
        catch(FileNotFoundException e){
            System.out.println("[Version] File " + levelListPath + " does not exist.");
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
}
