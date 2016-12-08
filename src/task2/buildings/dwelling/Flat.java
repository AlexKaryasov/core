/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2.buildings.dwelling;

import java.io.Serializable;
import task2.buildings.Space;

/**
 *
 * @author Александр
 * 
 */
public class Flat implements Space, Serializable {
    private static final double DEFAULT_SQUARE = 50.0;
    private static final int DEFAULT_ROOMS = 2;
    private double square;
    private int rooms;
    public Flat(){
        this.square = DEFAULT_SQUARE;
        this.rooms = DEFAULT_ROOMS;
    }
    public Flat(double square){
        this.square = square;
        this.rooms = 2;
    }
    public Flat(double square, int rooms){
        this.square = square;
        this.rooms = rooms;
    }
    @Override
    public int getRooms(){
        return rooms;
    }
    @Override
    public double getSquare(){
        return square;
    }
    @Override
    public void setRooms(int rooms){
        this.rooms = rooms;
    }
    @Override
    public void setSquare(double square){
        this.square = square;
    }
    @Override
    public String toString(){
        String st;
        StringBuffer sb = new StringBuffer("");
        sb.append(this.getClass().getSimpleName()).append(" (").append(this.rooms).append(", ").append(this.square).append(") ").toString();
        return sb.toString();
    }
    @Override
    public boolean equals(Object object){
        Flat flat = (Flat) object;
        return (getClass() == object.getClass()) && (this.getRooms()== flat.getRooms()) && (this.getSquare() == flat.getSquare());
    }
    @Override
    public int hashCode(){
        return (int) new Byte((byte)this.getRooms()) ^ new Byte((byte)this.getSquare());
    }
    @Override
    public Object clone() throws CloneNotSupportedException{
        Space cloned = (Space)super.clone();
        return cloned;
    }
    @Override
    public  int compareTo(Space o){
        if (this.getSquare() > o.getSquare()) return 1;
        if (this.getSquare() < o.getSquare()) return -1;
        return 0;
    }
}
