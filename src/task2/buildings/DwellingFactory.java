/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2.buildings;

import task2.buildings.dwelling.Dwelling;
import task2.buildings.dwelling.DwellingFloor;
import task2.buildings.dwelling.Flat;

/**
 *
 * @author Александр
 */
public class DwellingFactory implements BuildingFactory{
    
    @Override
    public Space createSpace(double area){
        return new Flat(area);
    }
    
    @Override
    public Space createSpace(int rooms, double area){
        return new Flat(area, rooms);
    }
    
    @Override
    public Floor createFloor(int spaces){
        return (Floor) new DwellingFloor(spaces);
    }
    
    @Override
    public Floor createFloor(Space[] spaces){
        return (Floor) new DwellingFloor(spaces);
    }
    
    @Override
    public Building createBuilding(int floorsCount, int[] spacesCount){
        return new Dwelling(floorsCount, spacesCount);
    }
    
    @Override
    public Building createBuilding(Floor[] floors){
        return new Dwelling(floors);
    }
}
