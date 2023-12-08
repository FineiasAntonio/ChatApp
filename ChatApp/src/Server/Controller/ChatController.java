package Server.Controller;

import Server.Entity.ClientEntity;
import Server.Entity.MessageEntity;
import lombok.Getter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;


public class ChatController extends Thread{

    @Getter
    private static Deque<MessageEntity> messages = new ArrayDeque<>();
    @Getter
    private static Set<ClientEntity> ConnectedClients = new HashSet<>();
    @Getter
    private static ClientController clientController = new ClientController();
    @Getter
    private static MessagesController messageController = new MessagesController();
    @Getter
    private static boolean haveUsersOnline = false;

    public static void initChat(){
        haveUsersOnline = true;
        while (true) {
            synchronized (messages){
                if(!messages.isEmpty()) {
                    messageController.broadcastMessage(messages.poll());
                }
            }
        }
    }

    @Override
    public void run() {
        initChat();
    }
}


