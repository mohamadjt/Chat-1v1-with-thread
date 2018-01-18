package SimpleChat1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

/**
 * Created by Hamed-Abbaszadeh on 1/4/2018.
 */
public class ReceiverImp implements Receiver , Source
{
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private Scanner scanner = null;
    private int port;

    public ReceiverImp(int port)
    {
        this.port = port;
    //    start();
    }

    @Override
    public String receive()
    {
        return scanner.nextLine();
    }

    @Override
    public void finalize()
    {
        scanner.close();
        try
        {
            socket.close();
            serverSocket.close();
        } catch (IOException e)
        {
            System.err.println("finalize failed");
            e.printStackTrace();
        }
    }

    @Override
    public void init()
    {
        try
        {
            serverSocket = new ServerSocket(port);
            socket=serverSocket.accept();
            scanner =new Scanner(socket.getInputStream());
        } catch (IOException e)
        {
            System.err.println("start failed");
            e.printStackTrace();
        }
    }
}
