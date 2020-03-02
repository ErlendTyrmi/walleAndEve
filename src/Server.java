// Source: https://github.com/eugenp/tutorials/blob/master/core-java-modules/core-java-networking/src/main/java/com/baeldung/socket/EchoMultiServer.java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author erlend
 */
public class Server {
    public static int PORT = 5555;
    private ServerSocket serverSocket;

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.startServer(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Boot up the server
     *
     * @param port this must match the client's port number
     * @throws IOException
     */
    public void startServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started.");

        // Listen for new connections forever
        while (true) {
            // Reply in a new thread
            new RequestHandler(serverSocket.accept()).start();
        }
    }

    /**
     * Stops the server
     *
     * @throws IOException
     */
    public void stopServer() throws IOException {
        serverSocket.close();
        System.out.println("Laptop Server stopped");
    }

    /**
     * Class to handle incoming messages in new threads.
     */
    private static class RequestHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public RequestHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                // DataOutputStream dos = new DataOutputStream(new FileOutputStream("foo.dat"))
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));

                String receivedMessage;
                while ((receivedMessage = in.readLine()) != null) {
                    if ("Hello EV3".equals(receivedMessage)) {
                        // Method call
                        out.println("Hello WALL-E");
                    } else {
                        // Alternative action
                        out.println("Spork?");
                    }
                }

                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException i) {
                i.printStackTrace();
            }
        }
    }
}

