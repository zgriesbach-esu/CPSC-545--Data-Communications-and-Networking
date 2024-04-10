
import java.io.*; 
import java.net.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Core function from https://www.youtube.com/watch?v=ZIzoesrHHQo
// Button implementation from https://www.youtube.com/watch?v=-IMys4PCkIA
public class ServerConnector implements Runnable, ActionListener {
    private Socket server;
    private BufferedReader in;
    private DataOutputStream out;
    private String msg = null;
    private JTextField loginText;
    private JPasswordField passText;
    private JButton loginButton;
    private JButton sendButton;
    private JTextField messageText;
    private String validationStatus;
    private boolean loginPressed = false;
   // private PrintStream out;

  
    
    
   public ServerConnector(Socket sock) throws IOException {
    server = sock;
    in = new BufferedReader(new InputStreamReader(server.getInputStream()));
    out = new DataOutputStream(server.getOutputStream()); 
  //  out = new PrintStream(server.getOutputStream());
}

    
    
    @Override
    public void run() {


        // LOGIN FRAME
        JFrame frame2 = new JFrame("GroupChat Login");
        frame2.setSize(300,300);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel2 = new JPanel();
        panel2.setSize(300,300);
        panel2.setLayout(null);

        loginText = new JTextField();

        passText = new JPasswordField();

        loginButton = new JButton("Log in");
        loginButton.setBounds(10, 200 ,80,25);
        loginButton.addActionListener(this);

        loginText.setBounds(100, 100, 165, 25);
        passText.setBounds(100, 150, 165, 25);

        panel2.add(loginText);
        panel2.add(passText);
        panel2.add(loginButton);

        frame2.add(panel2);
        frame2.setVisible(true);

        int attempts = 0;

        while (attempts < 3)
        {

        
            if (loginPressed == true && validationStatus.equals("Accepted")) {
                    
            

            // LOGIN FRAME END

            JFrame frame = new JFrame();
        
            JPanel panel = new JPanel();

            JTextArea chatBox = new JTextArea();
            chatBox.setBounds(100, 100, 200, 200);
            panel.add(chatBox);
            
            messageText = new JTextField();
            sendButton = new JButton();
        
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            panel.setLayout(null);

            messageText.setBounds(100, 350, 165, 25);
            panel.add(messageText);

            sendButton.setBounds(10, 350 ,80,25);
            sendButton.setText("Send");
            sendButton.addActionListener(this);
            panel.add(sendButton);


            frame.setSize(350, 450);
            panel.setSize(350, 400);

            frame.add(panel);
            //frame.add(messageText);
            //frame.add(sendButton);
            frame.setTitle("GroupChat");
            frame.setVisible(true);
            String chat = null;
            while (true) {
                
                
                // receive messages from server and other clients
                try {
                    msg = in.readLine();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                // Attempting to exit gracefully
                if (msg.equals("quit"))
                {
                    break;
                }
                chat = chat + "\n" + msg;
                chatBox.setText(chat);
                System.out.println(msg);

            }
                }
                else {
                attempts++;    
                }
            
        
}
        // Close socket
        try {
            server.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == sendButton)
        {

            String msg = null;
            msg = messageText.getText();
            messageText.setText("");

            try {
                out.writeBytes(msg + "\n");
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        else if (e.getSource() == loginButton)
        {
            
            String login;
            String pass;
            login = loginText.getText();
            pass = passText.getText();

            try {
                // send login name
                out.writeBytes(login + "\n");
                // send password
                out.writeBytes(pass + "\n");

                // set validation based on response
                validationStatus = in.readLine();
                loginPressed = true;
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }
        //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}
