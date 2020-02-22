// Source: https://github.com/eugenp/tutorials/blob/master/core-java-modules/core-java-networking/src/main/java/com/baeldung/socket/EchoMultiServer.java

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class LaptopServer {
    public static int PORT = 5555;
    private ServerSocket serverSocket;

    public static void main(String[] args) {
        LaptopServer server = new LaptopServer();
        try {
            server.startServer(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Laptop Server started");

        // Listen for new connections forever
        while (true) {
            // Reply in a new thread
            new RequestHandler(serverSocket.accept()).start();
        }
    }

    public void stopServer() throws IOException {
        serverSocket.close();
    }

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

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    if ("Hello WALL-E".equals(inputLine)) {
                        out.println("Hello EVE");
                    } else {
                        out.println("1001 1011 1011 1000 0001 1010 1001 1100 " +
                                "1001 0101 0101 1111 0001 1011 1011 1001 " +
                                "1001 1011 1011 1000 0001 1010 1001 1100 " +
                                "1001 0101 0101 1111 0001 1011 1011 1001 " +
                                "1001 1011 1011 1000 0001 1010 1001 1100 " +
                                "1001 0101 0101 1111 0001 1011 1011 1001 " +
                                "1001 1011 1011 1000 0001 1010 1001 1100 " +
                                "1001 0101 0101 1111 0001 1011 1011 1001 " +
                                "1001 1011 1011 1000 0001 1010 1001 1100 " +
                                "1001 0101 0101 1111 0001 1011 1011 1001 " +
                                "1001 1011 1011 1000 0001 1010 1001 1100 " +
                                "1001 0101 0101 1111 0001 1011 1011 1001 " +
                                "1001 1011 1011 1000 0001 1010 1001 1100 " +
                                "1001 0101 0101 1111 0001 1011 1011 1001 " +
                                "1001 1011 1011 1000 0001 1010 1001 1100 " +
                                "1001 0101 0101 1111 0001 1011 1011 1001 " +
                                "1001 1011 1011 1000 0001 1010 1001 1100 " +
                                "1001 0101 0101 1111 0001 1011 1011 1001 " +
                                "1001 1011 1011 1000 0001 1010 1001 1100 " +
                                "1001 0101 0101 1111 0001 1011 1011 1001 " +
                                "1001 1011 1011 1000 0001 1010 1001 1100 " +
                                "1001 0101 0101 1111 0001 1011 1011 1001 " +
                                "1001 1011 1011 1000 0001 1010 1001 1100 " +
                                "1001 0101 0101 1111 0001 1011 1011 1001 " +
                                "Spork?");
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

