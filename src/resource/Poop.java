package resource;

import java.awt.Graphics;

import graphics.*;
import utils.*;

public class Poop implements Renderee {
    
    PoopRenderer poopRenderer = new PoopRenderer(100, 800);

    int count;
    
    int miningCycle;
    int miningCycleCnt;
    int miningAmount;

    public Poop(int miningCycle, int initCnt, int miningAmount){
        count = initCnt;
        this.miningCycle = miningCycle;
        miningCycleCnt = 0;
        this.miningAmount = miningAmount;
    }

    public int howMuchIHave(){
        return count;
    }
    public void pickUp(int pickUp){
        count += pickUp;
    }
    public boolean enough(int price){
        return count >= price;
    }
    public void Use(int price){
        count -= price;
    }
    
    public void update(){
        miningCycleCnt = (miningCycleCnt+1) % miningCycle;
        if(miningCycleCnt == 0){
            pickUp(miningAmount);
        }
        // System.out.println("[Poop] Updated, has " + count + " poops.");
    }

    @Override
    public Renderer getRenderer() {
        this.poopRenderer.setNumber(this.count);
        return poopRenderer;
    }
}

class PoopRenderer implements Renderer {

    private int number, x, y;
    private GameImage gameImage = new GameImage();

    public PoopRenderer(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void render(Graphics g) {
        int n = number, digits = 4;
        if (n >= 10000) n = 9999;
        if (n >= 1000) digits = 4;
        else if (n >= 100) digits = 3;
        else if (n >= 10) digits = 2;
        else if (n >= 0) digits = 1;
        else return;
        for (int i = 3; i >= 4-digits; i--) {
            g.drawImage(GameImage.getNumberImage(n%10), x+24*i, y, null);
            n /= 10;
        }
    }
}