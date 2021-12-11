package by.bsuir.wt3.view;

import java.io.IOException;
import java.net.*;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("Server started.");
		
		ServerSocket serverSocket = new ServerSocket(25000);
		
		while (true)
		{
			Socket clientSocket = serverSocket.accept();
			
			ClientConnection connection = new ClientConnection(clientSocket);
			
			Thread t = new Thread(connection);
			t.start();
		}
	}

}
