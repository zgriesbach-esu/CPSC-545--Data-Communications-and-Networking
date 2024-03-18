import java.io.*; 
import java.net.*;
class TTW implements Runnable { 
    private Socket s;
    private DataOutputStream dos;
    private BufferedReader br;
    private BufferedReader kb;
    private String str1;

    public TTW(Socket sock, DataOutputStream dostream, BufferedReader buffr)
    {
        s = sock;
        dos = dostream;
        br = buffr;

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
                while (!(str1 = kb.readLine()).equals("exit")) 
                { 
                    // receive from the server 
                 try {
                    str1 = br.readLine();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } 
  
                System.out.println(str1); 
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
}