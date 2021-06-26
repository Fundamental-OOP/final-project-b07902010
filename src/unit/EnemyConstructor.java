package unit;

public class EnemyConstructor {
    public EnemyConstructor(){

    }
    public Unit constructEnemy(String enemyType){      // and other attributes
        Unit newEnemy = null;
        switch(enemyType){
            case "ADA":
                newEnemy = new ADA();
                break;
            case "CNlab":
                break;
            default:
                System.out.println("Enemy type " + enemyType + " not found.");
        }
        return newEnemy;
    }
}
