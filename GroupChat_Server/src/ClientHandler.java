
//*******************************//
// ClientHandler Class           //
// Author: Zachary Griesbach     //
//                               //
// Threads which perform user    //
// validation and then process   //
// message i/o requests          //
//*******************************//

import java.io.*; 
import java.net.*;
import java.util.ArrayList;
import java.util.*; 

// core function modified from https: //www.youtube.com/watch?v=ZIzoesrHHQo
class ClientHandler implements Runnable{
    private Socket clientSock;
    private PrintStream out;
    private BufferedReader in;
    private ArrayList<ClientHandler> clients;
    private String[] names;
    private String[] passwords;
    private String[] loggedIn;
    private int clientNumber;
    private Queue<String> messages;

    // constructor
    public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> cli,
     String[] usernames, String[] pwords, int clientNum, 
     Queue<String> msgs, String[] lIn) throws IOException {
        this.clientSock = clientSocket;
        this.clients = cli;
        this.names = usernames;
        this.passwords = pwords;
        this.clientNumber = clientNum;
        this.messages = msgs;
        this.loggedIn = lIn;
        in = new BufferedReader(new InputStreamReader(clientSock.getInputStream())); // reads in from the socket
        out = new PrintStream(clientSock.getOutputStream()); // writes out to the socket
    }

    // thread behavior
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
            
            // If validation succeeds, enter chat loop
            valid = validate(loginName, pWord);
            attempts++;
            if (valid == 1) {

        try{
                // loop until client quits
                while (true) {
                   
                    
                    // read in message from client
                    // label message with username
                    String msg = loginName + ": " + in.readLine();
                        synchronized(messages) {
                            messages.add(msg);

                            // debugging code
                            System.out.println("Writing " + msg);
                        }
                        if (clientSock.isClosed()) break;

                        //broadcast message to all other clients
                        messageAll(msg);

                }

                } catch (IOException e) {
            System.err.println("IOException in client handler");
            e.printStackTrace();
        }
            // try {
            //     clientSock.close();
            // } catch (IOException e) {
            //     // TODO Auto-generated catch block
            //     e.printStackTrace();
            // }
            
        }
        

    }
    // three login attempts fail, limit exceeded
    clients.get(clientNumber - 1).out.println("Too many attempts.");

    }
        
        // send message passed in to all live clients
        public void messageAll(String msg) {
        for (ClientHandler aClient: clients )
        {
            aClient.out.println(msg);
        }

        

    }
        // checks if provided username (name) and password (pass) are a valid pair in psswds.txt
        int validate(String name, String pass) {
            for (int i = 0; i < 5; i++)
		{
            int loginCount = -1;
			if ((name.equals(names[i])) && (pass.equals(passwords[i])))
			{
                for (int j = 0; j < 5; j++) // check that user login has not already been used
                {
                    if(name.equals(loggedIn[j])) // login matches an existing client
                    {
                        out.println("User is already logged in.");
                        return 0;
                    } 
                }
                out.println("Login accepted.\nChat begins.");
                loginCount += clientNumber; // select the appropriate index for this client
                loggedIn[loginCount] = name; // place current client's login name into the array
                
                
			return 1; // name and password match file
			}
		}   
            out.println("Failed to login."); // name and password do not match file
            return 0;
    }

}
