package model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


import graphics.*;

public abstract class World {
    protected final List<Renderee> renderees = new CopyOnWriteArrayList< Renderee >();     // not sure
    protected String nextWorldType;
    protected String myWorldType;
    private int updateInterval = 3;
    private int updateCnt;
    // protected Selector selector;

    // protected Background background;
    // String endingStatus;
    public World(String myWorldType) {
        nextWorldType = "None";
        this.myWorldType = myWorldType;
        updateCnt = 0;
    }
    public boolean updateCount(){
        if(++updateCnt == updateInterval){
            return update();
        }
        else{
            return true;
        }
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
    public void resetWorld(){
        updateCnt = 0;
        reset();
    }
    public abstract void reset();
    public String getNextWorldType(){
        return nextWorldType;
    }
    public String getMyWorldType(){
        return myWorldType;
    }
}