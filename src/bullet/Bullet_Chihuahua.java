package bullet;

import unit.ally.Ally_Chihuahua;
import unit.enemy.Enemy;
import model.LevelWorld;


public class Bullet_Chihuahua extends Bullet{
    private static int maxDist = 200;
    private int dist;
    private double deBuff = 0.8;
    private int deBuffDuration = 100;
    public Bullet_Chihuahua(int ATK, int posX, int posY, int lane,  int speed, LevelWorld levelWorld, Ally_Chihuahua shooter, double deBuff, int deBuffDuration) {
        super("Chihuahua", ATK, posX, posY, speed, lane, levelWorld, shooter);    // 寫死
        dist = 0;
        this.deBuff = deBuff;
        this.deBuffDuration = deBuffDuration;
    }
    @Override
    public void update() {
        super.update();
        dist += dx;
        if(dist >= maxDist){ levelWorld.removeBullet(this); }
    }
    @Override
    protected void damage(Enemy e) {
        super.damage(e);
        // e.setDx(e.getDx()/2 + 1, 3);
        int newATK = (int) Math.round(e.getATK() * deBuff);
        e.setATK(newATK , deBuffDuration);
    }
}
