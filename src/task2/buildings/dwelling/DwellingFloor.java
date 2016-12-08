/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2.buildings.dwelling;

import task2.buildings.dwelling.Flat;
import java.io.Serializable;
import java.util.Iterator;
import task2.buildings.Floor;
import task2.buildings.FloorIterator;
import task2.buildings.Space;
import task2.buildings.SpaceIterator;

/**
 *
 * @author Александр
 * 
 */
public class DwellingFloor implements Floor, Serializable{
    private Space[] flats;
    //private int number_of_flats;
    public DwellingFloor(int numberOfFlats){
        flats = new Space[numberOfFlats];
        //this.number_of_flats = number_of_flats;
        for (int i = 0; i < numberOfFlats; i++){
            flats[i] = new Flat();            
        }
    }
    public DwellingFloor(Space[] flats){
        this.flats = flats;
        //this.number_of_flats = flats.length;
    }
    public DwellingFloor(){}
    @Override
    public int getNumberOfSpaces(){
        return flats.length;
    }
    @Override
    public double getSumSquare(){
        double sum =0;
        for (Space flat : flats) {
            sum += flat.getSquare();
        }
        return sum;
    }
    @Override
    public int getSumRooms(){
        int sum = 0;
        for (Space flat: flats){
            sum += flat.getRooms();
        }
        return sum;
    }
    @Override
    public Space[] getArrayOfSpaces(){
        return flats;
    }
    @Override
    public Space getSpaceByIndex(int number){
        return flats[number];
    }
    @Override
    public void setSpaceByIndex(int number, Space anotherFlat){
        flats[number].setRooms(anotherFlat.getRooms());
        flats[number].setSquare(anotherFlat.getSquare());
        //return flats[number];
    }
    @Override
    public void addSpaceByIndex(int number, Space addedFlat){
        Space[] newFlats = new Space[flats.length+1];
        System.arraycopy(flats, 0, newFlats, 0, number);
        newFlats[number] = addedFlat;
        System.arraycopy(flats, number, newFlats, number + 1, newFlats.length - (number + 1));    
        flats = newFlats;
    }
    @Override
    public void deleteSpaceByIndex(int number){
        Space[] newFlats = new Space[flats.length-1];
        System.arraycopy(flats, 0, newFlats, 0, number);
        System.arraycopy(flats, number+1, newFlats, number+1, newFlats.length - (number + 1));
        flats = newFlats;
    }
    @Override
    public Space getBestSpace(){
        Space max = flats[0];
        for (Space flat : flats) {
            if (flat.getSquare() > max.getSquare())
                    max = flat;
        }
        return max;
    }
    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer("");
        sb.append(this.getClass().getSimpleName()).append("(").append(this.getArrayOfSpaces().length).append(" ");
        for (Space arrayOfSpace : this.getArrayOfSpaces()) {
            sb.append(arrayOfSpace.getClass().getSimpleName())
                    .append(" (")
                    .append(arrayOfSpace.getRooms())
                    .append(", ")
                    .append(arrayOfSpace.getSquare())
                    .append(") ");
        }
        sb.append(") ");
        return sb.toString();
    }
    @Override
    public boolean equals(Object object){
        DwellingFloor floor = (DwellingFloor) object;
        return (getClass() == object.getClass()) && (this.getArrayOfSpaces()== floor.getArrayOfSpaces()) && (this.getNumberOfSpaces() == floor.getNumberOfSpaces());
    }
    @Override
    public int hashCode(){
        int hash = 0;
        for (Space flat : this.flats) {
            hash = hash ^ flat.hashCode();
        }
        return (int) new Byte((byte) this.getNumberOfSpaces()) ^ hash;
    }
    @Override
    public Object clone() throws CloneNotSupportedException{
        DwellingFloor cloned = (DwellingFloor)super.clone();
        cloned.flats = this.flats.clone();      //то же самое в двеллинге!
        for (int i = 0; i < this.getNumberOfSpaces(); i++){
            cloned.setSpaceByIndex(i, (Space)this.getSpaceByIndex(i).clone());
        }
        return cloned;
    }
    //@Override
    public Iterator iterator(){
        return new SpaceIterator(new DwellingFloor(this.flats));
    }
   
    @Override
    public int compareTo(Floor o){
        if (this.getNumberOfSpaces() > o.getNumberOfSpaces()) return 1;
        if (this.getNumberOfSpaces() < o.getNumberOfSpaces()) return -1;
        return 0;
    }
}
