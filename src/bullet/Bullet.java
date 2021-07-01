package bullet;

import unit.enemy.Enemy;
import utils.UnitImage;
import unit.Unit;
import unit.ally.Shooter;
import graphics.*;
import model.*;
import java.util.List;

public abstract class Bullet implements Renderee{
    protected int ATK;
    protected int posX, posY, dx;
    protected int lane;
    protected LevelWorld levelWorld;
    protected ImageRenderer renderer;
    protected Shooter shooter;

    public Bullet (String Name, int ATK, int posX, int posY, int dx, int lane, LevelWorld levelWorld, Shooter shooter) {
        this.ATK = ATK;
        this.posX = posX;
        this.posY = posY;
        this.lane = lane;
        this.dx = dx;
        this.levelWorld = levelWorld;
        this.shooter = shooter;
        renderer =  new ImageRenderer(UnitImage.getBullet(Name));
    }

    public void setLevelWorld(LevelWorld levelWorld) {
        this.levelWorld = levelWorld;
    }

    public void update() {
        List<Enemy> enemies = this.levelWorld.getEnemies();
        boolean hit = false;
        for (Enemy enemy : enemies){
            if ( this.touch(enemy) ) {
                this.damage(enemy);
                hit = true;
            }
        }
        if (hit)
            levelWorld.removeBullet(this); // 應該 remove 掉之後就不會 call renderer 了吧
        else{
            posX += dx;
            if(posX > 1440){ levelWorld.removeBullet(this); }
        }
    }

    protected boolean touch( Unit u )  {  // TODO: set diff
        if(u.getLane() == lane){
            return Math.abs(u.getPosX() - this.posX) < 50;
        }
        return false;
    }

    protected void damage(Enemy e) {
        e.damaged(shooter, ATK);
    }

    public Renderer getRenderer() {
        renderer.setPosition(posX, posY);
        return renderer;  /* 如果會報錯: return (Renderer) renderer; */
    }
}
