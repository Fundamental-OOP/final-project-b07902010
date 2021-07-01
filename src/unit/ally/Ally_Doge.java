package unit.ally;

import model.LevelWorld;
import unit.Unit;

public class Ally_Doge extends Tank{
    static private int hp = 400;
    static private int cost = 200;
    static private int deaddelay = 20;
    static private double counterAttackRatio = 0.05;
    public Ally_Doge(int posX, int posY, int lane, int column, LevelWorld levelWorld){
        super("Doge", hp, posX, posY, lane, column, deaddelay, levelWorld, cost);
    }
    public static int getCost(){ return Ally_Doge.cost; }
    @Override
    public void damaged(Unit attacker, int dmg) {
        super.damaged(attacker, dmg);
        int counterDmg = (int) Math.round(dmg * counterAttackRatio);
        attacker.damaged(this, counterDmg);
    }
}
