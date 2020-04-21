
package chat.client;

import chat.support.BaseMessageClient;

import java.util.Scanner;

/**
 * Клиент
 */
public class Client extends BaseMessageClient {

	public Client(String host, int port) throws Exception {
		super( host, port );
	}
	
	public void onMessage( String message ) {
		System.out.printf( "Message read is -> %s%n", message );
	}

	public static void main(String[] args) throws Exception {

		System.out.println("Prepare client");

		Client client = new Client("localhost", 8080);

		System.out.println("Client is ready");
		Scanner s = new Scanner(System.in);
		boolean loop = true;

		System.out.println("Name: ");
		String name = s.nextLine();

		while (loop) {
			client.writeMessage(String.format("%s: %s", name, s.nextLine()));
		}
	}
	
}
