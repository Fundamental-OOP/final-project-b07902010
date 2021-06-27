package unit.ally;

import unit.enemy.Enemy;
import unit.State;
import unit.Unit;
import model.*;
import java.util.List;

public abstract class Shooter extends Ally {
    public Shooter (int HP, int ATK, int posX, int posY, int lane, LevelWorld levelWorld, int cost) {
        super(HP, ATK, posX, posY, lane, levelWorld, cost);
    }

    public void update() {
        // dead
        if (this.HP <= 0 ) {
            this.setState(State.Dead);
            return;
        }

        // attack
        List<Enemy> enemies = this.levelWorld.getEnemies();
        boolean hasShoot = false;
        for (Enemy enemy : enemies){
            if ( this.aim(enemy) ) {
                this.shoot();
                hasShoot = true;
            }
        }
        if (hasShoot) {
            this.setState(State.Attack);
            return;
        }

        // idle
        else {
            this.setState(State.Idle);
            return;
        }

    }

    public boolean aim(Unit u) {
        return u instanceof Enemy && this.canSee((Enemy) u);
    }

    abstract public boolean canSee(Enemy e);
    abstract void shoot();

}
