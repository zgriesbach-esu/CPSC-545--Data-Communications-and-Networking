
import java.io.*; 
import java.net.*;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.BorderLayout;

// Core function from https://www.youtube.com/watch?v=ZIzoesrHHQo
// Button implementation from https://www.youtube.com/watch?v=-IMys4PCkIA
public class ServerConnector implements Runnable, ActionListener {
    private Socket server;
    private BufferedReader in;
    private DataOutputStream out;
    private String msg = null;
    private JButton sendButton;
    private JTextField messageText;
    private JScrollPane scroll;
   // private PrintStream out;

  
    
    
   public ServerConnector(Socket sock) throws IOException {
    server = sock;
    in = new BufferedReader(new InputStreamReader(server.getInputStream()));
    out = new DataOutputStream(server.getOutputStream()); 
  //  out = new PrintStream(server.getOutputStream());
}

    
    
    @Override
    public void run() {

            // Chat window creation
            JFrame frame = new JFrame();
        
            JPanel panel = new JPanel();

            JTextArea chatBox = new JTextArea();
            chatBox.setBounds(100, 100, 10, 200);
            chatBox.setLineWrap(true);
            panel.add(chatBox); // attach chatBox to panel

            scroll = new JScrollPane(chatBox);

            //frame.getContentPane().add(scroll);

            panel.add(scroll);

            messageText = new JTextField();
            sendButton = new JButton();

            
        
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            panel.setLayout(null); // use default layout for panel

            messageText.setBounds(100, 350, 165, 25);
            panel.add(messageText); // attach messageText to panel

            sendButton.setBounds(10, 350 ,80,25);
            sendButton.setText("Send");
            sendButton.addActionListener(this);
            panel.add(sendButton); // attach sendButton to panel


            frame.setSize(350, 450);
            panel.setSize(350, 400);

            frame.add(panel); // attach panel to frame
            
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
        
        }
        //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }


