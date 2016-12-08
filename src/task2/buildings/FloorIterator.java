/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2.buildings;

import java.util.Iterator;

/**
 *
 * @author Александр
 */
public class FloorIterator implements Iterator<Floor>{
    private int index = -1;
    private Building building;
    public FloorIterator(Building building){
        this.building = building;
    }
    @Override
    public boolean hasNext(){
        return index < building.getNumberOfFloors()-1;
    }
    @Override
    public Floor next(){
        return building.getFloorByIndex(++index);
    }
}
