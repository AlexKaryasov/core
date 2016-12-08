/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2.buildings.dwelling.hotel;

import java.util.Arrays;
import task2.buildings.Floor;
import task2.buildings.Space;
import task2.buildings.dwelling.Dwelling;

/**
 *
 * @author Александр
 */
public class Hotel extends Dwelling{
    HotelFloor[] hotelFloor;
    public int getStarsOfHotel(){
        int maxStars = hotelFloor[0].getStars();        
        for (int i = 1; i < this.hotelFloor.length; i++)
            if (hotelFloor[i].getStars() > maxStars)
                maxStars = hotelFloor[i].getStars();        
        return maxStars;
    }
    @Override
    public Space getBestSpace(){
        double quality;
        Space max = this.hotelFloor[0].getSpaceByIndex(0);
        for (int i = 0; i < this.getNumberOfFloors(); i++){
            for (int j = 0; j < this.hotelFloor[i].getNumberOfSpaces(); j++){
                switch (this.hotelFloor[i].getStars()){
                    case 1 : quality = this.hotelFloor[i].getStars()*0.25;
                    break;
                    case 2 : quality = this.hotelFloor[i].getStars()*0.5;
                    break;
                    case 3: quality = this.hotelFloor[i].getStars();
                    break;
                    case 4: quality = this.hotelFloor[i].getStars()*1.25;
                    break;
                    case 5: quality = this.hotelFloor[i].getStars()*1.5;
                    break;
                    default: quality = 0;
                        break;
                }
                if (this.hotelFloor[i].getSpaceByIndex(j).getSquare()*quality > max.getSquare())
                    max = this.hotelFloor[i].getSpaceByIndex(j);
            }            
        }
        return max;
    }
    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer("");
        sb.append(this.getClass()).append(" (").append(this.getStarsOfHotel()).append(", ").append(this.getNumberOfFloors()).append(", ");
        for (HotelFloor hotelFloor1 : this.hotelFloor) {
            hotelFloor1.toString();
        }
        sb.append(") ");
        return sb.toString();
    }
    @Override
    public boolean equals(Object object){
        Hotel hotel = (Hotel) object;
        return (object instanceof Hotel) && (this.getNumberOfFloors() == hotel.getNumberOfFloors()) && (this.getArrayOfFloors() == hotel.getArrayOfFloors());
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < this.getNumberOfFloors(); i++){
            hash = hash ^ this.getFloorByIndex(i).hashCode();
        }
        return new Byte((byte)this.getNumberOfFloors()) ^ hash;
    }
    
}
