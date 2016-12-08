/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2.buildings.office;

//import task3.OfficeFloor.SingleList.Node;

import task2.buildings.office.Node;
import task2.buildings.office.Office;
import java.io.Serializable;
import java.util.Iterator;
import task2.buildings.Floor;
import task2.buildings.FloorIndexOutOfBoundException;
import task2.buildings.InvalidRoomsCountException;
import task2.buildings.InvalidSpaceAreaException;
import task2.buildings.Space;
import task2.buildings.SpaceIterator;

//import task3.OfficeFloor.SingleList.Node.*;

/**
 *
 * @author Александр
 * 
 */
public class OfficeFloor implements Floor, Serializable {
    private SingleList listOfOffices;
    protected static class SingleList implements Serializable{
        private Node head;
        private int size;
        //private Node office;
        private Node getNodeByIndex(int number){
            Node office = head;
            for (int i = 0; i < number; i++){
                office = office.getNext();
            }
            return office;
    }
        private void addNodeByIndex(Node e, int number){
            Node office; 
            //if ((number < 0) || (number > this.size))
                //throw new FloorIndexOutOfBoundException("Недопустимый номер этажа", number);
            if (number == 0)
                office = this.getNodeByIndex(this.getSize()-1);
            else
                office = this.getNodeByIndex(number-1);
            e.setNext(office.getNext());
            office.setNext(e);
            this.setSize(this.getSize()+1);
    }
        private int getSize(){
            return this.size;
        }
        private void setSize(int size){
            this.size = size;
        }        
        private void deleteNodeByIndex(int number) {
            Node office;
            if (number == 0) {
                office = this.getNodeByIndex(this.getSize() - 1);
            } else {
                office = this.getNodeByIndex(number - 1);
            }
            office.setNext(office.getNext().getNext());
            if (number == 0) {
                this.head = this.head.getNext();
            }
            this.setSize(this.getSize() - 1);
        }
        private Node getHead(){
            return this.head;
        }
        private void setHead(Node e){
            this.head = e;
        }     
        @Override
        public Object clone() throws CloneNotSupportedException{
            SingleList cloned = (SingleList) super.clone();
            cloned.setHead((Node) this.getHead().clone());
            return cloned;
        }
    }
    public OfficeFloor(){}
    public OfficeFloor(int numberOfOffices){
            this.listOfOffices = new SingleList();
            listOfOffices.setHead(new Node(new Office(0)));
            listOfOffices.setSize(1);
            if (numberOfOffices < 0) 
                throw new FloorIndexOutOfBoundException("Недопустимый номер офиса", numberOfOffices);
            for (int i = 1; i < numberOfOffices; i++){
                Node office = new Node(new Office());                
                listOfOffices.addNodeByIndex(office, i);
            }           
        }
    public OfficeFloor(Space[] arrayOfOffices){
        this.listOfOffices = new SingleList();
        listOfOffices.setHead(new Node(arrayOfOffices[0]));
        listOfOffices.setSize(1);
        for(int i = 1; i < arrayOfOffices.length; i++){
            Node office = new Node(arrayOfOffices[i]);
            listOfOffices.addNodeByIndex(office, i);
        }
    }
    @Override
    public int getNumberOfSpaces(){
        return this.listOfOffices.getSize();
    }
    @Override
    public double getSumSquare(){
        Node chosenOffice = this.listOfOffices.getHead();
        double sum = 0;
        Space office = this.listOfOffices.getHead().getElement();
        sum += office.getSquare();
        for (int i = 1; i < listOfOffices.size; i++){
            chosenOffice = chosenOffice.getNext();
            sum += chosenOffice.getElement().getSquare();
        }
        return sum;
    }
    @Override
    public int getSumRooms(){
        Node chosen_office = this.listOfOffices.getHead();
        int sum = 0;
        Space office = this.listOfOffices.getHead().getElement();
        sum += office.getRooms();
        for (int i = 1; i < listOfOffices.getSize(); i++){
            chosen_office = chosen_office.getNext();
            sum += chosen_office.getElement().getRooms();
        }
        return sum;
    }
    //@Override
    public SingleList getOffices(){
        return this.listOfOffices;
    }
    //@Override
    @Override
    public Space[] getArrayOfSpaces(){
        Space[] offices = new Space[getOffices().getSize()];
        Node chosenOffice = this.listOfOffices.getHead();
        for (int i = 0; i < offices.length; i++) {
            offices[i] = this.listOfOffices.getNodeByIndex(i).getElement();
            chosenOffice = chosenOffice.getNext();
        }
        return offices;        
    }
    //@Override
    @Override
    public Space getSpaceByIndex(int number){
        if ((number < 0) || (number > this.listOfOffices.getSize()))
            throw new FloorIndexOutOfBoundException("Недопустимый номер офиса", number);
        Space office = this.listOfOffices.getNodeByIndex(number).getElement();
        return office;
    }
    //@Override
    @Override
    public void setSpaceByIndex(int number, Space newOffice){
        Space office = getSpaceByIndex(number);
        if (newOffice.getSquare() < 0)
            throw new InvalidSpaceAreaException("Площадь не может быть отрицательной!", newOffice.getSquare());
        office.setSquare(newOffice.getSquare());
        if (newOffice.getRooms() < 0)
            throw new InvalidRoomsCountException("Количество комнат не может быть отрицательным!", newOffice.getRooms());
        if ((number < 0) || (number > this.listOfOffices.getSize()))
            throw new FloorIndexOutOfBoundException("Недопустимый номер офиса", number);
        office.setRooms(newOffice.getRooms());
    }
    //@Override
    @Override
    public void addSpaceByIndex(int number, Space office){
        Node element = new Node(office);
        if (office.getSquare() < 0)
            throw new InvalidSpaceAreaException("Площадь не может быть отрицательной", office.getSquare());
        if (office.getRooms() < 0)
            throw new InvalidRoomsCountException("Количество комнат не может быть отрицательным", office.getRooms());
        if ((number < 0) || (number > this.listOfOffices.getSize()))
            throw new FloorIndexOutOfBoundException("Недопустимый номер офиса", number);
        this.listOfOffices.addNodeByIndex(element, number);
    }
    //@Override
    @Override
    public void deleteSpaceByIndex(int number){
        if (number < 0)
            throw new FloorIndexOutOfBoundException("Недопустимый номер офиса", number);
        this.listOfOffices.deleteNodeByIndex(number);
    }
    //@Override
    @Override
    public Space getBestSpace(){
        Node chosen_office = this.listOfOffices.getHead();
        Space office = this.listOfOffices.getHead().getElement();
        Space maxOffice = office;
        for (int i = 1; i < this.listOfOffices.getSize(); i++){
            chosen_office = chosen_office.getNext();
            office =  chosen_office.getElement();
            if (chosen_office.getElement().getSquare() < 0)
                throw new InvalidSpaceAreaException("Площадь не может быть отрицательной!", chosen_office.getElement().getSquare());            
            if (office.getSquare() > maxOffice.getSquare())
                maxOffice = office;
        }
        return maxOffice;
    }
    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer("");
        sb.append(this.getClass()).append(" (").append(this.getNumberOfSpaces()).append(", ");
        for (Space arrayOfSpace : this.getArrayOfSpaces()) {
            sb.append(arrayOfSpace.getClass()).append(" (").append(arrayOfSpace.getRooms()).append(", ").append(arrayOfSpace.getSquare()).append(") ");
        }
        sb.append(") ");
        return sb.toString();
    }
    @Override
    public boolean equals(Object object){
        OfficeFloor floor = (OfficeFloor) object;
        return (getClass() == object.getClass()) && (this.getArrayOfSpaces()== floor.getArrayOfSpaces()) && (this.getNumberOfSpaces() == floor.getNumberOfSpaces());
    }
    @Override
    public int hashCode(){
        int hash = 0;
        for (Space space : this.getArrayOfSpaces()) {
            hash = hash ^ space.hashCode();
        }
        return (int) new Byte((byte) this.getNumberOfSpaces()) ^ hash;
    }
    @Override
    public Object clone() throws CloneNotSupportedException{
        OfficeFloor cloned = (OfficeFloor)super.clone();
        cloned.listOfOffices = (SingleList) super.clone();
        cloned.listOfOffices.setHead((Node) this.listOfOffices.getHead().clone());
        for (int i = 0; i < this.getNumberOfSpaces(); i++){
            cloned.setSpaceByIndex(i, (Space)this.getSpaceByIndex(i).clone());
        }
        return cloned;
    }
    //@Override
    public Iterator iterator(){
        return new SpaceIterator(new OfficeFloor(this.getArrayOfSpaces()));
    }
    
    @Override
    public int compareTo(Floor o){
        if (this.getNumberOfSpaces() > o.getNumberOfSpaces()) return 1;
        if (this.getNumberOfSpaces() < o.getNumberOfSpaces()) return -1;
        return 0;
    }
}

