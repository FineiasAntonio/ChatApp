package Client;

import Client.HUD.ApplicationHUD.ApplicationHUD;
import lombok.Getter;

import javax.swing.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class User {
    private String username;
    @Getter
    private static SocketChannel clientConn;


    public User(String username){
        this.username = username;
        clientConn = Connection.connect(username);
        new UserListenerService().start();
    }

    public static void send(String message){
        UserSenderService.sendMessage(message);
    }

}

class UserListenerService extends Thread{
    private ByteBuffer buffer = ByteBuffer.allocate(4096);
    private final Charset charset = StandardCharsets.UTF_8;

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(User.getClientConn().isConnected());
                if (User.getClientConn().read(buffer) > 0) {
                    buffer.flip();
                    String message = charset.decode(buffer).toString();
                    ApplicationHUD.getControllerChatHUD().append(message);
                    buffer.clear();
                }

            }
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Disconected", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}

class UserSenderService {
    private static ByteBuffer buffer;

    public static void sendMessage(String message){

        try {
            byte[] messageBytes = message.getBytes();
            buffer = ByteBuffer.allocate(messageBytes.length);
            buffer.put(messageBytes);
            buffer.flip();
            User.getClientConn().write(buffer);
            buffer.clear();

        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Disconected", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}