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
public class InvalidSpaceAreaException extends IllegalArgumentException{
    private double square;
    public double getSquare(){
        return square;
    }
    public InvalidSpaceAreaException(String message, double square){
        super(message);
        this.square = square;
    }
}
