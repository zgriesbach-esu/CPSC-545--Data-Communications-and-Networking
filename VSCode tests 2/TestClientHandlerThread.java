import java.io.*; 
import java.net.*;
import java.util.ArrayList; 

class ClientHandler implements Runnable{
    private Socket clientSock;
    private PrintStream out;
    private BufferedReader in;
    private ArrayList<ClientHandler> clients;

    // Constructor
    public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> cli) throws IOException {
        this.clientSock = clientSocket;
        this.clients = cli;
        in = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
        out = new PrintStream(clientSock.getOutputStream());
    }


    @Override
    public void run() {
        
        try{
                while (true) {
                    // read in message from client
                    String msg = in.readLine();
                    
                    if (msg.equals("quit")) break;
                    //broadcast message to all other clients
                    messageAll(msg);



                }
            
        } catch (IOException e) {
            System.err.println("IOException in client handler");
            e.printStackTrace();
        }
            try {
                clientSock.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

    }
    
        public void messageAll(String msg) {
        for (ClientHandler aClient: clients )
        {
            aClient.out.println(msg);
        }
    }
}