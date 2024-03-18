import java.io.*; 
import java.net.*;
class TTW implements Runnable { 
    private Socket s;
    private DataOutputStream dos;
    // private BufferedReader br;
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
                while (!(str = kb.readLine()).equals("exit")) {
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
