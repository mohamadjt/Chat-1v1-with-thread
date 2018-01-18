package SimpleChat1;

import java.io.IOException;
import java.util.Scanner;

public class Client implements Sender, Receiver, Runnable {
	public Sender sender;
	private Receiver receiver;

	public Client(int senderPort, int receiverPort, String ip) {
		receiver = new ReceiverImp(receiverPort);
		sender = new SenderImp(senderPort, ip);
	}

	public static void main(String[] args) throws IOException {
		Client client = new Client( 8482,7472, "localhost");
		Scanner scanner = new Scanner(System.in);
		new Thread(client).start();
		((Source) client.sender).init();
		String s;
		try {
			while (true) {
				s = scanner.nextLine();
				client.send(s);
				if (s.equals(".close")) {
					break;
				}

			}
		} catch (Exception e) {
		}
		scanner.close();
	}

	public void close() {
	}

	@Override
	public String receive() {
		return receiver.receive();
	}

	@Override
	public void send(String string) {
		sender.send(string);
	}

	@Override
	public void run() {
		((Source) this.receiver).init();
		try {
			while (true) {
				String s = this.receive();
				if (s.equals(".close"))
					break;
				System.out.println("Server says : " + s);
			}
		} catch (Exception e) {
			System.err.println("error in run");
		}

	}
}
