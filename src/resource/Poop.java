package resource;

public class Poop {
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
}
