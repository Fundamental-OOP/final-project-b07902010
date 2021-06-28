package level;

import java.util.ArrayList;

public class LevelList {
    private String battleType;
    private ArrayList< String > levelNames;
    public LevelList(String battleType){
        this.battleType = battleType;
        levelNames = new ArrayList< String >();
    }
    public void addLevel(String levelName){
        levelNames.add(levelName);
    }
    public String searchNextLevel(String levelName){
        int levelOrder = levelNames.indexOf(levelName);
        if(levelOrder < levelNames.size()){
            return levelNames.get(levelOrder);
        }
        else{
            return null;
        }
    }
}
