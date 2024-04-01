// Server2 class that 
// receives data and sends data 
  
import java.io.*; 
import java.net.*;
import java.util.*; 
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors; 


// Core function: https://www.geeksforgeeks.org/establishing-the-two-way-communication-between-server-and-client-in-java/
class Server2 { 
    
    public static void main(String args[]) 
        throws Exception 

    { 
        Queue<String> msgQueue = new PriorityQueue<String>();
        final ArrayList<ClientHandler> clientList = new ArrayList<ClientHandler>();
        final ExecutorService pool = Executors.newFixedThreadPool(4);

        File file = new File("psswds.txt");
        
        // Create arrays for usernames and passwords
        String[] names = new String[10];
        String[] passwords = new String[10];
        // String msgBuffer;
        // int attempts = 0;
        
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

        while (threadCount < 4)
        {
            Socket s = ss.accept();
            ClientHandler clientThread = new ClientHandler(s, clientList, names, passwords,
             clientNum, msgQueue);
            clientList.add(clientThread);
            threadCount++;
            clientNum++;

            pool.execute(clientThread);
        }




        // // connect it to client socket 
        // Socket s = ss.accept(); 
        // System.out.println("Connection established"); 
  
        // // to send data to the client 
        // PrintStream ps 
        //     = new PrintStream(s.getOutputStream()); 
  
        // // to read data coming from the client 
        // BufferedReader br 
        //     = new BufferedReader( 
        //         new InputStreamReader( 
        //             s.getInputStream())); 
  
        // // to read data from the keyboard 
        // BufferedReader kb 
        //     = new BufferedReader( 
        //         new InputStreamReader(System.in)); 

        // DataOutputStream dos 
        //     = new DataOutputStream( 
        //          s.getOutputStream()); 
  
        // // server executes continuously 
        // while (true) { 
  
        //     String str, str1; 
  
        //     // repeat as long as the client 
        //     // does not send a null string 
  
        //     // read from client 
        //     while ((str = br.readLine()) != null) { 
        //         System.out.println(str); 
        //         str1 = kb.readLine(); 
  
        //         // send to client 
        //         ps.println(str1); 
        //         //dos.writeBytes(str1 + "\n");
        //     } 
  
        //     // close connection 
        //     ps.close(); 
        //     br.close(); 
        //     kb.close(); 
               ss.close(); 
               scn.close();
        //     s.close(); 
  
            // terminate application 
            System.exit(0); 
  
        } // end of while 
    } 
// } 
