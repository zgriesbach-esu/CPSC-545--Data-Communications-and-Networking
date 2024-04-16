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
    private String lastMsg = "FILLER_TEXT";
    private String[] names;
    private String[] passwords;
    private String[] loggedIn;
    private int clientNumber;
    private Queue<String> messages;

    // Constructor
    public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> cli,
     String[] usernames, String[] pwords, int clientNum, Queue<String> msgs, String[] lIn) throws IOException {
        this.clientSock = clientSocket;
        this.clients = cli;
        this.names = usernames;
        this.passwords = pwords;
        this.clientNumber = clientNum;
        this.messages = msgs;
        this.loggedIn = lIn;
        in = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
        out = new PrintStream(clientSock.getOutputStream());
    }


    @Override
    public void run() {
        int attempts = 0;
        String loginName = "";
        String pWord = ""; 
        

        while (attempts < 3) {


            // prompt user for username and password
            clients.get(clientNumber - 1).out.println("Please enter name.");
            try {
                loginName = in.readLine();
                out.println(loginName);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }    
            clients.get(clientNumber - 1).out.println("Please enter password.");
            try {
                pWord = in.readLine();
                out.println(pWord);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            // if login info is valid, valid == 1, otherwise valid == 0
            int valid = 1;
            
            valid = validate(loginName, pWord);
            attempts++;
            if (valid == 1) {

        try{
                // loop until client quits
                while (!clientSock.isClosed()) {
                   
                    
                    // read in message from client
                    // label message with username
                    String msg = loginName + ": " + in.readLine();
                        synchronized(messages) {
                            messages.add(msg);

                            // debugging code
                            System.out.println("Writing " + msg);
                        }
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
        

    }
    clients.get(clientNumber - 1).out.println("Too many attempts.");
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
        int loginCount = 0;
            for (int i = 0; i < 5; i++)
		{
            
			if ((name.equals(names[i])) && (pass.equals(passwords[i])))
			{
                for (int j = 0; j < 5; j++) // check that user login has not already been used
                {
                    if(name.equals(loggedIn[j]))
                    {
                        out.println("User is already logged in.");
                        return 0;
                    } 
                }
                out.println("Login accepted.\nChat begins");
                loggedIn[loginCount] = name; // track users logged in
                loginCount++;
                
			return 1; // Name and password match
			}
		}
            out.println("Failed to login.");
            return 0;
    }

}