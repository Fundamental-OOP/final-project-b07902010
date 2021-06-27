package unit;

import model.LevelWorld;

public class EnemyConstructor {
    LevelWorld world;
    public EnemyConstructor(LevelWorld world){
        this.world = world;
    }
    public Enemy constructEnemy(String enemyType, int lane){      // and other attributes
        Enemy newEnemy = null;
        switch(enemyType){
            case "FOOP":
                newEnemy = new Enemy_FOOP();
                break;
            case "CNlab":
                break;
            default:
                System.out.println("Enemy type " + enemyType + " not found.");
        }
        return newEnemy;
    }
}
