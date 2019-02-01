/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.*;
import java.net.*;
import java.util.ArrayList;



/**
 *
 * @author MKhaled
 */
public class ChatServer {
    
    ServerSocket ss ; 

    
    public ChatServer (){
        try {
            ss = new ServerSocket(5551);
        } catch (IOException ex) {
           ex.printStackTrace();
        }
        while(true){
            try { 
                 Socket s = ss.accept() ;
                   new ChatHandler(s);
             
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        new ChatServer(); 
    }
    
 
}
