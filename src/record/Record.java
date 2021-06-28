package record;

import java.util.ArrayList;

public class Record {
    ArrayList< String > enableAllyTypes;
    ArrayList< String > chosenAllyTypes;
    
    String highestLevelName;
    String nextLevelName;
    public Record(String highestLevelName){
        this.highestLevelName = highestLevelName;

    }
    public String getHighestLevelName(){
        return highestLevelName;
    }
}
