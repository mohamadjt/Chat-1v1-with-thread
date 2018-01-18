package SimpleChat1;

import java.util.Scanner;

/**
 * Created by Hamed-Abbaszadeh on 12/28/2017.
 */
public class Server implements Sender, Receiver,Runnable
{
    private Sender sender = null;
    private Receiver receiver = null;

    public Server(String ip, int senderPort, int receiverPort)
    {
        receiver = new ReceiverImp(receiverPort);
        sender = new SenderImp(senderPort, ip);
    }

    public static void main(String[] args)
    {
        Server server = new Server("localhost", 8282, 7272);
        Scanner scanner = new Scanner(System.in);
        new Thread(server).start();
        String s;
        ((Source) server.sender).init();
        try
        {
            while (true)
            {
                s = scanner.nextLine();
                if (s.equals(".close"))
                {
                    System.err.println("chat closed");
                    break;
                }
                server.send(s);
            }
        }
        catch (Exception e)
        {

        }

        scanner.close();
    }

    @Override
    public String receive()
    {
        return receiver.receive();
    }

    @Override
    public void send(String string)
    {
        sender.send(string);
    }

	@Override
	public void run() {
		 ((Source)this.receiver).init();
		try
        {
            while (true)
            {	String kh=this.receive();
        		if(kh.equals(".close"))
        			break;
                System.out.println("Server says : "+ kh);
            }
        }
        catch(Exception e)
        {
        	System.err.println("error in run");
        }
		
	}
}
