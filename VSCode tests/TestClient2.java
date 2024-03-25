// Client2 class that 
// sends data and receives also 
  
import java.io.*; 
import java.net.*; 

// Core function: https://www.geeksforgeeks.org/establishing-the-two-way-communication-between-server-and-client-in-java/
class Client2 { 
  
    public static void main(String args[]) 
        throws Exception 
    { 
  
        // Create client socket 
        Socket s = new Socket("localhost", 888);
        
        ServerConnector serverConn = new ServerConnector(s);

        new Thread(serverConn).start();

        // PrintStream out
        //     = new PrintStream(s.getOutputStream());

        // to send data to the server 
        DataOutputStream dos 
            = new DataOutputStream( 
                s.getOutputStream()); 
  
        // to read data coming from the server 
        BufferedReader br 
            = new BufferedReader( 
                new InputStreamReader( 
                    s.getInputStream())); 
  
        // to read data from the keyboard 
        BufferedReader kb 
            = new BufferedReader( 
                new InputStreamReader(System.in)); 
        String str, str1; 

        // TTW writer = new TTW(s, dos, kb);

        // Thread t1 = new Thread(writer);
        // t1.start();

        // TTR reader = new TTR(s, dos, kb, br);

        // Thread t2 = new Thread(reader);
        // t2.start();

        
    //    while (!(str = kb.readLine()).equals("exit")) 
    //    {
    //     // do nothing, allow the threads to run everything
    //    }
        
      // for (int i = 0; i < 5; i++) {
       // repeat as long as exit 
       // is not typed at client 
     while (!(str = kb.readLine()).equals("exit")) { 
    
            // send to the server 
            dos.writeBytes(str + "\n"); 
            

            // receive from the server 
        //     str1 = br.readLine(); 
  
          //  System.out.println(str1); 

            
            
    } 
    
        // close connection. 

        // join the write and read threads
        // t1.join();
        // t2.join();

        dos.close(); 
        br.close(); 
        kb.close(); 
        s.close(); 
        } 
    }