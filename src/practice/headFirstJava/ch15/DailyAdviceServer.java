package practice.headFirstJava.ch15;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DailyAdviceServer {
    String[] adviceList = {"Take smaller bites", "Go for the tight jeans. No they do NOT make you look fat",
    "One word: inappropriate", "Just for today, be honest. Tell your boss what you finally think",
    "You might want to rethink that haircut"};

    public void go(){
        try{
            ServerSocket serverSocket = new ServerSocket(4242);
            while(true){
                Socket sock = serverSocket.accept();
                PrintWriter writer = new PrintWriter(sock.getOutputStream());
                String advice = getAdvice();
                writer.println(advice);
                writer.close();
                System.out.println(advice);
            }
        } catch(IOException iex){
            iex.printStackTrace();
        }
    }

    private String getAdvice(){
        int random = (int)(Math.random() * adviceList.length);
        return adviceList[random] ;
    }

    public static void main(String[] args) {
        DailyAdviceServer server = new DailyAdviceServer();
        server.go();
    }
}
