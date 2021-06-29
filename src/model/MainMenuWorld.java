package model;


public class MainMenuWorld extends World{
    private boolean selected = false;
    public MainMenuWorld(){
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
