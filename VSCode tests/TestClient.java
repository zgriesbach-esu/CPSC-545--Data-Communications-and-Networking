// A Java program for a Client
import java.io.*;
import java.net.*;

public class TestClient {
    // initialize socket and input output streams
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
 
    // constructor to put ip address and port
    public TestClient(String address, int port)
    {
        // establish a connection
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");
 
            // takes input from terminal
            input = new DataInputStream(System.in);
 
            // sends output to the socket
            out = new DataOutputStream(
                socket.getOutputStream());
        }
        catch (UnknownHostException u) {
            System.out.println(u);
            return;
        }
        catch (IOException i) {
            System.out.println(i);
            return;
        }
 
        // string to read message from input
        String line = "";
 
        // keep reading until "Over" is input
        while (!line.equals("Over")) {
            try {
                line = input.readLine();
                out.writeUTF(line);
                
            }
            catch (IOException i) {
                System.out.println(i);
            }
           /* try {
            line = input.readUTF();        // Read message from server
                System.out.println(line);   // print message from server
            }
            catch (IOException i){
                System.out.println(i);
            }*/

        }
 
        // close the connection
        try {
            input.close();
            out.close();
            socket.close();
        }
        catch (IOException i) {
            System.out.println(i);
        }
    }
 
    public static void main(String args[])
    {
        TestClient client = new TestClient("127.0.0.1", 5000);
    }
}
