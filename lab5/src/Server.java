import Client.AddInputHelper;
import executionmanager.CommandProcessor;
import ioserver.ServerInputHandler;
import ioserver.ServerOutputHandler;

import java.net.DatagramSocket;
import java.net.InetAddress;


public class Server {
    public static void main(String[] args) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(123);
        ServerInputHandler inputHandler = new ServerInputHandler(serverSocket);
        ServerOutputHandler outputHandler = new ServerOutputHandler(serverSocket, InetAddress.getByName("localhost"));
        AddInputHelper.setHandler(inputHandler,outputHandler);
        while (true) {
            var command = inputHandler.input();
            outputHandler.setPort(inputHandler.getLastPort());
            System.out.println(command);
            CommandProcessor.parse(command, outputHandler);
        }
    }
}