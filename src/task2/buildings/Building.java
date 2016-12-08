
package task2.buildings;

import java.util.Iterator;

/**
 *
 * @author Александр
 */
public interface Building {

    void addSpaceByIndex(int number, Space space);

    void deleteSpaceByIndex(int number);

    Space getBestSpace();

    Floor[] getArrayOfFloors();

    Floor getFloorByIndex(int number);

    int getSumRooms();

    double getSumSquare();

    void setFloorByIndex(Floor floor, int number);

    void sort(Space[] offices);
    
    int getNumberOfFloors();
    
    int getNumberOfSpaces();
    
    Space getSpaceByIndex(int number);
    
    void setSpaceByIndex(int number, Space space);
    
    public Object clone() throws CloneNotSupportedException;
    
    public Iterator iterator();
    
}
