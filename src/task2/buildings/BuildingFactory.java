/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2.buildings;

/**
 *
 * @author Александр
 */
public interface BuildingFactory {
    
public Space createSpace(double area);

public Space createSpace(int roomsCount, double area);

public Floor createFloor(int spacesCount);

public Floor createFloor(Space[] spaces);

public Building createBuilding(int floorsCount, int[] spacesCounts);

Building createBuilding(Floor[] floors);
    
}
