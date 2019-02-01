/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author MKhaled
 */
public class ChatHandler extends Thread {
    
    DataInputStream dis ; 
    PrintStream ps ; 
    static ArrayList <ChatHandler> clients = new ArrayList<>() ; 
    static int counter = 0 ; 
    int cid ; 
    
    public ChatHandler (Socket s){
        try { 
            dis = new DataInputStream(s.getInputStream()) ;
            ps = new PrintStream(s.getOutputStream()) ;
            counter++ ; 
            cid = counter ; 
            this.ps.println(cid);
            clients.add(this) ; 
            start();
        } catch (IOException ex) {
           ex.printStackTrace();
        }
    }
    
    @Override
    public void run (){
        String str ; 
        int rid ;
        try { 
           System.out.println("Current id : "+cid);  
            while(true){
           str  = dis.readLine() ;
           rid = Integer.parseInt(dis.readLine()) ; 
           System.out.println("RID : " + rid);
           send(str , rid) ; 
            }
            
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
  public void  send(String str , int rid){
        for (ChatHandler ch : clients){
            if (ch.cid == rid){
               
               ch.ps.println(cid);
               ch.ps.println(str);
           
            }
        }
    }
    
}
