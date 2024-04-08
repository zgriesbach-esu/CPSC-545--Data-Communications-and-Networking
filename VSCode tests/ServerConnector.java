
import java.io.*; 
import java.net.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Core function from https://www.youtube.com/watch?v=ZIzoesrHHQo
public class ServerConnector implements Runnable, ActionListener {
    private Socket server;
    private BufferedReader in;
    private String msg = null;
    JButton sendButton;
    JTextField messageText;
   // private PrintStream out;

  
    
    
   public ServerConnector(Socket sock) throws IOException {
    server = sock;
    in = new BufferedReader(new InputStreamReader(server.getInputStream()));
  //  out = new PrintStream(server.getOutputStream());
}

    
    
    @Override
    public void run() {

        JFrame frame = new JFrame();
    
        JPanel panel = new JPanel();
        
        messageText = new JTextField();
        sendButton = new JButton();
       
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null);

        messageText.setBounds(100, 100, 165, 25);
        panel.add(messageText);

        sendButton.setBounds(10, 100 ,80,25);
        sendButton.setText("Send");
        sendButton.addActionListener(this);
        panel.add(sendButton);


        frame.setSize(350, 400);
        panel.setSize(350, 400);

        frame.add(panel);
        //frame.add(messageText);
        //frame.add(sendButton);
        frame.setVisible(true);

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
            messageText.setText("button clicked");
        }
        //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}
