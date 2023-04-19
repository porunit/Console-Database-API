import executionmanager.NetworkHandler;
import org.apache.log4j.PropertyConfigurator;

public class UDPClient {
    public static void main(String[] args) {
        String log4jConfPath = "/Users/porunit/Desktop/lab/Prog/Lab6/student-18/Client/src/recources/log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
        NetworkHandler.start();
    }
}