import java.util.*;
import java.io.*;

public class ChatServer{

    public static void main(String args[]) throws FileNotFoundException{

    // String names[10]
    // String passwords[10]
    // String msgBuffer;
    // int attempts = 0;
    File file = new File("psswds.txt");
    

    String[] names = new String[10];
    String[] passwords = new String[10];
    // String msgBuffer;
    // int attempts = 0;
    
    Scanner scn = new Scanner(file);
    Scanner scnIn = new Scanner(System.in);

    // for (int i = 0; i < 10; i++)
    // Read in list of usernames and passwords from included .txt file into temporary variables;
    // Copy username temp variable to names[i]
    // Copy password temp variable to passwords[i]
    // i++
    // End for loop
    System.out.println("hello");
    int i = 0;
    while (scn.hasNext() != false)
    {
        names[i] = scn.next();
        System.out.print(names[i] + " ");
        passwords[i] = scn.next();
        System.out.print(passwords[i] + "\n");
        i++;
    }
    
    // Wait for Client program(s) to connect to socket of main Server program
    // Create new Server thread upon each Client program connection
    // Connect Server thread socket to Client program socket
    // Disconnect main Server socket from Client socket

    // Prompt Client program for username and password
    
    System.out.print("Please enter username: ");
    scnIn.nextLine();
    System.out.print("Please enter password: ");
    scnIn.nextLine();

    // while (attempts < 3)
    // read input from Client program
    // Compare username and password

    // if (validation == success)
    // Break 

    // else if (validation == failed)
    // loop

    // End loop
    // while (Client has not exited program)

    // Listen for a Client to click “send” button
    // Give the currently active Client the mutex lock

    // 	Critical section

    // Read in message from Client

    // Insert message into the back end of the message queue
    // Write earliest received message to msgBuffer
    // write msgBuffer to socket of receiving client, example "Client 1: <message>”

    // 	End critical section

    // Give the mutex lock to the waiting Client

    // Select next message in message queue

    // loop

    // End loop

    // else if (client has exited program)

    // Join the corresponding server thread
    
    return;
    }
}