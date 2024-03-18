import java.io.*; 
import java.net.*;
class TTW implements Runnable { 
    private Socket s;
    private DataOutputStream dos;
    private BufferedReader kb;
    private String str;

    public TTW(Socket sock, DataOutputStream dostream, BufferedReader keybrd)
    {
        s = sock;
        dos = dostream;
        kb = keybrd;

    }
   /* public static void main(String args[]) 
    { 
  
        // start the thread 
        t.start(); 
 
        // get the name of the thread 
        System.out.println(t.getName());
    } 
    */
    @Override public void run() 
    { 

        try {
            while (!(str = kb.readLine()).equals("exit")) 
            { 
  
                // send to the server 
                try {
                    dos.writeBytes(str + "\n");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } 
            System.out.println("Inside run method"); 
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }
}