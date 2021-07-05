package model;


public class HomeWorld extends World{
    public HomeWorld(){
        super("Home");
    }
    public boolean update(){
        return running;
    }
}