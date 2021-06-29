package model;


public class HomeWorld extends World{
    private boolean selected = false;
    public HomeWorld(){
        super("MainMenu");
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
