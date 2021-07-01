package unit.ally;
import unit.State;
import utils.UnitImage;
import model.*;

public class Ally_Pooper extends Ally {
    private static int hp = 50;
    private static int atk = 0;
    private static int deaddelay = UnitImage.getUnitAnimation("Pooper", "Dead").size();
    private static int cost = 50;
    private static int poopCycle = 100;
    private static int poopAmount = 25;
    private int poopCycleCnt = 0;
    public Ally_Pooper(int posX, int posY, int lane, int column, LevelWorld levelWorld) {
        super("Pooper", hp, atk, posX, posY, lane, column, deaddelay, 0, 0, levelWorld, cost);
    }
    @Override
    public void update() {
        super.update();
        if(HP <= 0){ state = State.Dead; }
        switch(state){
            case Idle:
                poopCycleCnt = (poopCycleCnt+1) % poopCycle;
                if (poopCycleCnt == 0) {
                    levelWorld.getPoop().pickUp(poopAmount);
                }
                break;
            case Dead:
                if (deadCountDown == deadDelay){
                    levelWorld.moveAllyToGraveYard(this);
                    deadCountDown--;
                }
                else if (deadCountDown <= 0) {
                    levelWorld.reallyKillAlly(this);
                }
                else{
                    deadCountDown--;
                }
                break;
            default:
                state = State.Idle;
                break;
        }
    }
    public static int getCost(){ return Ally_Pooper.cost; }
}
