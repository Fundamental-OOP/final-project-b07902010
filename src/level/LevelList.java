package level;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class LevelList {
    private String battleType;
    private ArrayList< String > levelNames;
    private Dictionary< String, Integer > namesToLevel;
    public LevelList(String battleType){
        this.battleType = battleType;
        levelNames = new ArrayList< String >();
        namesToLevel = new Hashtable< String, Integer >();
    }
    public void addLevel(String levelName){
        levelNames.add(levelName);
        namesToLevel.put(levelName, levelNames.size());
    }
    public String searchNextLevel(String levelName){
        int levelOrder = namesToLevel.get(levelName);
        if(levelOrder < levelNames.size()){
            return levelNames.get(levelOrder);
        }
        else{
            return null;
        }
    }
}
