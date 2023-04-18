package executionmanager;

import io.network.C2SPackage;
import io.network.ServerInputHandler;
import io.network.ServerOutputHandler;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;


public class NetworkHandler {
    private static final Logger log = Logger.getLogger(NetworkHandler.class);
    static DatagramSocket serverSocket;
    static ServerInputHandler inputHandler;
    static ServerOutputHandler outputHandler;
    static int SERVER_PORT = 228;

    public static void start() {
        try {
            serverSocket = new DatagramSocket(SERVER_PORT);
            log.info("Server started");
        } catch (SocketException e) {
            log.error("Socket error");
        }
        inputHandler = new ServerInputHandler(serverSocket);
        outputHandler = new ServerOutputHandler(serverSocket);
        for (; ; ) {
            try {
                requestHandling();
            } catch (IOException | ClassNotFoundException e) {
                log.error("Error while handling request");
            }
        }
    }

    private static void requestHandling() throws IOException, ClassNotFoundException {
        C2SPackage request = inputHandler.input();
        log.debug("Payload get (" + request.command() + ") from: " + inputHandler.getLastIP() + ":" + inputHandler.getLastPort());
        outputHandler.setPort(inputHandler.getLastPort());
        outputHandler.setIP(inputHandler.getLastIP());
        CommandProcessor.parse(request, outputHandler);
        log.debug("Payload sent response to: " + inputHandler.getLastIP() + ":" + inputHandler.getLastPort());
    }
}
