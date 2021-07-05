package bullet;

import model.LevelWorld;
import unit.ally.Ally_RainbowCat;

public class Bullet_RainbowCat extends Bullet{
    public Bullet_RainbowCat(int ATK, int posX, int posY, int lane,  int speed, LevelWorld levelWorld, Ally_RainbowCat shooter) {
        super("RainbowCat", ATK, posX, posY, speed, lane, levelWorld, shooter);
    }
}
