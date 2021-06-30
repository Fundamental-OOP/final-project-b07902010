package model;

import record.Record;

public class LevelSelectionWorld extends World{
    boolean running;
    public LevelSelectionWorld(){
        super("Level Selection");
        nextWorldType = "Home";
        running = true;
    }
    public boolean update(){
        // select 
        return running;
    }
    public void selectLevel(String levelName){
        Record.setCurrentLevel(levelName);
    }
    // something set running to false
    public void reset(){
        // add selections to selector
    }
    
}
