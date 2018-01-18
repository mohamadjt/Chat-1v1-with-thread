package SimpleChat1;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Formatter;

/**
 * Created by Hamed-Abbaszadeh on 1/4/2018.
 */
public class SenderImp implements Sender , Source
{
    private Socket socket = null;
    private Formatter formatter = null;
    private int port;
    private String host;

    public SenderImp(int port, String host)
    {
        this.port = port;
        this.host = host;
   //     start();
    }

    public void init()
    {
        try
        {
            socket = new Socket(host, port);
            formatter = new Formatter(socket.getOutputStream());
        } catch (IOException e)
        {
           init();
        }
    }

    @Override
    public void send(String string)
    {
        formatter.format(string + "%n").flush();
    }

    @Override
    public void finalize()
    {
        formatter.close();
        try
        {
            socket.close();
        } catch (IOException e)
        {
            System.out.println("can't close");
            e.printStackTrace();
        }
    }
}
