package io.network;

import java.net.DatagramSocket;
import java.net.InetAddress;


public class NetworkHandler {
    static DatagramSocket serverSocket;
    static ServerInputHandler inputHandler;
    static ServerOutputHandler outputHandler;
    static int SERVER_PORT = 228;

    public static void start() throws Exception {
        serverSocket = new DatagramSocket(SERVER_PORT);
        inputHandler = new ServerInputHandler(serverSocket);
        outputHandler = new ServerOutputHandler(serverSocket, InetAddress.getByName("localhost"));
        outputHandler.setPort(224);
        for(;;){
            C2SPackage request = inputHandler.input();
            
        }
    }
}
