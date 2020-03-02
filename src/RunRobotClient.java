import java.io.IOException;
import java.util.Scanner;

public class RunRobotClient {
    // Test class
    public static final int PORT = 5555;
    public static final String IP = "192.168.43.252";


    public static void main(String[] args) {
        Client eve = new Client();
        Boolean failed = false;

        while (true) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("TEST: Press any key to rotate wheels...");
            String input = scanner.nextLine();
            try {
                eve.startConnection(IP, PORT);
                String message = "turn,360";
                System.out.println("Sending message: " + message);
                // Prints the server reply:
                System.out.println("Received: " + eve.sendMessage(message));
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}