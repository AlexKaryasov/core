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
public class PlacementExchanger {
    public static boolean spaceExchangeIsPossible(Space s1, Space s2){
        return (s1.getRooms() == s2.getRooms()) && (s1.getSquare() == s2.getSquare());
    }
    public static boolean floorExchangeIsPossible(Floor f1, Floor f2){
        return (f1.getSumRooms() == f2.getSumRooms()) && f1.getSumSquare() == f2.getSumSquare();
    }
    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2) throws InexchangeableSpacesException{
        if (!spaceExchangeIsPossible(floor1.getSpaceByIndex(index1), floor2.getSpaceByIndex(index2)))
                throw new InexchangeableSpacesException("Нельзя поменяться!");
        Space temp = floor1.getSpaceByIndex(index1);
        floor1.setSpaceByIndex(index1, floor2.getSpaceByIndex(index2));
        floor2.setSpaceByIndex(index2, temp);        
    }
    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2) throws InexchangeableFloorException{
        if (!floorExchangeIsPossible(building1.getFloorByIndex(index1), building2.getFloorByIndex(index2)))
            throw new InexchangeableFloorException("Нельзя поменяться");
        Floor temp = building1.getFloorByIndex(index1);
        building1.setFloorByIndex(building2.getFloorByIndex(index2), index1);
        building2.setFloorByIndex(temp, index2);
    }
}
