/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2.buildings.threads;


/**
 *
 * @author Александр
 */
public class CleanRepairSemaphore {
    boolean taken = false;
    
    public synchronized void take() throws InterruptedException{
        this.taken = true;
        this.notify();
    }
    
    public synchronized void release() throws InterruptedException{        
        while (!this.taken) wait();        
        this.taken = false;
        this.notify();
    }
}
