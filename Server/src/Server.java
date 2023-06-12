import commandmanagement.commands.LoadCommand;
import executionmanager.DatabaseHandler;
import executionmanager.DatabaseManager;
import executionmanager.NetworkHandler;
import io.ManualControlThread;
import org.apache.log4j.PropertyConfigurator;


public class Server {
    public static void main(String[] args) {
        String log4jConfPath = "/Users/porunit/Desktop/lab/Prog/Lab6/student-18/Server/src/resources/log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
        DatabaseHandler db= new DatabaseHandler("jdbc:postgresql://localhost:5432/studs","postgres", "358000");
        db.connectToDatabase();
        DatabaseManager.setDbHandler(db);
        Thread manualControlThread = new Thread(new ManualControlThread());
        manualControlThread.start();
        NetworkHandler.start();
    }
}