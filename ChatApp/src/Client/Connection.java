package Client;

import Client.HUD.ApplicationHUD.ApplicationHUD;
import lombok.Getter;

import javax.swing.*;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class Connection {
    @Getter
    private static SocketChannel ClientSocket;

    public static SocketChannel connect(String username){
        try {

            ClientSocket = SocketChannel.open();
            ClientSocket.connect(new InetSocketAddress("localhost", 5000));
            sendUsername(username);
            return ClientSocket;

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Disconected", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        //else
        throw new NullPointerException();
    }

    private static boolean sendUsername(String username){
        try {
            PrintStream senderService = new PrintStream(new BufferedOutputStream(ClientSocket.socket().getOutputStream()), true);
            senderService.println(username);
            return true;
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Disconected", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }
}
