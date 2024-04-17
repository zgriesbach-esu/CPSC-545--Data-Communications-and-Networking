//*******************************//
// Server Class                  //
// Author: Zachary Griesbach     //
//                               //
// Creates ClientHandler threads //
// from a threadpool to service  //
// Clients                       //
//*******************************//

import java.io.*; 
import java.net.*;
import java.util.*; 
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors; 


// Core function modified from https: //www.geeksforgeeks.org/establishing-the-two-way-communication-between-server-and-client-in-java/
// Removed i/o from server and moved it to ClientHandler threads created instead
// Thread creation modified from https: //www.youtube.com/watch?v=ZIzoesrHHQo
class Server { 
    
    public static void main(String args[]) 
        throws Exception 

    { 
        // This queue temporarily stores the sent messages
        Queue<String> msgQueue = new PriorityQueue<String>(); 

        // this arraylist is used to refer to the ClientHandler instances
        final ArrayList<ClientHandler> clientList = new ArrayList<ClientHandler>(); 

        // creating a thread pool ready to be assigned to ClientHandlers
        final ExecutorService pool = Executors.newFixedThreadPool(4); 

        // location of the password file
        File file = new File("src/psswds.txt"); 
        
        // Create arrays for usernames and passwords
        // and shared array for logged in users
        String[] names = new String[10];
        String[] passwords = new String[10];
        String[] loggedIn = new String[10];

        // initialized login list with filler text
        for (int i = 0; i < 10; i++)
        {
            loggedIn[i] = "filler";
        }
        
        // Read in usernames and passwords from psswds.txt
        // Keep the entries paired with each other
        Scanner scn = new Scanner(file);
        int i =0;
        while (scn.hasNext() != false)
        {
            names[i] = scn.next();
            System.out.print(names[i] + " ");
            passwords[i] = scn.next();
            System.out.print(passwords[i] + "\n");
            i++;
        }

        // Create server Socket 
        ServerSocket ss = new ServerSocket(888); 
        
        int threadCount = 0;
        int clientNum = 1;

        while (threadCount < 4) // allow a max of four clients
        {
            Socket s = ss.accept(); // accept socket connection to client (ServerConnector)
            ClientHandler clientThread = new ClientHandler(s, clientList, names, passwords,
             clientNum, msgQueue, loggedIn);
            clientList.add(clientThread); // keep track of each ClientHandler thread
            threadCount++;
            clientNum++;

            pool.execute(clientThread);
        }
               ss.close(); 
               scn.close();
        //     s.close(); 
  
            // terminate application 
            pool.close();
            System.exit(0); 
  
        } // end of while 
    } 
