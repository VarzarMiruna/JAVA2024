package home;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameClient extends Thread{
    int playerNr;
    int turn;
    MainFrame frame;
    private final String serverAdress = "127.0.0.1";
    private final int PORT = 2434;
    private boolean running=true;
    @Override
    public void run(){
        try{
            Socket socket = new Socket(serverAdress,PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String request,response;
            Scanner systemIn = new Scanner(System.in);
            while(running){
                if(turn==playerNr){
                    request = systemIn.nextLine();
                    if(request.equals("exit"))
                        running=false;
                    out.println(request);
                }
                response=null;
                while(response==null)
                    response = in.readLine();
                System.out.println(response);
                System.out.flush();
                if(response.length()>=6 && response.substring(0,6).equals("Player")){
                    turn=playerNr=0;
                }
                else if(response.length()>=28 && response.substring(0,28).equals("Game started! You are Player")){
                    playerNr=response.charAt(28)-'0';
                    frame = new MainFrame();
                    frame.setVisible(true);
                    turn=1;
                }
                else if(response.length()>=7 && response.substring(0,7).equals("Placed ")){
                    response=response.substring(7);
                    String parts[] = response.split(" ",2);
                    int lin=parts[0].charAt(0)-'A'+1,col=Integer.parseInt(parts[0].substring(1));
                    Color color = new Color(Integer.parseInt(parts[1]));
                    frame.board.place(lin,col,color);
                    turn=turn%2+1;
                }
            }
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        } catch (IOException ex) {
            Logger.getLogger(GameClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
