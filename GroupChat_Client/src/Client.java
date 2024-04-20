
//**********************************//
// Client Class                     //
// Author: Zachary Griesbach        //
//                                  //
// Creates ServerConnector threads  //
// to handle Client i/o             //
//**********************************//
  

import java.net.*;

// core function modified from https: //www.youtube.com/watch?v=ZIzoesrHHQo
class Client { 
  
    public static void main(String args[]) 
        throws Exception 
    { 
        
        // create client socket 
        Socket s = new Socket("localhost", 888);
        
        // create ServerConnector
        ServerConnector serverConn = new ServerConnector(s);
        
        // run ServerConnector thread
        new Thread(serverConn).start();
  
    
        Thread.currentThread().interrupt();
        // close connection.  
        s.close(); 
        } 
    }