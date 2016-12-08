/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2.buildings;

import task2.buildings.dwelling.Dwelling;
import task2.buildings.dwelling.DwellingFloor;
import task2.buildings.dwelling.Flat;
import task2.buildings.office.OfficeFloor;
import task2.buildings.office.OfficeBuilding;
import task2.buildings.office.Office;
import task2.buildings.Buildings;
import java.io.*;
import static java.lang.Thread.MAX_PRIORITY;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import task2.buildings.threads.Cleaner;
import task2.buildings.threads.Repairer;
import task2.buildings.threads.CleanRepairSemaphore;
import task2.buildings.threads.SequentialCleaner;
import task2.buildings.threads.SequentialRepairer;


/**
 *
 * @author Александр
 */
public class Task2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        Space[] ff = new Flat[5];
        for (int i = 0; i < ff.length; i++){
            ff[i] = new Flat(60.0);            
        }
        DwellingFloor etazh = new DwellingFloor(ff);
        etazh.setSpaceByIndex(2, new Flat(67));
        for (Space ff1 : ff) {
            System.out.println(ff1.getSquare());
        }
        System.out.println("Number of flats on floor: " + etazh.getNumberOfSpaces());
        System.out.println("The best space is: " + etazh.getBestSpace().getSquare());
        System.out.println("The number of rooms: " + etazh.getSumRooms());
        ff[3].setRooms(4);
        System.out.println("New number of rooms: " + etazh.getSumRooms());
        System.out.println("New area is: " + etazh.getSumSquare());
        ff[2].setSquare(45.8);
        System.out.println("Now about new area after changing? " + etazh.getSumSquare());
        System.out.println(etazh.getSpaceByIndex(2).getSquare());
        etazh.addSpaceByIndex(2, new Flat(64.8, 2));
        System.out.println("before adding: " + etazh.getNumberOfSpaces());
        etazh.addSpaceByIndex(2, new Flat(64.8, 2));
        System.out.println("After adding: " + etazh.getNumberOfSpaces());
        //etazh.deleteSpaceByIndex(5);
        etazh.addSpaceByIndex(2, new Flat(64.8, 2));
        System.out.println("Number of flats after adding and removing: " + etazh.getNumberOfSpaces());        
        Floor[] someFloors;
        someFloors = new Floor[10];
        //Dwelling building = new Dwelling(someFloors);
        for (int i = 0; i < someFloors.length; i++) {
            someFloors[i] = new DwellingFloor(12-i);
            }                    
        Dwelling building = new Dwelling(someFloors);
        System.out.println(building.getFloorByIndex(3).getSpaceByIndex(2).getSquare());
        System.out.println("flats before adding: " + building.getNumberOfSpaces());
        //System.out.println("Ваш звонок очень важен для нас ");
        building.addSpaceByIndex(27, new Flat(70.2,2));
        //System.out.println("Спасибо за ожидание");
        System.out.println("flats after adding: " + building.getNumberOfSpaces());
        //building.deleteSpaceByIndex(26);
        System.out.println("flats after removing: " + building.getNumberOfSpaces());
        Floor etazh2 = new OfficeFloor(24);
        for(int i = 0; i < etazh2.getNumberOfSpaces(); i++){
            etazh2.getArrayOfSpaces()[i] = new Office();
        }
        System.out.println(etazh2.getBestSpace().getSquare());
        System.out.println(etazh2.getNumberOfSpaces());
        etazh2.setSpaceByIndex(5, new Office(64.4));
        etazh2.addSpaceByIndex(2, new Office(60.0));
        System.out.println(etazh2.getNumberOfSpaces());
        //etazh2.deleteSpaceByIndex(8);
        System.out.println(etazh2.getNumberOfSpaces());
        for (int i = 0; i < etazh2.getNumberOfSpaces(); i++){
            System.out.println(etazh2.getArrayOfSpaces()[i].getSquare());
            System.out.println(etazh2.getArrayOfSpaces()[i].getRooms());
        }
        //Office[] gg = new Office[10];
        Floor[] someFloors2 = new OfficeFloor[10];
        for (int i = 0; i < someFloors2.length; i++){
            someFloors2[i] = new OfficeFloor(12-i);
        }               
        Building building2 = new OfficeBuilding((OfficeFloor[]) someFloors2);
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream("out1.bin"));
            Buildings.outputBuilding(building2, dos);
            dos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Task2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Task2.class.getName()).log(Level.SEVERE, null, ex);
        }        
        try (DataInputStream dis = new DataInputStream(new FileInputStream("out1.bin"))) {
            Buildings.inputBuilding(dis);
            dis.close();
        } catch (IOException ex) {
            Logger.getLogger(Task2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try (PrintWriter out = new PrintWriter(new FileWriter("out.txt"))) {
            //Buildings.writeBuilding(building2, out);
            //Buildings.writeBuilding(building, out);
            Buildings.writeBuilding(building2, out);
        }
        catch (IOException ex) {
            Logger.getLogger(Task2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try (BufferedReader in = new BufferedReader(new FileReader("out.txt"))) {
            Building readBuilding = Buildings.readBuilding(in);
            System.out.println(readBuilding.getBestSpace());
            in.close();
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(Task2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Task2.class.getName()).log(Level.SEVERE, null, ex);
        }        
        System.out.println("Число этажей во втором здании: " + building2.getNumberOfSpaces());
        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(new FileOutputStream("out.bin"));
            out.writeObject(building2);
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(Task2.class.getName()).log(Level.SEVERE, null, ex);
        }
        Building building3 = null;
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("out.bin"));
            building3 = (Building)in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Task2.class.getName()).log(Level.SEVERE, null, ex);
        }
        if ((building3.getNumberOfFloors() == building2.getNumberOfFloors()) && (building2.getNumberOfSpaces() == building3.getNumberOfSpaces()))
            System.out.println("buildings are equal");
        Iterator iter1 = building.iterator();
        Iterator iter2 = etazh.iterator();
        while (iter1.hasNext()){
            System.out.println(iter1.next());
        }    
        while (iter2.hasNext()){
            System.out.println(iter2.next());
        }
        Floor etazh3 = new DwellingFloor(ff);
        Repairer t = new Repairer(etazh2);
        //t.start();
        //t.setPriority(MAX_PRIORITY);
        //t.interrupt();
        Cleaner t2 = new Cleaner(etazh2);
        //t2.start();
        CleanRepairSemaphore sem = new CleanRepairSemaphore();
        SequentialRepairer sr = new SequentialRepairer(sem, etazh2);
        SequentialCleaner sc = new SequentialCleaner(sem, etazh2);
        sr.start();
        sc.start();
        sc.setPriority(MAX_PRIORITY);
    }    
}    
