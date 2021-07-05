package unit.enemy;

import unit.State;
import utils.UnitImage;
import model.*;

public class Enemy_DSA extends Enemy {
    private static int moveCycle = 100;
    private int moveCycleCnt;
    private static int deaddelay = UnitImage.getUnitAnimation("DSA", "dead").size();;
    private static int atkCycle = 20;
    private static int atkDelay = UnitImage.getUnitAnimation("DSA", "attack").size();
    public Enemy_DSA (int posX, int posY, int lane, LevelWorld levelWorld ) {
        super("DSA", 80, 20, posX, posY, lane, deaddelay, atkCycle, atkDelay, levelWorld, 5);
    }
    @Override
    public void update(){
        super.update();
        if(state == State.Walk){
            moveCycleCnt = (moveCycleCnt+1) % moveCycle;
            if(moveCycleCnt == 0){
                int move;
                if(lane > 0 && lane < 4){
                    int random = (int) Math.round((Math.random()));
                    if(random == 1){ move = 1; }
                    else{ move = -1; }
                }
                else if(lane == 0){ move = 1; }
                else if(lane == 4){ move = -1; }
                else{ move = 0; }
                lane += move;
                posY += move * 120;
            }
        }
        else{
            moveCycleCnt = 0;
        }
    }
}