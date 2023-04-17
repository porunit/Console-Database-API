import executionmanager.NetworkHandler;
import io.ManualControlThread;


public class Server {
    public static void main(String[] args) {
        Thread manualControlThread = new Thread(new ManualControlThread());
        manualControlThread.start();
        NetworkHandler.start();
    }
}