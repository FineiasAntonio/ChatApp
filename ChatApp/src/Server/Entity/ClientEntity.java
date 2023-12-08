package Server.Entity;

import Server.Controller.ChatController;
import Server.Controller.ClientController;
import Server.Controller.MessagesController;
import lombok.Data;
import lombok.Getter;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Data
public class ClientEntity {
    private String username;
    private SocketChannel clientConn;
    private ClientDataReciever clientDataReciever;



    public ClientEntity(String username, SocketChannel clientConn) {
        this.username = username;
        this.clientConn = clientConn;

        this.clientDataReciever = new ClientDataReciever(this);

        clientDataReciever.start();

        {
            if (!ChatController.isHaveUsersOnline()) {
                new ChatController().start();
            }
        }
    }

    private ByteBuffer buffer;
    private byte[] bytes;

    public void returnMessage(MessageEntity msg)  {
        try {
            String message = msg.toString();
            bytes = message.getBytes();

            buffer = ByteBuffer.allocate(bytes.length);
            buffer.put(bytes);
            buffer.flip();

            clientConn.write(buffer);
            buffer.clear();

        } catch (IOException e){
            ClientController.disconnectClient(this);
            e.printStackTrace();
        }
    }
    public void returnMessage(String message)  {
        try {
            bytes = message.getBytes();

            buffer = ByteBuffer.allocate(bytes.length);
            buffer.put(bytes);
            buffer.flip();

            clientConn.write(buffer);
            buffer.clear();

        } catch (IOException e){
            ClientController.disconnectClient(this);
            e.printStackTrace();
        }
    }
}

class ClientDataReciever extends Thread{
    private ByteBuffer buffer;
    private Charset charset = StandardCharsets.UTF_8;
    private ClientEntity clientEntity;
    private SocketChannel clientConn;

    public ClientDataReciever(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
        this.clientConn = clientEntity.getClientConn();
    }

    @Override
    public void run() {
        try {
        while (true) {
            buffer = ByteBuffer.allocate(4096);

            if (clientConn.read(buffer) > 0) {

                buffer.flip();
                String message = charset.decode(buffer).toString();

                MessagesController.queueMessage(new MessageEntity(clientEntity.getUsername(), message));
                buffer = ByteBuffer.allocate(0);
            }

        }
            } catch (IOException e){
                ClientController.disconnectClient(clientEntity);
                e.printStackTrace();
            }
        }
    }


