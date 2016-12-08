/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2.buildings.threads;

import java.util.logging.Level;
import java.util.logging.Logger;
import task2.buildings.Floor;

/**
 *
 * @author Александр
 */
public class SequentialCleaner extends Thread{
    private CleanRepairSemaphore sem;
    private Floor floor;
    
    public SequentialCleaner(CleanRepairSemaphore sem, Floor floor){
        this.sem = sem;
        this.floor = floor;
    }
    
    @Override
    public void run(){
       for (int i = 0; i < floor.getNumberOfSpaces(); i++){
           try {
               sem.release();
               System.out.format("Cleaning space number %d with total area %.2f square meters \n", i, floor.getSpaceByIndex(i).getSquare());
               Thread.sleep(700);
           } catch (InterruptedException ex) {
               Logger.getLogger(SequentialCleaner.class.getName()).log(Level.SEVERE, null, ex);
           }                      
       } 
    }
}
