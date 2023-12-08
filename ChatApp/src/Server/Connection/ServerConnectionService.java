package Server.Connection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import Server.Repository.ClientCreationService;

public class ServerConnectionService {

    private ServerSocketChannel server;
    private SocketChannel clientConnection;

    private ServerConnectionService(ServerSocketChannel server){
        this.server = server;
    }

    public static ServerConnectionService getServerInstance() throws IOException {
        return new ServerConnectionService(ServerSocketChannel.open());
    }

    public void openConnection(int port) throws IOException{
        this.server.bind(new InetSocketAddress(port));
        System.out.println("Server started at the port: " + port);

        while (this.server.isOpen()) {
            new ClientCreationService(server.accept()).start();
        }

    }
}
