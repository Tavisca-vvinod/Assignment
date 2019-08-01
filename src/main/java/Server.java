import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static private String RESOURCE_PATH = "C:\\Users\\vvinod\\IdeaProjects\\AssignmentHttp\\src\\main\\resources";
    static private String resourceToSend = "";
    public static void main(String[] args)
    {
        try
        {
            ServerSocket server = new ServerSocket(8080);
            while(true)
            {
                Socket client = server.accept();
                new Thread(()->handleClient(client)).start();
            }
        }
        catch(IOException e)
        {
            System.out.println("There has been an error "+e.getMessage());
        }
    }
}
