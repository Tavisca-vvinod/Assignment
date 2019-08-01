import java.io.*;
import java.net.Socket;

public class ClientHandler
{
    static private String RESOURCE_PATH = "C:\\Users\\vvinod\\IdeaProjects\\HttpAssignment\\src\\main\\resources";
    static private String resourceToSend = "";
    static void handleClient(Socket client) {
        int resourceDataByte=-1;
        String metaData =  "Server: My Java HTTP Server : 1.0\n"+"Content-type: text/html\n";
        try {
            InputStreamReader clientReader = new InputStreamReader(client.getInputStream());
            BufferedOutputStream serverToClientWriter = new BufferedOutputStream(client.getOutputStream());
            char[] requestedDataReader=new char[256];
            clientReader.read(requestedDataReader);
            String requestInformation = String.valueOf(requestedDataReader);
            String[] requestInformationArray = requestInformation.split(" ");
            if (requestInformationArray[0].equalsIgnoreCase("GET"))
            {
                resourceToSend = RESOURCE_PATH + "\\" + requestInformationArray[1].substring(1);
                BufferedInputStream resourceReader = null;
                try
                {
                    resourceReader = new BufferedInputStream(new FileInputStream(resourceToSend));
                    metaData = "HTTP/1.1 200 OK" + metaData;
                    System.out.println("FILEFOUND");
                }
                catch (FileNotFoundException f)
                {
                    System.out.println("FILENOTFOUND");
                    metaData = "HTTP/1.1 404 NOT FOUND"+metaData;
                    resourceToSend = RESOURCE_PATH + "\\" + "Error404.html";
                    resourceReader = new BufferedInputStream(new FileInputStream(resourceToSend));
                }
                metaData +=resourceReader.available()+"\n\n";
                serverToClientWriter.write(metaData.getBytes());
                while ((resourceDataByte = resourceReader.read()) != -1)
                {
                    serverToClientWriter.write((byte) resourceDataByte);
                }
                serverToClientWriter.flush();
                resourceReader.close();
                clientReader.close();
                serverToClientWriter.close();
                System.out.println("--------------------------------------------");
                System.out.println("--------------------------------------------");
            }
        }catch (Exception e)
        {
            System.out.println("There has been an error "+e.getMessage());
        }
    }
}
