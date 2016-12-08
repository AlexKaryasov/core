/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2.buildings.office;

import java.io.Serializable;
import java.util.Iterator;
import task2.buildings.Building;
import task2.buildings.Floor;
import task2.buildings.FloorIndexOutOfBoundException;
import task2.buildings.FloorIterator;
import task2.buildings.Space;

/**
 *
 * @author Александр
 */
public class OfficeBuilding implements Building, Serializable {
    private DoubleList listOfFloors;
    protected static class DoubleList implements Serializable{
        private DoubleNode head;
        private int size;
        private DoubleNode getDoubleNodeByIndex(int number) {
            DoubleNode office = head;
            int middle = this.size / 2;
            if (number > middle){
                office = office.getNext();
                for (int i = this.size; i > number; i--)
                    office = office.getPrev();
            }
            else{
                for (int i = 0; i < number; i++)
                    office = office.getNext();
            }
            return office;
        }
        private void addDoubleNodeByIndex(DoubleNode e, int number){
            DoubleNode office;
            if (number == 0)
                office = this.getDoubleNodeByIndex(this.getSize() - 1);
            else
                office = this.getDoubleNodeByIndex(number - 1);
            e.setNext(office.getNext());
            office.setNext(e);
            e.setPrev(office.getNext().getPrev());
            office.getNext().setPrev(e);
            this.setSize(this.getSize() + 1);
        }
        /*private void setDoubleNodeByIndex(DoubleNode e, int number){
            DoubleNode office;
            this.getDoubleNodeByIndex(number).setElement(element);
        }*/
        private void deleteDoubleNodeByIndex(int number){
            DoubleNode office;
            if (number == 0)
                office = this.getDoubleNodeByIndex(this.getSize() - 1);
            else
                office = this.getDoubleNodeByIndex(number - 1);
            office.setNext(office.getNext().getNext());
            office.getNext().getNext().setPrev(office);
            this.setSize(this.getSize() - 1);
        }
        protected int getSize(){
            return this.size;        
        }
        protected void setSize(int size){
            this.size = size;
        }            
        protected DoubleNode getHead(){
            return this.head;
        }
        protected void setHead(DoubleNode head){
            this.head = head;
        }
        @Override
        public Object clone() throws CloneNotSupportedException{
            DoubleList cloned = (DoubleList) super.clone();
            return cloned;
        }
    }
    public OfficeBuilding(){}
    public OfficeBuilding(Floor[] floors){
        this.listOfFloors = new DoubleList();
        this.listOfFloors.setHead(new DoubleNode(floors[0]));
        this.listOfFloors.setSize(1);
        for (int i = 1; i < floors.length; i++){
            DoubleNode floor = new DoubleNode(floors[i]);
            this.listOfFloors.addDoubleNodeByIndex(floor, i);            
        }        
    }
    public OfficeBuilding(int numberOfFloors, int[] offices){
        this.listOfFloors = new DoubleList();
        Floor groundFloor = (Floor)new OfficeFloor(offices[0]);
        this.listOfFloors.setHead(new DoubleNode(groundFloor));
        this.listOfFloors.setSize(1);
        for (int i = 1; i < numberOfFloors; i++){
            Floor floor = (Floor)new OfficeFloor(offices[i]);
            this.listOfFloors.addDoubleNodeByIndex(new DoubleNode(floor), i);
        }                    
    }
    public DoubleList getFloors(){
        return this.listOfFloors;
    }
    @Override
    public int getNumberOfFloors(){
        return this.listOfFloors.getSize();
    }
    @Override
    public int getNumberOfSpaces(){
        int flats = 0;
        for (Floor floor : this.getArrayOfFloors()) {
            flats += floor.getNumberOfSpaces();
        }        
        return flats;
    }
    @Override
    public double getSumSquare(){
        DoubleNode chosenFloor = this.listOfFloors.getHead();
        double sum = 0;
        Floor floor = this.listOfFloors.getHead().getElement();
        sum += floor.getSumSquare();
        for (int i = 1; i < this.listOfFloors.getSize(); i++){
            chosenFloor = chosenFloor.getNext();
            sum += chosenFloor.getElement().getSumSquare();
        }
        return sum;
    }
    @Override
    public int getSumRooms(){
        DoubleNode chosenFloor = this.listOfFloors.getHead();
        int sum = 0;
        Floor floor = this.listOfFloors.getHead().getElement();
        sum += floor.getSumRooms();
        for (int i = 1; i < this.listOfFloors.getSize(); i++){
            chosenFloor = chosenFloor.getNext();
            sum += chosenFloor.getElement().getSumRooms();
        }
        return sum;
    }
    @Override
    public Floor[] getArrayOfFloors(){
        Floor[] floors = new Floor[this.listOfFloors.getSize()];
        DoubleNode chosenElement = this.listOfFloors.getHead();
        for (int i = 0; i < floors.length; i++){
            floors[i] = this.listOfFloors.getDoubleNodeByIndex(i).getElement();
            chosenElement = chosenElement.getNext();
        }
        return floors;
    }
    @Override
    public Floor getFloorByIndex(int number){
        if ((number < 0) || (number > this.listOfFloors.getSize()))
            throw new FloorIndexOutOfBoundException("Недопустимый номер этажа", number);
        Floor floor = this.listOfFloors.getDoubleNodeByIndex(number).getElement();
        return floor;
    }
    @Override
    public void setFloorByIndex(Floor floor, int number){
        if ((number < 0) || (number > this.listOfFloors.getSize()))
            throw new FloorIndexOutOfBoundException("Недопустимый номер этажа", number);
        this.listOfFloors.getDoubleNodeByIndex(number).setElement(floor);
    }
    @Override
    public void addSpaceByIndex(int number, Space office){
        int iter_floors = 0;
        int num = number;
        for (Floor arrayOfFloor : this.getArrayOfFloors()) {
            while (num > arrayOfFloor.getNumberOfSpaces()) {
                num = num - arrayOfFloor.getNumberOfSpaces();
                iter_floors++;
                if (iter_floors >= this.getArrayOfFloors().length)
                    break;
            }
        }        
        this.getArrayOfFloors()[iter_floors].addSpaceByIndex(num, office); 
    }@Override
    public Space getSpaceByIndex(int number){
        int iter_floors = 0;
        int num = number;
        for (Floor floor : this.getArrayOfFloors()){
            while (num > floor.getNumberOfSpaces()){
                num = num - floor.getNumberOfSpaces();
                iter_floors++;
                if (iter_floors >= this.getArrayOfFloors().length)
                    break;
            }        
        }
        return this.getArrayOfFloors()[iter_floors].getSpaceByIndex(num);
    }
    @Override
    public void setSpaceByIndex(int number, Space anotherSpace){
        int iter_floors = 0;
        int num = number;
        for (Floor floor : this.getArrayOfFloors()){
            while (num > floor.getNumberOfSpaces()){
                num = num - floor.getNumberOfSpaces();
                iter_floors++;
                if (iter_floors >= this.getArrayOfFloors().length)
                    break;
            }
            this.getArrayOfFloors()[iter_floors].getArrayOfSpaces()[num].setRooms(anotherSpace.getRooms());
            this.getArrayOfFloors()[iter_floors].getArrayOfSpaces()[num].setSquare(anotherSpace.getSquare());
        }    
    }
    //переписать!
    @Override
    public void deleteSpaceByIndex(int number){
        int iter_floors = 0;
        int num = number;
        for (Floor arrayOfFloor : this.getArrayOfFloors()) {
            while (num > arrayOfFloor.getNumberOfSpaces()) {
                num = num - arrayOfFloor.getNumberOfSpaces();
                iter_floors++;
                if (iter_floors >= this.getArrayOfFloors().length)
                    break;
            }
        this.getArrayOfFloors()[iter_floors].deleteSpaceByIndex(num); 
        }
    }
    @Override
    public Space getBestSpace(){
        Space chosenOffice = this.getArrayOfFloors()[0].getBestSpace();
        Space max = chosenOffice;
        for (int i = 1; i < this.getArrayOfFloors().length; i++){            
            if (this.getArrayOfFloors()[i].getBestSpace().getSquare() > max.getSquare() )
                max = this.getArrayOfFloors()[i].getBestSpace();
        }
        return max;
    }
    @Override
    public void sort(Space[] offices){
        Space temp;
        for (int i = 0; i < offices.length; i++){
            for (int j = offices.length - 1; j > i; j--){
                if (offices[j-1].getSquare() < offices[j].getSquare()){
                    temp = offices[j-1];
                    offices[j-1] = offices[j];
                    offices[j] = temp;
                }                    
            }
        }        
    }
    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer("");
        sb.append(this.getClass().getSimpleName()).append(" (").append(this.getArrayOfFloors().length).append(", ");
        for (Floor floor : this.getArrayOfFloors()) {
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
        OfficeBuilding floor = (OfficeBuilding) object;
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
        OfficeBuilding cloned = (OfficeBuilding) super.clone();
        for (int i = 0; i < this.getArrayOfFloors().length; i++) {
            cloned.setFloorByIndex((Floor) this.getFloorByIndex(i).clone(), i);
        }
        for (int i = 0; i < this.getNumberOfSpaces(); i++){
            cloned.setSpaceByIndex(i, (Office)this.getSpaceByIndex(i).clone());
        }
        cloned.listOfFloors.setHead((DoubleNode) this.listOfFloors.getHead().clone());
        return cloned;
    }
    @Override
    public Iterator iterator(){
        return new FloorIterator(new OfficeBuilding(this.getArrayOfFloors()));
    }
}
