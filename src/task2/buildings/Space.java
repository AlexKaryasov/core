/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2.buildings;

/**
 *
 * @author Александр
 * 
 */
public interface Space extends Comparable<Space>{
    
    
    public int getRooms();

    public double getSquare();

    public void setRooms(int rooms);

    public void setSquare(double square);
    
    public Object clone() throws CloneNotSupportedException;
    
}
