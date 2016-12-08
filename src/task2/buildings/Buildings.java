/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2.buildings;


import java.io.*;
import java.util.Comparator;
import java.util.Formatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Александр
 * 
 */
public class Buildings {
    private static BuildingFactory bf = new DwellingFactory();
    
    public static void outputBuilding(Building building, OutputStream out) throws IOException{
        DataOutputStream dos = new DataOutputStream(out);
        try{
            dos.writeInt(building.getArrayOfFloors().length);
            for (Floor arrayOfFloor : building.getArrayOfFloors()) {
                dos.writeInt(arrayOfFloor.getNumberOfSpaces());
                for (int i = 0; i < arrayOfFloor.getNumberOfSpaces(); i++){
                    dos.writeInt(arrayOfFloor.getSpaceByIndex(i).getRooms());
                    dos.writeDouble(arrayOfFloor.getSpaceByIndex(i).getSquare());
                }
            }
            dos.writeChar('\n');
        }
        catch(IOException e){
            e.getMessage();
        }            
    }
    public static Building inputBuilding(InputStream in) throws IOException{        
        Building building = null;
        DataInputStream dis = new DataInputStream(in);
        try {
            int num = 0;            
            int sas = dis.readInt();
            Floor[] floors = new Floor[sas];            
            for (int i = 0; i < floors.length; i++){
                floors[i] = createFloor(new DwellingFactory(), dis.readInt());                
                for (int j = num; j < num + floors[i].getArrayOfSpaces().length; j++){                    
                    floors[i].getArrayOfSpaces()[j].setRooms(dis.readInt());                    
                    floors[i].getArrayOfSpaces()[j].setSquare(dis.readDouble());
                }                
            }
            building = createBuilding(new DwellingFactory(), floors);            
            
        }
        catch(IOException e){
            e.getMessage();
        } 
        
        return building;                                               
    }
    public static void writeBuilding(Building building, Writer out){        
        try(PrintWriter pw = new PrintWriter(out)){            
            pw.print(building.getArrayOfFloors().length + " ");
            for (Floor arrayOfFloor : building.getArrayOfFloors()) {
                pw.print(arrayOfFloor.getNumberOfSpaces()+ " ");
                for (int i = 0; i < arrayOfFloor.getNumberOfSpaces(); i++){
                    pw.print(arrayOfFloor.getSpaceByIndex(i).getRooms() + " ");
                    String st = String.valueOf(arrayOfFloor.getSpaceByIndex(i).getSquare());
                    pw.print(st + " ");                        
                } 
                
            }
            pw.println();
            //pw.close();
        }                               
    }
    public static Building readBuilding(Reader in){
            Building building = null;            
            try{                                
                StreamTokenizer st = new StreamTokenizer(in);
                int num = 0;
                while (st.nextToken() != st.TT_NUMBER) {}
                Floor[] floors = new Floor[(int) st.nval];                 
                for (int i = 0; i < floors.length; i++) {
                    while (st.nextToken() != st.TT_NUMBER) {}
                    int size = (int) st.nval;
                    for (int j = 0; j < size; j++) {
                        floors[i] = createFloor(new DwellingFactory(), size);                        
                        while (st.nextToken() != st.TT_NUMBER) {}
                        floors[i].getArrayOfSpaces()[j].setRooms((int) st.nval);
                        while (st.nextToken() != st.TT_NUMBER) {}
                        floors[i].getArrayOfSpaces()[j].setSquare(st.nval);
                    }                    
                }                
                building = createBuilding(new DwellingFactory(), floors);
            } catch (IOException ex) {
            Logger.getLogger(Buildings.class.getName()).log(Level.SEVERE, null, ex);
        }
            return building;
    }
    public static Building readBuilding(Scanner sc){
        Building building = null;
        Floor[] floors = new Floor[sc.nextInt()];
        for (Floor floor : floors) {
            int size = sc.nextInt();
            for (int j = 0; j < size; j++) {
                floor.getArrayOfSpaces()[j].setRooms(sc.nextInt());
                floor.getArrayOfSpaces()[j].setSquare(sc.nextDouble());
            }
        }        
        building = createBuilding(new DwellingFactory(), floors);        
        return building;
    }    
    public static void writeBuildingFormat(Building building, Writer out){
        try(Formatter fmt = new Formatter(new PrintWriter(out))){
            fmt.format("Floors count %d ",building.getArrayOfFloors().length);
            for (Floor arrayOfFloor : building.getArrayOfFloors()){
                fmt.format("Space count %d", arrayOfFloor.getNumberOfSpaces());
                for (int i = 0; i < arrayOfFloor.getNumberOfSpaces(); i++){
                    fmt.format("Rooms %d",arrayOfFloor.getSpaceByIndex(i).getRooms());
                    fmt.format("area %f", arrayOfFloor.getSpaceByIndex(i).getSquare());                    
                }
            }            
        }        
    }
    
    public <T extends Comparable<T>>void sort(T[] generics){
        for (int i = 0; i < generics.length; i++) {        
        T min = generics[i];
        int min_i = i;                    
        for (int j = i+1; j < generics.length; j++) {
            if (generics[j].compareTo(min) == -1) {
                min = generics[j];
                min_i = j;
            }
        }
            if (i != min_i) {
            T tmp = generics[i];
            generics[i] = generics[min_i];
            generics[min_i] = tmp;
            }
        }
    }
    public <T> void sort(T[] generics, Comparator<T> comparator){
        for (int i = 0; i < generics.length; i++){
            int index = i;
            for (int j = i + 1; j < generics.length; j++) {
            if (comparator.compare(generics[j], generics[index]) < 0)
                index = j;
            }
            T temp = generics[i];
            generics[i] = generics[index];
            generics[index] = temp;
        }
    }
    
    public void setBuildingFactory(BuildingFactory bf){
        this.bf = bf;
    }
            
    public static Building createBuilding(BuildingFactory bf, Floor[] floors){
        return bf.createBuilding(floors);
    }
            
    public static Floor createFloor(BuildingFactory bf, int numberOfSpaces){
        return bf.createFloor(numberOfSpaces);
    }
    public Floor synchronizedFloor(Floor floor){
        return new SynchronizedFloor(floor.getArrayOfSpaces());
    }
}
