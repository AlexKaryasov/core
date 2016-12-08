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
public class SquareComparator implements Comparator<Floor>{
    @Override
    public int compare(Floor o1, Floor o2){
        if (o1.getSumSquare() > o2.getSumSquare()) return -1;
        if (o1.getSumSquare() < o2.getSumSquare()) return 1;
        return 0;
    }
}
