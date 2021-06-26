package battletype;

import model.LevelWorld;

public class NormalBattleStatusChecker extends BattleStatusChecker{
    public NormalBattleStatusChecker(){
        super();
    }
    public BattleStatus checkBattleStatus(){
        if(world.getCastle().getHP() <= 0){
            return BattleStatus.lose;
        }
        if(level.allEnemyAppeared() && world.getEnemies().size() == 0){
            return BattleStatus.win;
        }
        return BattleStatus.battleContinue;
    }
    public String getName(){
        return "Normal";
    }
}
