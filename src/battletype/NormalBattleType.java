package battletype;

public class NormalBattleType extends BattleType{
    public NormalBattleType(){
        super("Normal");
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
}
