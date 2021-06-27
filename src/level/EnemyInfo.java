package level;

public class EnemyInfo {
    String enemyType;
    int appearTime;
    int appearLane;
    public EnemyInfo(String enemyType, int appearTime, int appearLane){
        this.enemyType = enemyType;
        this.appearTime = appearTime;
        this.appearLane = appearLane;
    }
    public String getType(){
        return enemyType;
    }
    public int getTime(){
        return appearTime;
    }
    public int getLane(){
        return appearLane;
    }
}
