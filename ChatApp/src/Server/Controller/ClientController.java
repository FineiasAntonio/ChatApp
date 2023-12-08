package Server.Controller;

import Server.Entity.ClientEntity;

public class ClientController {
    public static void enrollClient(ClientEntity client){
        ChatController.getConnectedClients().add(client);
        MessagesController.serverBroadcastMessage(client.getUsername() + " joined the chat");
    }

    public static void disconnectClient(ClientEntity client){
        ChatController.getConnectedClients().remove(client);
        MessagesController.serverBroadcastMessage(client.getUsername() + " left the chat");
    }
}
