/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2.buildings.net.client;
import java.net.*;
import java.io.*;
//import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import task2.buildings.Building;
import task2.buildings.Buildings;

/**
 *
 * @author Александр
 */
public class BinaryClient {
    public static String fileA;
    public static String fileB;
    public static String fileC;
    public static final int PORT = 50000;
    
    public static void main(String[] args){              
        try{          
            File file = new File(args[2]);
            Socket socket = new Socket(InetAddress.getLocalHost(), PORT);
            BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());            
            BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
            PrintWriter stdOut = new PrintWriter(new FileWriter(file));                                                             
            FileInputStream bis = new FileInputStream(args[0]);
            BufferedReader bis2 = new BufferedReader(new InputStreamReader((new FileInputStream(args[1]))));            
            //Building input1;
            //bis.read();
            String input2;
            int type;
            while((input2 = bis2.readLine()) != null){
                Building building = Buildings.inputBuilding(bis);                 
                System.out.println(building.toString());
                //input2 = bis2.readLine();
                type = typeToNumber(input2);                                
                Buildings.outputBuilding(building, out); 
                //out.flush();
                //out.write(1);
                out.write(type);
                System.out.println(type);
                //out.flush();
                //out.flush();
            }            
            //bis.close();
            int cost = in.read();
            System.out.println(cost);
            //stdOut.print(in.read());
            socket.close();
        }           
            //int cost = in.read();
            
            //out.close();
            //in.close();            
        catch (IOException ex) {
            Logger.getLogger(BinaryClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static int typeToNumber(String type){
        int number = 0;
        switch (type){
            case "Dwelling" : number = 1; break;
            case "OfficeBuilding" : number = 2; break;
            case "Hotel" : number = 3; break;
            default: break;
        }
        return number;
    }
}
