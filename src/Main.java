import java.io.IOException;

public class Main {
    public static final int PORT = 5555;
    public static final String IP = "127.0.0.1";


    public static void main(String[] args) {
        BotClient eve = new BotClient();
        Boolean failed = false;

        // Start client
        for (int i = 0; i < 10000; i++) {
            try {
                eve.startConnection(IP, PORT);

                String[] messages = {"Hello WALL-E", "Something else"};
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
                Thread.sleep(100);
            } catch (Exception e) {
                // Ignore
            }
        }
        if (failed){
            System.out.println("FAILED!");
        }
    }
}
