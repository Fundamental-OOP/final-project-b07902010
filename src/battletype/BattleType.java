package battletype;

import model.LevelWorld;
import level.Level;

public abstract class BattleType{
    protected LevelWorld world;
    protected Level level;
    protected String name;
    public BattleType(String name){
        world = null;
        level = null;
        this.name = name;
    }
    public void setWorld(LevelWorld world){
        this.world = world;
    }
    public void setLevel(Level level){
        this.level = level;
    }
    public abstract BattleStatus checkBattleStatus();
    public String getName(){
        return name;
    }
}
