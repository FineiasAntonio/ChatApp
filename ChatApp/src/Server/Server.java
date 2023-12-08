package Server;


import Server.Connection.ServerConnectionInitializer;
import Server.Controller.ChatController;

public class Server {

	public static void main(String[] args) {
		ServerConnectionInitializer.init();
		ChatController.initChat();
	}
}