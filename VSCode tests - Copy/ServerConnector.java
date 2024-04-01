import java.io.*; 
import java.net.*; 

// Core function from https://www.youtube.com/watch?v=ZIzoesrHHQo
public class ServerConnector implements Runnable{
    private Socket server;
    private BufferedReader in;
   // private PrintStream out;
    
   public ServerConnector(Socket sock) throws IOException {
    server = sock;
    in = new BufferedReader(new InputStreamReader(server.getInputStream()));
  //  out = new PrintStream(server.getOutputStream());
}
    
    @Override
    public void run() {
        while (true) {
            String msg = null;
            try {
                msg = in.readLine();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // Attempting to exit gracefully
            if (msg.equals("quit"))
            {
                break;
            }
            System.out.println(msg);

        }
        // Close socket
        try {
            server.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    }


}
