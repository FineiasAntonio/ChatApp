package Server.Controller;

import Server.Entity.MessageEntity;

public class MessagesController {
    public static void queueMessage(MessageEntity message){
        ChatController.getMessages().push(message);
    }

    public void broadcastMessage(MessageEntity message){
        ChatController.getConnectedClients().stream() //send the message to all connected clients
                .forEach(ClientEntity -> ClientEntity.returnMessage(message));

        System.out.println("enviou mensagem: " + message);
    }

    public static void serverBroadcastMessage(String message){
        ChatController.getConnectedClients().stream() //send the message to all connected clients
                .forEach(ClientEntity -> ClientEntity.returnMessage(message));

        System.out.println("enviou mensagem: " + message);
    }
}
