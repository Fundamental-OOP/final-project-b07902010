package battletype;

import model.LevelWorld;

public class NormalBattleStatusChecker extends BattleStatusChecker{
    public NormalBattleStatusChecker(){
        super();
    }
    public BattleStatus checkBattleStatus(){
        if(world.getCastle().getHP() <= 0){
            System.out.println("lose");
            return BattleStatus.lose;
        }
        if(level.allEnemyAppeared() && world.getEnemies().size() == 0){
            System.out.println("win");
            return BattleStatus.win;
        }
        return BattleStatus.battleContinue;
    }
    public String getName(){
        return "Normal";
    }
}
