package bullet;

import model.*;
import graphics.*;

public class Bullet_MiMiMaoMao extends Bullet {
    public Bullet_MiMiMaoMao(int posX, int posY, int lane, LevelWorld levelWorld) {
        super(10, posX, posY,30, lane, levelWorld);    // 寫死
        this.renderer = new AnimationRenderer("./img/bullet/MiMiMaoMao", "sprite");
    }

}
