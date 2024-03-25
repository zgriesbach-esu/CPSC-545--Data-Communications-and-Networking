import java.io.*; 
import java.net.*;
class TTR implements Runnable { 
    private Socket s;
    private DataOutputStream dos;
    private BufferedReader kb;
    private BufferedReader br;
    private String str1;

    public TTR(Socket sock, DataOutputStream dostream, BufferedReader keybrd, BufferedReader buffr)
    {
        this.s = sock;
        this.dos = dostream;
        this.kb = keybrd;
        this.br = buffr;
    }

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