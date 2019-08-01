import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args)
    {
        try
        {
            ServerSocket server = new ServerSocket(8080);
            while(true)
            {
                Socket client = server.accept();
                new Thread(()->ClientHandler.handleClient(client)).start();
            }
        }
        catch(IOException e)
        {
            System.out.println("There has been an error "+e.getMessage());
        }
    }
}
