
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
    // winddow components
    private JFrame frame;
    private JPanel panel;
    private JTextArea chatBox;
    private String msg = null;
    private JButton sendButton;
    private JTextArea messageText;
    private JScrollPane scroll;
    
   public ServerConnector(Socket sock) throws IOException {
    server = sock;
    in = new BufferedReader(new InputStreamReader(server.getInputStream()));
    out = new DataOutputStream(server.getOutputStream()); 

    // Chat window creation
    frame = new JFrame();
        
    panel = new JPanel();

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
    ,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    scroll.setBounds(50, 50, 250, 200);

    //frame.getContentPane().add(scroll);

    panel.add(scroll);
    //panel.add(chatBox);

    messageText = new JTextArea();
    sendButton = new JButton("Send");

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    panel.setLayout(null); // use default layout for panel

    messageText.setBounds(100, 350, 165, 50);
    messageText.setLineWrap(true);
    messageText.setWrapStyleWord(true);
    panel.add(messageText); // attach messageText to panel

    sendButton.setBounds(10, 350 ,80,25);
    //sendButton.setText("Send");
    sendButton.addActionListener(this);
    panel.add(sendButton); // attach sendButton to panel


    frame.setSize(350, 500);
    panel.setSize(350, 400);

    frame.add(panel); // attach panel to frame
    
    frame.setTitle("GroupChat");
}

    
    
    @Override
    public void run() {

            
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
                if (msg.equals("quit")) break;
                    
                    chatBox.append(msg + "\n");
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

//throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
}