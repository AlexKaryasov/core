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
public class SpaceIndexOutOfBoundsException extends IndexOutOfBoundsException{
    private int number;
    public int getNumber(){
        return number;
    }
    public SpaceIndexOutOfBoundsException(String message, int number){
        super(message);
        this.number = number;
    }
}
