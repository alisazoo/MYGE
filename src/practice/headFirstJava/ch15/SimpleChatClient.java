package practice.headFirstJava.ch15;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleChatClient {

    JFrame frame = new JFrame("Ludicrously Simple Chat Client");
    JTextArea incoming;
    JTextField outgoing;
    BufferedReader reader;
    PrintWriter writer;
    Socket sock;

    public static void main(String[] args) {
        SimpleChatClient client = new SimpleChatClient();
        client.go();
    }


    public void go(){
        JPanel mainPanel = new JPanel();

        incoming = new JTextArea(15,50);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);

        JScrollPane qScroller = new JScrollPane(incoming);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        outgoing = new JTextField(20);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener( new SendButtonListener() );

        mainPanel.add(qScroller);
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);

        setUpNetworking();

        Thread readerThread = new Thread( new IncomingReader() );
        readerThread.start();

        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(400,500);
        frame.setVisible(true);
    }

    private void setUpNetworking(){
        try{
            sock = new Socket("localhost", 5000);

            InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(streamReader);

            writer = new PrintWriter(sock.getOutputStream());

            System.out.println("Networking established");
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    public class SendButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                writer.println(outgoing.getText());
                writer.flush();
            } catch(Exception exception){
                exception.printStackTrace();
            }
            outgoing.setText("");
            outgoing.requestFocus();
            frame.repaint();
        }
    }

    public class IncomingReader implements Runnable{
        @Override
        public void run() {
            String message;
            try{
                while( (message = reader.readLine()) != null){
                    System.out.println("read " + message);
                    incoming.append(message + "\n");
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
