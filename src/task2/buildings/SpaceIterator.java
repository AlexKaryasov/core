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
public class SpaceIterator implements Iterator<Space>{
    private Floor floor;
    private int index = 0;
    public SpaceIterator(Floor floor){
        this.floor = floor;
    }
    @Override
    public boolean hasNext(){
        return index != floor.getNumberOfSpaces() - 1;
    }
    @Override
    public Space next(){
        return floor.getSpaceByIndex(++index);
    }
}
