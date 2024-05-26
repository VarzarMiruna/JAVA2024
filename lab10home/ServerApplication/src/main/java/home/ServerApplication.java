package home;

import java.io.IOException;

public class ServerApplication {

    public static void main(String[] args) {
        try {
            GameServer server = new GameServer();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
//cd /d D:\sem_2\java\LAB\lab10home\ClientApplication\src\main\java\home