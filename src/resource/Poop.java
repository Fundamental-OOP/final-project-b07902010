package resource;

public class Poop {
    int count;
    public Poop(){
        count = 0;
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
}
