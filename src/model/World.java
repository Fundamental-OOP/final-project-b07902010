package model;

import java.awt.*;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


import graphics.*;
import unit.*;

import selector.*;
import level.*;

import game.Canvas;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;

public abstract class World {
    protected final List<Renderee> renderees = new CopyOnWriteArrayList< Renderee >();     // not sure
    protected String nextWorldType;
    protected String myWorldType;
    protected Selector selector;

    // protected Background background;
    // String endingStatus;
    public World(String myWorldType) {
        nextWorldType = "None";
        this.myWorldType = myWorldType;
    }
    public abstract boolean update();
    // public setBackground(Background background){
    //     this.background = background;
    // }
    public void addRenderee(Renderee r) {
        renderees.add(r);
    }
    public void removeRenderee(Renderee r) {
        renderees.remove(r);
    }
    public List< Renderee > getRenderees() {
        return renderees;
    }
    public abstract void resetWorld();
    public String getNextWorldType(){
        return nextWorldType;
    }
    public String getMyWorldType(){
        return myWorldType;
    }
}