package Server.Infra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ServerClientDataListener implements ServerDataListener {
    private SocketChannel conn;

    public ServerClientDataListener(SocketChannel conn) {
        this.conn = conn;
    }

    @Override
    public String listen() throws IOException {
        String username;

        BufferedReader reciever = new BufferedReader(new InputStreamReader(conn.socket().getInputStream()));

        System.out.println("esperando username");

        if ((username = reciever.readLine()) != null) {
            return username;
        }

        return null;
    }
}
