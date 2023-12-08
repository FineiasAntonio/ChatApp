package Server.Connection;

import java.io.IOException;

public class ServerConnectionInitializer {

    public static void init(){
        try {
            ServerConnectionService server = ServerConnectionService.getServerInstance();
            server.openConnection(5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
