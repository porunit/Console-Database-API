import executionmanager.CommandProcessor;
import ioserver.ServerInputHandler;
import ioserver.ServerOutputHandler;

import java.net.DatagramSocket;
import java.net.InetAddress;


public class Server {
    public static void main(String[] args) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(123);
        ServerInputHandler.setSocket(serverSocket);
        ServerOutputHandler outputHandler = new ServerOutputHandler(serverSocket, InetAddress.getByName("localhost"));
        while (true) {
            var command = ServerInputHandler.input();
            outputHandler.setPort(ServerInputHandler.getLastPort());
            System.out.println(command);
            CommandProcessor.parse(command, outputHandler);
        }
    }
}