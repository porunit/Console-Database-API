package executionmanager;

import io.network.C2SPackage;
import io.network.ServerInputHandler;
import io.network.ServerOutputHandler;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class NetworkHandler {
    private static final Logger log = Logger.getLogger(NetworkHandler.class);
    private static DatagramSocket serverSocket;

    private static final int SERVER_PORT = 64999;

    public static void start() {
        try {
            serverSocket = new DatagramSocket(SERVER_PORT);
            log.info("Server started");
        } catch (SocketException e) {
            log.error("Socket error");
        }
        while (true) {
            ServerInputHandler inputHandler = new ServerInputHandler(serverSocket);
            ServerOutputHandler outputHandler = new ServerOutputHandler(serverSocket);
            try {
                    try {
                        requestHandling(inputHandler, outputHandler);
                    } catch (IOException | ClassNotFoundException e) {
                        log.error("Error while handling request");
                    }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    private static void requestHandling(ServerInputHandler inputHandler, ServerOutputHandler outputHandler) throws IOException, ClassNotFoundException {
        C2SPackage request = inputHandler.input();
        log.debug("Payload get (" + request.command() + ") from: " + inputHandler.getLastIP() + ":" + inputHandler.getLastPort());
        outputHandler.setPort(inputHandler.getLastPort());
        outputHandler.setIP(inputHandler.getLastIP());
        CommandProcessor.parse(request, outputHandler, inputHandler);
        log.debug("Payload sent response to: " + inputHandler.getLastIP() + ":" + inputHandler.getLastPort());
    }
}
