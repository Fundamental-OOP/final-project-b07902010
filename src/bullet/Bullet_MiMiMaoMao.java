package bullet;

import model.*;
import unit.ally.Ally_MiMiMaoMao;

public class Bullet_MiMiMaoMao extends Bullet {
    
    public Bullet_MiMiMaoMao(int ATK, int posX, int posY, int lane, int speed, LevelWorld levelWorld, Ally_MiMiMaoMao shooter) {
        super("MiMiMaoMao", ATK, posX, posY, speed, lane, levelWorld, shooter);    // 寫死
    }
}
