package record;

import java.util.ArrayList;

public class Record {
    ArrayList< String > allAllyTypes;
    ArrayList< String > usingAllyTypes;
    String highestLevelName;
    String nextLevelName;
    public Record(String highestLevelName){
        this.highestLevelName = highestLevelName;

    }
    public String getHighestLevelName(){
        return highestLevelName;
    }
}
