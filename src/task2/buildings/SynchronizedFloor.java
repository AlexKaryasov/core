/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2.buildings;

/**
 *
 * @author Александр
 * 
 */
public class SynchronizedFloor implements Floor{
    private Space[] flats;
    
    public SynchronizedFloor(Space[] flats){
        this.flats = flats;
    }
    

    @Override
    public synchronized int getNumberOfSpaces() {
        return this.flats.length;
    }

    @Override
    public synchronized void addSpaceByIndex(int number, Space office) {
        Space[] newFlats = new Space[flats.length+1];
        System.arraycopy(flats, 0, newFlats, 0, number);
        newFlats[number] = office;
        System.arraycopy(flats, number, newFlats, number + 1, newFlats.length - (number + 1));    
        flats = newFlats;
    }

    @Override
    public synchronized void deleteSpaceByIndex(int number) {
        Space[] newFlats = new Space[flats.length-1];
        System.arraycopy(flats, 0, newFlats, 0, number);
        System.arraycopy(flats, number+1, newFlats, number+1, newFlats.length - (number + 1));
        flats = newFlats;
    }

    @Override
    public synchronized Space[] getArrayOfSpaces() {
        return this.flats;
    }

    @Override
    public synchronized Space getBestSpace() {
        Space max = flats[0];
        for (Space flat : flats) {
            if (flat.getSquare() > max.getSquare())
                    max = flat;
        }
        return max;
    }

    @Override
    public synchronized Space getSpaceByIndex(int number) {
        return flats[number];
    }

    @Override
    public synchronized int getSumRooms() {
        int sum = 0;
        for (Space flat: flats){
            sum += flat.getRooms();
        }
        return sum;
    }

    @Override
    public synchronized double getSumSquare() {
        double sum =0;
        for (Space flat : flats) {
            sum += flat.getSquare();
        }
        return sum;
    }

    @Override
    public synchronized void setSpaceByIndex(int number, Space newOffice) {
        flats[number].setRooms(newOffice.getRooms());
        flats[number].setSquare(newOffice.getSquare());
    }

    
    @Override
    public synchronized int compareTo(Floor o) {
        SynchronizedFloor sf = (SynchronizedFloor) o;
        if (this.getNumberOfSpaces() > sf.getNumberOfSpaces()) return 1;
        if (this.getNumberOfSpaces() < sf.getNumberOfSpaces()) return -1;
        return 0;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
