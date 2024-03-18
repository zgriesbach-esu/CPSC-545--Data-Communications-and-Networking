import java.io.*; 
import java.net.*;
class TTR implements Runnable { 
    private Socket s;
    private DataOutputStream dos;
    private BufferedReader kb;
    private String str;

    public TTR(Socket sock, DataOutputStream dostream, BufferedReader keybrd)
    {
        this.s = sock;
        this.dos = dostream;
        this.kb = keybrd;
    }

    @Override public void run() 
    {
        try {
            while (!(str = kb.readLine()).equals("exit")) { 
  
                // send to the server 
                try {
                    dos.writeBytes(str + "\n");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } 
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}