import java.io.IOException;

public class Main {
    // Test class
    public static final int PORT = 5555;
    public static final String IP = "192.168.43.252";


    public static void main(String[] args) {
        Client eve = new Client();
        Boolean failed = false;

        // Start client
        for (int i = 0; i < 10000; i++) {
            try {
                eve.startConnection(IP, PORT);

                String[] messages = {"Hello, EV3"};
                for (String message : messages) {
                    System.out.println("Sending message: " + message);
                    // Prints the server reply:
                    System.out.println("Received: " + eve.sendMessage(message));
                }

                eve.stopConnection();
            } catch (IOException e) {
                e.printStackTrace();
                failed = true;
            }
            try {
                Thread.sleep(20);
            } catch (Exception e) {
                // Ignore
            }
        }
        if (failed){
            System.out.println("FAILED!");
        }
    }
}
