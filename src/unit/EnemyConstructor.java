package unit;

import model.LevelWorld;
import unit.enemy.*;

public class EnemyConstructor {
    LevelWorld world;
    public EnemyConstructor(LevelWorld world){
        this.world = world;
    }
    public Enemy constructEnemy(String enemyType, int lane) {      // and other attributes
        Enemy newEnemy = null;
        switch(enemyType){
            case "FOOP":
                newEnemy = new Enemy_FOOP(1340, 160 + 120 * lane, lane, world);
                break;
            case "CNLab":
                newEnemy = new Enemy_CNLab(1340, 160 + 120 * lane, lane, world);
                break;
            case "ADA":
                newEnemy = new Enemy_ADA(1340, 160 + 120 * lane, lane, world);
                break;
            case "DSA":
                newEnemy = new Enemy_DSA(1340, 160 + 120 * lane, lane, world);
                break;
            default:
                System.out.println("[EnemyConstructor] Enemy type " + enemyType + " not found.");
        }
        return newEnemy;
    }
}
