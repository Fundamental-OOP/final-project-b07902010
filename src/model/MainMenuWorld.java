package model;

import selector.*;

public class MainMenuWorld extends World{
    public MainMenuWorld(){
        super("MainMenu");
    }
    public boolean update(){
        // if selector is triggered check selector and decide which world to go to (setNextWorld)
        // and return false to stop this world
        return true;
    }
    public void reset(){

    }
}
