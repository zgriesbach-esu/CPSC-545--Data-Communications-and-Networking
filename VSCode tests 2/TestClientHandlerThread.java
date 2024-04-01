import java.io.*; 
import java.net.*;
import java.util.ArrayList;
import java.util.*; 

// Core function from https://www.youtube.com/watch?v=ZIzoesrHHQo
class ClientHandler implements Runnable{
    private Socket clientSock;
    private PrintStream out;
    private BufferedReader in;
    private ArrayList<ClientHandler> clients;
    private String[] names;
    private String[] passwords;
    private int clientNumber;
    private Queue<String> messages;
    // Constructor
    public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> cli,
     String[] usernames, String[] pwords, int clientNum, Queue<String> msgs) throws IOException {
        this.clientSock = clientSocket;
        this.clients = cli;
        this.names = usernames;
        this.passwords = pwords;
        this.clientNumber = clientNum;
        this.messages = msgs;
        in = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
        out = new PrintStream(clientSock.getOutputStream());
        
    }


    @Override
    public void run() {
        int attempts = 0;
        String loginName = "";
        String pWord = ""; 
        

        while (attempts < 3) {

            // // Prompt user for username and password
            clients.get(clientNumber - 1).out.println("Please enter name ");
            try {
                loginName = in.readLine();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }    
            clients.get(clientNumber - 1).out.println("Please enter password ");
            try {
                pWord = in.readLine();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            // If login info is valid, valid == 1, otherwise valid == 0
            int valid = 1;
            
            valid = validate(loginName, pWord);
            attempts++;
            if (valid == 1) {

        try{
               
                

                
                while (true) {
                    // read in message from client
                    
                   
                    String msg = "client " + clientNumber + ": " + in.readLine();
                
                        synchronized(messages) {
                            messages.add(msg);

                            // debugging code
                            System.out.println("Writing " + clientNumber +
                            "'s message: " + msg);
                        }
                        if (msg.equals("quit")) break;
                        //broadcast message to all other clients
                        messageAll(msg);
                        
                         // send all messages available
                   /*  synchronized (messages) {
                        while(messages.size() != 0)
                        {
                            messageAll();
                        }
                }*/

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
        

    }
    clients.get(clientNumber - 1).out.println("Failed to log in");
    // try {
    //     clientSock.close();
    // } catch (IOException e) {
    //     // TODO Auto-generated catch block
    //     e.printStackTrace();
    // }

    }
        // Trying removing direct access to msg in favor of remove()
        public void messageAll(String msg) {
        for (ClientHandler aClient: clients )
        {
            // String qMsg = messages.remove();
            // Send message to all with sender's number attached
            aClient.out.println(msg);
          
        }

        

    }
        // Checks if provided username and password are a valid pair
        int validate(String name, String pass) {

            for (int i = 0; i < 2; i++)
		{
            
			if ((name.equals(names[i])) && (pass.equals(passwords[i])))
			{
			return 1; // Name and password match
			}
		}
            return 0;
    }

}