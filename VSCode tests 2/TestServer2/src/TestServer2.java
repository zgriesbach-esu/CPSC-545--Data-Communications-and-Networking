// Server2 class that 
// receives data and sends data 
  
import java.io.*; 
import java.net.*; 
  
class TestServer2 { 
  
    public static void main(String args[]) 
        throws Exception 
    { 
  
        // Create server Socket 
        ServerSocket ss = new ServerSocket(888); 
  
        // connect it to client socket 
        Socket s = ss.accept(); 
        System.out.println("Connection established"); 
  
        // to send data to the client 
        PrintStream ps 
            = new PrintStream(s.getOutputStream()); 
  
        // to read data coming from the client 
        BufferedReader br 
            = new BufferedReader( 
                new InputStreamReader( 
                    s.getInputStream())); 
  
        // to read data from the keyboard 
        BufferedReader kb 
            = new BufferedReader( 
                new InputStreamReader(System.in)); 
  
        // server executes continuously 
        while (true) { 
  
            String str, str1; 
            String[] messagelog = new String[6];
            int i = 0;
  
            // repeat as long as the client 
            // does not send a null string 
  
            // read from client 
            while ((str = br.readLine()) != null) { 
                System.out.println(str); 
                str1 = kb.readLine(); 
                
                if (i < 5)
                {
                    messagelog[i] = str;
                    i++;
                    messagelog[i] = "Server: " + str1;
                    i++;
                }
                // send to client 
                ps.println("Server: " + str1 + "\n"); 
            } 
  
            // close connection 
            ps.close(); 
            br.close(); 
            kb.close(); 
            ss.close(); 
            s.close(); 
  
            // terminate application 
            System.exit(0); 
  
        } // end of while 
    } 
} 