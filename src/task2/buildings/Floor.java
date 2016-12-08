/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2.buildings;

/**
 *
 * @author Александр
 * @param <T>
 */
public interface Floor extends Comparable<Floor>{

    int getNumberOfSpaces();
    //@Override
    void addSpaceByIndex(int number, Space office);

    //@Override
    void deleteSpaceByIndex(int number);

    //@Override
    Space[] getArrayOfSpaces();

    //@Override
    Space getBestSpace();

    //@Override
    Space getSpaceByIndex(int number);

    int getSumRooms();

    double getSumSquare();

    //@Override
    void setSpaceByIndex(int number, Space newOffice);
    
    public Object clone() throws CloneNotSupportedException;
    
    
//    public <T extends Floor> int compareTo(T floor);
    
}
