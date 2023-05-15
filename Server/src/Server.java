import executionmanager.NetworkHandler;
import io.ManualControlThread;
import org.apache.log4j.PropertyConfigurator;


public class Server {
    public static void main(String[] args) {
        String log4jConfPath = "/home/studs/s372839/S/log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
        Thread manualControlThread = new Thread(new ManualControlThread());
        manualControlThread.start();
        NetworkHandler.start();
    }
}