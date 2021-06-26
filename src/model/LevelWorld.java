package model;

import level.BattleStatus;
import level.Level;




public class LevelWorld extends World{
    private Level level;
    public LevelWorld(Sprite... sprites){
        super(sprites);
    }
    @Override
    public boolean update() {
        level.update();
        for (Sprite sprite : sprites) {
            sprite.update();
        }
        return checkGameOver();
    }
    public void setLevel(Level level){
        this.level = level;
    }
    private BattleStatus checkBattleStatus(){
        return level.checkBattleStatus();
    }
    public boolean checkGameOver(){     // return running or not
        if(checkBattleStatus() == BattleStatus.battleContinue){
            return true;
        }
        else{
            return false;
        }
    }

}
