/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2.buildings;

import task2.buildings.office.Office;
import task2.buildings.office.OfficeBuilding;
import task2.buildings.office.OfficeFloor;

/**
 *
 * @author Александр
 */
public class OfficeFactory implements BuildingFactory{
    
    @Override
    public Space createSpace(double area){
        return new Office(area);
    }
    
    @Override
    public Space createSpace(int rooms, double area){
        return new Office(area, rooms);
    }
    
    @Override
    public Floor createFloor(int spaces){
        return (Floor)new OfficeFloor(spaces);
    }
    
    @Override
    public Floor createFloor(Space[] spaces){
        return (Floor)new OfficeFloor(spaces);
    }
    
    @Override
    public Building createBuilding(int floorsCount, int[] spacesCount){
        return new OfficeBuilding(floorsCount, spacesCount);
    }
    
    @Override
    public Building createBuilding(Floor[] floors){
        return new OfficeBuilding(floors);
    }
}
