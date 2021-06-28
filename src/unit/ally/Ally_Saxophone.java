package unit.ally;
import model.*;
import graphics.*;

public class Ally_Saxophone extends Tank {
    public Ally_Saxophone(int posX, int posY, int lane, LevelWorld levelWorld) {
        super(300, posX, posY, lane, levelWorld, 50);
        this.walkRenderer = new AnimationRenderer("../img/ally/Saxophone", "walk");
        this.idleRenderer = new AnimationRenderer("../img/ally/Saxophone", "idle");
        this.attackRenderer = new AnimationRenderer("../img/ally/Saxophone", "attack");
        this.beAttackedRenderer = new AnimationRenderer("../img/ally/Saxophone", "beAttack");
        this.deadRenderer = new AnimationRenderer("../img/ally/Saxophone", "dead");
    }


}
