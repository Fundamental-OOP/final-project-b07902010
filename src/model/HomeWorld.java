package model;


public class HomeWorld extends World{
    // private boolean selected = false;
    public HomeWorld(){
        super("Home");
    }
    public boolean update(){
        return running;
    }
    
    @Override
    public void setNextWorld(String nextWorld){
        this.nextWorldType = nextWorld;
        running = false;
    }
    public void reset(){
        running = true;
    }
}