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
public class InvalidRoomsCountException extends IllegalArgumentException{
    private int rooms;
    public int getRooms(){
        return rooms;
    }
    public InvalidRoomsCountException(String message, int rooms){
        super(message);
        this.rooms = rooms;
    }
}
