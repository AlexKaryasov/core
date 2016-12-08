/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2.buildings.dwelling;

import task2.buildings.dwelling.DwellingFloor;
import java.io.Serializable;
import java.util.Iterator;
import task2.buildings.Building;
import task2.buildings.Floor;
import task2.buildings.FloorIterator;
import task2.buildings.Space;

/**
 *
 * @author Александр
 */
//import task2.DwellingFloor;
public class Dwelling implements Building, Serializable{
    private Floor[] floors;
    public Dwelling(int numberOfFloors, int[] flatsOnFloor){
        this.floors = (Floor[])new DwellingFloor[numberOfFloors];
        for (int i = 0; i < numberOfFloors; i++){
            this.floors[i] = (Floor)new DwellingFloor(flatsOnFloor[i]);
        }
    }
    public Dwelling(Floor[] floors){
        this.floors = floors;
    }
    public Dwelling(){}
    @Override
    public int getNumberOfFloors(){
        return floors.length;
    }
    @Override
    public int getNumberOfSpaces(){
        int flats = 0;
        for (Floor floor : floors) {
            flats += floor.getNumberOfSpaces();
        }        
        return flats;
    }
    @Override
    public double getSumSquare(){
        double square = 0;
        for (Floor floor : floors){
            square += floor.getSumSquare();
        }    
        return square;
    }
    @Override
    public int getSumRooms(){
       int rooms = 0;
       for (Floor floor : floors){
            rooms += floor.getSumRooms();
        }    
        return rooms;
    }
    @Override
    public Floor[] getArrayOfFloors(){
        return floors;
    }
    @Override
    public Floor getFloorByIndex(int number){
        return floors[number];
    }
    @Override
    public void setFloorByIndex(Floor anotherFloor, int number){        
        for (int i = 0; i < floors[number].getArrayOfSpaces().length; i++){
            floors[number].getArrayOfSpaces()[i].setRooms(anotherFloor.getSpaceByIndex(i).getRooms());
            floors[number].getArrayOfSpaces()[i].setSquare(anotherFloor.getSpaceByIndex(i).getSquare());
        }
        //return anotherFloor;
    }
    @Override
    public Space getSpaceByIndex(int number){
        int iter_floors = 0;
        int num = number;
        for (Floor floor : floors){
            while (num > floor.getNumberOfSpaces()){
                num = num - floor.getNumberOfSpaces();
                iter_floors++;
                if (iter_floors >= floors.length)
                    break;
            }        
        }
        return floors[iter_floors].getSpaceByIndex(num);
    }
    @Override
    public void setSpaceByIndex(int number, Space anotherSpace){
        int iter_floors = 0;
        int num = number;
        for (Floor floor : floors){
            while (num > floor.getNumberOfSpaces()){
                num = num - floor.getNumberOfSpaces();
                iter_floors++;
                if (iter_floors >= floors.length)
                    break;
            }
            floors[iter_floors].getArrayOfSpaces()[num].setRooms(anotherSpace.getRooms());
            floors[iter_floors].getArrayOfSpaces()[num].setSquare(anotherSpace.getSquare());
        }    
    }
    @Override
    public void addSpaceByIndex(int number, Space addedFlat){
        int iter_floors = 0;
        int num = number;
        for (Floor floor : floors){
            while (num > floor.getNumberOfSpaces()){
                num = num - floor.getNumberOfSpaces();
                iter_floors++;
                if (iter_floors >= floors.length)
                    break;
            }                        
        }        
        floors[iter_floors].addSpaceByIndex(num, addedFlat);        
    }
    @Override
    public void deleteSpaceByIndex(int number){
        int iter_floors = 0;
        for (Floor floor : floors){
            while (number >= floor.getNumberOfSpaces()){
                number -= floor.getNumberOfSpaces();
                iter_floors++;
            }                        
        }        
        floors[iter_floors].deleteSpaceByIndex(number); 
        //floors[number / floors.length + 1].removeFlat(number % floors.length);        
    }
    @Override
    public Space getBestSpace(){
        Space max =floors[0].getArrayOfSpaces()[0];
        for (int i = 0; i < this.getNumberOfFloors(); i++){
            for (int j = 0; j < floors[i].getNumberOfSpaces(); j++){
                if (floors[i].getArrayOfSpaces()[j].getSquare() > max.getSquare()) { 
                    max = floors[i].getArrayOfSpaces()[j];
                }  
            }
        }
        return max;
    }
    @Override
    public void sort(Space[] flats){
        for (int i = 0; i < flats.length; i++) {        
        Space min = flats[i];
        int min_i = i;                    
        for (int j = i+1; j < flats.length; j++) {
            if (flats[j].getSquare() < min.getSquare()) {
                min = flats[j];
                min_i = j;
            }
        }
            if (i != min_i) {
            Space tmp = flats[i];
            flats[i] = flats[min_i];
            flats[min_i] = tmp;
            }
        }        
    }
    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer("");
        sb.append(this.getClass().getSimpleName()).append(" (").append(this.floors.length).append(", ");
        for (Floor floor : this.floors) {
            sb.append(floor.getClass().getSimpleName()).append(" (").append(floor.getNumberOfSpaces()).append(", ");
            for (Space arrayOfSpace : floor.getArrayOfSpaces()) {
                sb.append(arrayOfSpace.getClass().getSimpleName()).append(" (").append(arrayOfSpace.getRooms()).append(", ").append(arrayOfSpace.getSquare()).append(") ");
            }           
        }
        sb.append(") ").append(") ");
        return sb.toString();
    }
    @Override
    public boolean equals(Object object){
        Dwelling floor = (Dwelling) object;
        return (getClass() == object.getClass()) && (this.getArrayOfFloors()== floor.getArrayOfFloors()) && (this.getNumberOfFloors() == floor.getNumberOfFloors());
    }
    @Override
    public int hashCode(){
        int hash = 0;
        for (Floor floor : this.getArrayOfFloors()) {
            hash = hash ^ floor.hashCode();
        }
        return (int) new Byte((byte) this.getNumberOfFloors()) ^ hash;
    }
    @Override
    public Object clone() throws CloneNotSupportedException{
        Building cloned = (Building)super.clone();
        for (int i = 0; i < this.getArrayOfFloors().length; i++)
            cloned.setFloorByIndex((Floor)cloned.getFloorByIndex(i).clone(), i);         
        for (int j = 0; j < this.getNumberOfSpaces(); j++)
            cloned.setSpaceByIndex(j, (Space)cloned.getSpaceByIndex(j).clone());
        return cloned;
    }
    @Override 
    public Iterator iterator(){
        return new FloorIterator(new Dwelling(this.getArrayOfFloors()));
    }
}
