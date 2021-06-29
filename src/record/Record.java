package record;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Record {
    private final ArrayList< String > enableAllyTypes = new ArrayList< String >();
    private final ArrayList< String > chosenAllyTypes = new ArrayList< String >();
    private final Dictionary< String, String > levelProgresses = new Hashtable<String, String>();   // <battle type, highest level>
    private final 
    // private final ArrayList< String > battleTypes = new ArrayList< String >();
    String currentBattleType;
    public Record(){
    }
    public void setLevelProgress(String battleType, String levelName){

    }
    public String getHighestLevelName(String battleType){
        return levelProgresses.get(battleType);
    }
    public ArrayList< String > getChosenAllyTypes(){
        ArrayList< String > ret = new ArrayList< String >();
        for(String Type : chosenAllyTypes){
            ret.add(Type);
        }
        return ret;
    }
    public ArrayList< String > getEnableAllyTypes(){
        ArrayList< String > ret = new ArrayList< String >();
        for(String Type : enableAllyTypes){
            ret.add(Type);
        }
        return ret;
    }
    public void addEnableAllyTypes(String type){
        enableAllyTypes.add(type);
    }

}
