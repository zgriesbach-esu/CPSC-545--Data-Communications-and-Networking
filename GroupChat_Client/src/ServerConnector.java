
//***********************************//
// ServerConnector Class             //
// Author: Zachary Griesbach         //
//                                   //
// Threads which handle client-side  //
// interaction, i.e. submitting      //
// messages and displaying messages  //
// via the GUI                       //
//***********************************//

import java.io.*; 
import java.net.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// core function modified from https: //www.youtube.com/watch?v=ZIzoesrHHQo
// button implementation modified from https: //www.youtube.com/watch?v=-IMys4PCkIA
public class ServerConnector implements Runnable, ActionListener {
    private Socket server;
    private BufferedReader in;
    private DataOutputStream out;

    // window components
    private JFrame frame;
    private JPanel panel;
    private JTextArea chatBox;
    private String msg = null;
    private JButton sendButton;
    private JTextArea messageText;
    private JScrollPane scroll;
    
   // constructor
   public ServerConnector(Socket sock) throws IOException {
    server = sock;

    // connect a buffered reader and a data output stream to the socket passed in
    in = new BufferedReader(new InputStreamReader(server.getInputStream()));
    out = new DataOutputStream(server.getOutputStream()); 

    // chat window creation
    frame = new JFrame();
        
    panel = new JPanel();

    // create the text area that holds the chat conversation
    chatBox = new JTextArea();
    chatBox.setBounds(50, 50, 250, 200);

    // display messages with wrap by word if they are too long
    // do not allow user to edit chatBox
    chatBox.setLineWrap(true);
    chatBox.setWrapStyleWord(true);
    chatBox.setEditable(false); 
    panel.add(chatBox); // attach chatBox to panel

    // create scrollpane to allow chatbox to scroll up and down
    scroll = new JScrollPane(chatBox, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS
    , ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    scroll.setBounds(50, 50, 250, 200);

    panel.add(scroll);

    // create the text area that users type in and button to send that text
    messageText = new JTextArea();
    sendButton = new JButton("Send");

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    panel.setLayout(null); // use default layout for panel

    messageText.setBounds(100, 350, 165, 50);
    messageText.setLineWrap(true);
    messageText.setWrapStyleWord(true);
    panel.add(messageText); // attach messageText to panel

    sendButton.setBounds(10, 350 ,80,25);
    sendButton.addActionListener(this); // give the button functionality on being clicked
    panel.add(sendButton); // attach sendButton to panel


    frame.setSize(350, 500);
    panel.setSize(350, 400);

    frame.add(panel); // attach panel to frame
    
    frame.setTitle("GroupChat");
}

    // thread behavior
    @Override
    public void run() {

            // show the GroupChat window
            frame.setVisible(true);
            while (true) {
                
                
                // receive messages from server and other clients
                try {
                    msg = in.readLine();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (msg.equals("quit")) break;
                    
                    chatBox.append(msg + "\n");
            }
            
                // close socket
                try {
                    server.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        
    }

    // method executes when send button is clicked
    // takes whatever is typed in messageText, not including whitespace or null
    // and resets messageText to be blank
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == sendButton)
        {

            String msg = null;
            msg = messageText.getText();
            messageText.setText("");


            // don't send blank messages or whitespace
            if (!msg.matches("[ ]*"))
            {
                try {
                    out.writeBytes(msg + "\n");
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
    }

}
