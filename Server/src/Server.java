import executionmanager.NetworkHandler;
import io.ManualControlThread;
import org.apache.log4j.PropertyConfigurator;


public class Server {
    public static void main(String[] args) {
        String log4jConfPath = "/Users/porunit/Desktop/lab/Prog/Lab6/student-18/Server/src/recources/log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
        Thread manualControlThread = new Thread(new ManualControlThread());
        manualControlThread.start();
        NetworkHandler.start();
    }
}