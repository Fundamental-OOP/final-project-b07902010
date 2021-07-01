package bullet;

import unit.enemy.Enemy;
import unit.Unit;

import graphics.*;
import model.*;
import java.util.List;

public abstract class Bullet implements Renderee{
    protected int ATK;
    protected int posX, posY, dx;
    protected int lane;
    protected LevelWorld levelWorld;
    protected ImageRenderer renderer;

    public Bullet (String Name, int ATK, int posX, int posY, int dx, int lane, LevelWorld levelWorld) {
        this.ATK = ATK;
        this.posX = posX;
        this.posY = posY;
        this.lane = lane;
        this.dx = dx;
        this.levelWorld = levelWorld;
        renderer =  new ImageRenderer("../img/bullet/" + Name +"/sprite_0.png");
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
        else
            posX += dx;
    }

    protected boolean touch( Unit u )  {  // TODO: set diff
        if(u.getLane() == lane){
            return Math.abs(u.getPosX() - this.posX) < 20;
        }
        return false;
    }

    protected void damage(Enemy e) {
        int newHP = e.getHP() - ATK;
        e.setHP(Math.max(newHP, 0));
    }

    public Renderer getRenderer() {
        renderer.setPosition(posX, posY);
        return renderer;  /* 如果會報錯: return (Renderer) renderer; */
    }
}
