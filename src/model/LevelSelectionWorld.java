package model;

import java.util.ArrayList;

import record.Record;
import selector.LevelSelector;

public class LevelSelectionWorld extends World{
    private final LevelSelector levelSelector = new LevelSelector(this, 9);
    private ArrayList< String > levelNames;
    // private int page;
    // private int maxPage;
    public LevelSelectionWorld(){
        super("Level Selection");
        nextWorldType = "Home";
        running = true;
        // page = 0;
        // maxPage = 0;
    }
    public boolean update(){
        return running;
    }
    public void selectLevel(String levelName){
        Record.setCurrentLevel(levelName);
        nextWorldType = "Level";
        running = false;
    }
    @Override
    public void reset(){
        super.reset();
        levelSelector.clear();
        levelNames = Record.getAvailableLevels();
        for(String levelName : levelNames){
            levelSelector.addSelection(levelName);
        }
    }
    public LevelSelector getSelector(){
        return levelSelector;
    }
    
}
