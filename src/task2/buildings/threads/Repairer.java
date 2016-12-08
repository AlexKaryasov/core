/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2.buildings.threads;

import task2.buildings.Floor;

/**
 *
 * @author Александр
 */
public class Repairer extends Thread{
    private Floor floor;
    
    public Repairer(Floor floor){
        this.floor = floor;
    }
    
    @Override
    public void run(){
        for (int i = 0; i < floor.getNumberOfSpaces(); i++){
            System.out.format("Repairing space number %d with total area %.2f square meters \n", i, floor.getSpaceByIndex(i).getSquare());
        }
    }
}
