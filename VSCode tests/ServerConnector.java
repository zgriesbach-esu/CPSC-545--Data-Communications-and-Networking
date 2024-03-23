
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

            System.out.println(msg);

        }

        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'run'");
    }


}
