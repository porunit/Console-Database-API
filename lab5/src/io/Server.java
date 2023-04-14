package io;


import executionmanager.CommandProcessor;
import io.network.ServerInputHandler;
import io.network.ServerOutputHandler;

import java.net.DatagramSocket;
import java.net.InetAddress;


public class Server {
    public static void main(String[] args) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(123);
        ServerInputHandler inputHandler = new ServerInputHandler(serverSocket);
        ServerOutputHandler outputHandler = new ServerOutputHandler(serverSocket, InetAddress.getByName("localhost"));
        outputHandler.setPort(224);
        Thread manualControlThread = new Thread(new ManualControlThread(outputHandler));
        manualControlThread.start();
        while (true) {
            var payload = inputHandler.input();
            CommandProcessor.parse(payload, outputHandler);
        }
    }
}