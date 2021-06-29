package model;


public class HomeWorld extends World{
    private boolean selected = false;
    public HomeWorld(){
        super("Home");
    }
    public boolean update(){
        return !selected;
    }
    public void setNextWorld(String nextWorld){
        this.nextWorldType = nextWorld;
        selected = true;
    }
    public void reset(){

    }
}
