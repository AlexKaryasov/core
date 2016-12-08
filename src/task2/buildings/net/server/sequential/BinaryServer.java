/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2.buildings.net.server.sequential;

import java.io.IOException;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import task2.buildings.Building;
import task2.buildings.Buildings;

/**
 *
 * @author Александр
 */
public class BinaryServer {
    private static final int PORT = 50000;
    
    public static void main(String[] args) throws ClassNotFoundException{
        try {
            System.out.println("start");                        
            ServerSocket socket = new ServerSocket(PORT);
            socket.setSoTimeout(50000);
            Socket client;
            boolean working = true;
            client = socket.accept();
            System.out.println("Connected");
            BufferedOutputStream out = new BufferedOutputStream(client.getOutputStream());
            BufferedInputStream in = new BufferedInputStream(client.getInputStream()); 
            //BufferedReader br = new BufferedReader(new InputStreamReader(in));
            Building input1;
            //br.readLine();
            //System.out.println("Этот метод работает!");
            //String input2;
            //while(working){                                                            
                while((input1 = Buildings.inputBuilding(in)) != null){                          
                    Building building = input1;                       
                    System.out.println(building.toString());    
                    int hceck = in.read();
                    System.out.println(hceck);
                    int cost = setCost(building, hceck);                  
                    out.write(cost);
                    System.out.println(cost);
                    out.flush();
                }
            //}
            //socket.close();
            //client.close();
            } catch (IOException ex) {
            Logger.getLogger(BinaryServer.class.getName()).log(Level.SEVERE, null, ex);        
        } 
    }                    
        private static int setCost(Building building, int number){
            int cost = 0;
            switch (number){
                case 1: cost = (int)building.getSumSquare() * 2000; break;
                case 2 : cost = (int)building.getSumSquare() * 1500; break;
                case 3 : cost = (int)building.getSumSquare() * 1000; break;
                default: break;
            }                  
            return cost;
        }    
}
