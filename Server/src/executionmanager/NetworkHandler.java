package executionmanager;

import exceptions.NetworkException;
import io.network.C2SPackage;
import io.network.ServerInputHandler;
import io.network.ServerOutputHandler;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class NetworkHandler {
    static DatagramSocket serverSocket;
    static ServerInputHandler inputHandler;
    static ServerOutputHandler outputHandler;
    static int SERVER_PORT = 228;

    public static void start(){
        try {
            serverSocket = new DatagramSocket(SERVER_PORT);
        } catch (SocketException e) {
            throw new NetworkException("Socket error");
        }
        inputHandler = new ServerInputHandler(serverSocket);
        try {
            outputHandler = new ServerOutputHandler(serverSocket, InetAddress.getByName("localhost"));
        } catch (UnknownHostException e) {
            throw new NetworkException("Host error");
        }
        for(;;){
            try {
                requestHandling();
            } catch (IOException | ClassNotFoundException e) {
                throw new NetworkException("Error while handling request");
            }
        }
    }
    private static void requestHandling() throws IOException, ClassNotFoundException {
        C2SPackage request = inputHandler.input();
        outputHandler.setPort(inputHandler.getLastPort());
        outputHandler.setIP(inputHandler.getLastIP());
        CommandProcessor.parse(request, outputHandler);
    }
}
