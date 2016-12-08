/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2.buildings;

import java.util.Comparator;

/**
 *
 * @author Александр
 */
public class RoomsComparator implements Comparator<Building>{
    @Override
    public  int compare(Building o1, Building o2){
        if (o1.getSumRooms() > o2.getSumRooms()) return -1;
        if (o1.getSumRooms() < o2.getSumRooms()) return 1;
        return 0;
    }
}
