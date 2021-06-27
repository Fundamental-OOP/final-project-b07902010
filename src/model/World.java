package model;

import java.awt.*;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


import graphics.*;
import unit.*;


import level.*;


import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;

public abstract class World {
    protected final List renderees = new CopyOnWriteArrayList< Renderee >();     // not sure

    public World() {
        
    }
    public abstract boolean update();

    public void addRenderee(Renderee r){
        renderees.add(r);
    }
    public void removeRenderee(Renderee r){
        renderees.remove(r);
    }
    
    public List< Renderee > getRenderees(){
        return renderees;
    }
}