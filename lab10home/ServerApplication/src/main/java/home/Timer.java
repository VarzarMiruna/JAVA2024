package home;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Timer extends Thread {
    Player player;
    boolean done=false;
    boolean running=false;
    long startedAt;
    public Timer(Player player){
        this.player=player;
    }
    @Override
    public void run(){
        startedAt=System.currentTimeMillis();
        while(!done){
            while(running){
                if(System.currentTimeMillis()-startedAt>60000){
                    running=false;
                    done=true;
                }
            }
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
