package battletype;

import level.Level;
import model.LevelWorld;

public abstract class BattleStatusChecker {
    protected LevelWorld world;
    protected Level level;
    public BattleStatusChecker(){
        world = null;
        level = null;
    }
    public void setWorld(LevelWorld world){
        this.world = world;
    }
    public void setLevel(Level level){
        this.level = level;
    }
    public abstract BattleStatus checkBattleStatus();
    public abstract String getName();
}
