package model;

import java.util.ArrayList;

import record.Record;
import selector.LevelSelector;

public class LevelSelectionWorld extends World{
    private final LevelSelector levelSelector = new LevelSelector(5);
    private ArrayList< String > levelNames;
    private int page;
    private int maxPage;
    public LevelSelectionWorld(){
        super("Level Selection");
        nextWorldType = "Home";
        running = true;
        page = 0;
        maxPage = 0;
    }
    public boolean update(){
        return running;
    }
    public void selectLevel(String levelName){
        Record.setCurrentLevel(levelName);
        nextWorldType = "Level";
        running = false;
    }
    // public void nextPage(){
    //     if(page >= maxPage){ return; }
    //     page++;
    //     newPage();
    // }
    // public void previousPage(){
    //     if(page <= 0){ return; }
    //     page--;
    //     newPage();
    // }
    // public void newPage(){
    //     levelSelector.clear();
    //     // add levels
    // }
    // something set running to false
    public void reset(){
        // add selections to selector
        levelSelector.clear();
        // page = 0;
        // get available levels and set max page
        levelNames = Record.getAvailableLevels();
        for(String levelName : levelNames){
            levelSelector.addSelection(levelName);
        }
    }
    
}
