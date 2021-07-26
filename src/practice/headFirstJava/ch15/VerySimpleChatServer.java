package practice.headFirstJava.ch15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class VerySimpleChatServer {

    ArrayList<PrintWriter> clientOutputStream;

    public class ClientHandler implements Runnable{
        BufferedReader reader;
        Socket sock;

        public ClientHandler( Socket clientSocket ){
            try{
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream() );
                reader = new BufferedReader( isReader );
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }

        public void run(){
            String message;
            try{
                while( (message = reader.readLine()) != null ){
                    System.out.println("read "+ message);
                    tellEveryone( message );
                }
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }// end: inner class ClientHandler

    public static void main(String[] args) {
        new VerySimpleChatServer().go();
    }

    public void go(){
        clientOutputStream = new ArrayList<>();
        try{
            ServerSocket serverSock = new ServerSocket(5000);

            while(true){
                Socket clientSocket = serverSock.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientOutputStream.add(writer);

                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
                System.out.println("got a connection");
            }
        } catch(Exception ex){
            ex.printStackTrace();

        }
    }// end: go()

    public void tellEveryone(String message){
        Iterator<PrintWriter> it = clientOutputStream.iterator();
        while(it.hasNext()){
            try{
                PrintWriter writer = it.next();
                writer.println(message);
                writer.flush();
            } catch( Exception ex ){
                ex.printStackTrace();
            }
        }
    }
}