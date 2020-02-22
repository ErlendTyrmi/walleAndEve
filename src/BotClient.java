import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * The client is run from the robot, and lets the robot request status and navigation data.
 */
public class BotClient {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    /**
     * Makes a new connection to the server
     *
     * @param ip,   static ip of the machine linked to the camera
     * @param port, must match the servers port
     * @throws IOException
     */
    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    /**
     * Sends a message requesting navigation data or error messages.
     *
     * @param msg a message in string format
     * @return Server reply as String
     * @throws IOException
     */
    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }

    /**
     * Terminates the connection to the server.
     *
     * @throws IOException
     */
    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }


}
