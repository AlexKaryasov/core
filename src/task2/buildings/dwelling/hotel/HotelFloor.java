/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2.buildings.dwelling.hotel;
import task2.buildings.Space;
import task2.buildings.dwelling.*;


/**
 *
 * @author Александр
 */
public class HotelFloor extends DwellingFloor{
    private int stars;
    private Space[] apartments;
    private static final int DEFAULT_STARS = 1;
    public HotelFloor(int numberOfApartments){
        apartments = new Flat[numberOfApartments];
        this.stars = DEFAULT_STARS;
        for (Space apartment : this.apartments) {
            apartment = new Flat();
        }
    }
    public HotelFloor(Space[] apartments){
        this.stars = DEFAULT_STARS;
        this.apartments = apartments;
    }
    public int getStars(){
        return this.stars;
    }
    public void setStars(int stars){
        this.stars = stars;
    }
    @Override 
    public String toString(){
        StringBuffer sb = new StringBuffer("");
        sb.append(this.getClass()).append(" (").append(this.getStars()).append(this.getNumberOfSpaces());
        for (Space arrayOfSpace : this.getArrayOfSpaces()) {
            sb.append(arrayOfSpace.getClass()).append(" (").append(arrayOfSpace.getRooms()).append(", ").append(arrayOfSpace.getSquare()).append(") ");
        }
        sb.append(") ");
        return sb.toString();
    }
    @Override
    public boolean equals(Object object){
        HotelFloor floor = (HotelFloor) object;
        return (object instanceof HotelFloor) && (this.getNumberOfSpaces() == floor.getNumberOfSpaces()) && (this.getArrayOfSpaces() == floor.getArrayOfSpaces()) && (this.getStars() == floor.getStars());
    }
    @Override
    public int hashCode(){
        int hash = 0;
        for (int i = 0; i < this.getNumberOfSpaces(); i++){
            hash = hash ^ this.getSpaceByIndex(i).hashCode();
        }
        return new Byte((byte)this.getNumberOfSpaces()) ^ new Byte((byte)this.getStars()) ^ hash;
    }
}
