package bullet;

import unit.enemy.Enemy;
import model.LevelWorld;


public class Bullet_Chihuahua extends Bullet{
    private static int maxDist = 200;
    private int dist;
    public Bullet_Chihuahua(int ATK, int posX, int posY, int lane,  int speed, LevelWorld levelWorld) {
        super("Chihwahwa", ATK, posX, posY, speed, lane, levelWorld);    // 寫死
        dist = 0;
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
        e.setDx(0, 1);
    }
}
