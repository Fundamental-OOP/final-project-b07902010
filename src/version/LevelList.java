package version;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class LevelList {
    private String battleTypeName;
    private ArrayList< String > levelNames;
    // private Dictionary< String, Integer > namesToLevel;
    public LevelList(String battleTypeName){
        this.battleTypeName = battleTypeName;
        levelNames = new ArrayList< String >();
        // namesToLevel = new Hashtable< String, Integer >();
    }
    public void addLevel(String levelName){
        levelNames.add(levelName);
        // namesToLevel.put(levelName, levelNames.size());
    }
    public String searchNextLevel(String levelName){
        int levelOrder = levelNames.indexOf(levelName);
        System.out.println("[LevelList] getting level name: " + levelName + " with order " + levelOrder);

        levelOrder++;
        if(levelOrder == levelNames.size()){
            return levelNames.get(levelOrder-1);
        }
        else{
            return levelNames.get(levelOrder);
        }
    }
    public int getLevelOrder(String levelName){
        return levelNames.indexOf(levelName);
    }
    public ArrayList< String > getAvailableLevels(String progress){
        ArrayList< String > ret = new ArrayList< String >();
        for(String levelName : levelNames){
            ret.add(levelName);
            if(levelName.equals(progress)){ break;}
        }
        return ret;
    }
    public String highestLevel(){
        return levelNames.get(levelNames.size()-1);
    }
}
