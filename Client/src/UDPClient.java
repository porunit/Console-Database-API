import executionmanager.NetworkHandler;
import org.apache.log4j.PropertyConfigurator;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        String log4jConfPath = "/Users/porunit/Desktop/Study Projects/client2/src/recources/log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
        NetworkHandler.start();
    }
}