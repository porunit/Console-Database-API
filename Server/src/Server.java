import executionmanager.CommandProcessor;
import executionmanager.NetworkHandler;
import io.ManualControlThread;
import io.network.ServerInputHandler;
import io.network.ServerOutputHandler;

import java.net.DatagramSocket;
import java.net.InetAddress;


public class Server {
    public static void main(String[] args) throws Exception {
        Thread manualControlThread = new Thread(new ManualControlThread());
        manualControlThread.start();
        NetworkHandler.start();
    }
}