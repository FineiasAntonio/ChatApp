package Server.Repository;

import Server.Controller.ChatController;
import Server.Controller.ClientController;
import Server.Entity.ClientEntity;
import Server.Infra.ServerClientDataListener;
import Server.Infra.ServerDataListener;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public class ClientCreationService extends Thread{
    private SocketChannel clientConn;
    private ServerDataListener dataListener;

    public ClientCreationService(SocketChannel clientConn){
        this.clientConn = clientConn;
        dataListener = new ServerClientDataListener(clientConn);
    }

    @Override
    public void run(){
        try {
            String username = dataListener.listen();
            ClientController.enrollClient(new ClientEntity(username, clientConn));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
