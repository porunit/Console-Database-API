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
    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void start() {
        try {
            serverSocket = new DatagramSocket(SERVER_PORT);
            log.info("Server started");
        } catch (SocketException e) {
            log.error("Socket error");
        }
        ServerInputHandler inputHandler = new ServerInputHandler(serverSocket);

        while (true) {
            try {
                requestHandling(inputHandler);
            } catch (IOException | ClassNotFoundException e) {
                log.error("Error while handling request " + e.getMessage());
            }
        }
    }

    private static void requestHandling(ServerInputHandler inputHandler) throws IOException, ClassNotFoundException {
        C2SPackage request = null;
        while(request == null) {
            request = inputHandler.input();
        }
        log.debug("Payload get (" + request.command() + ") from: " + inputHandler.getLastIP() + ":" + inputHandler.getLastPort());
        C2SPackage finalRequest = request;
        Runnable requestProcessor = () -> {
            ServerOutputHandler outputHandler = new ServerOutputHandler(serverSocket);
            ServerInputHandler inputter = new ServerInputHandler(serverSocket);
            outputHandler.setPort(inputHandler.getLastPort());
            outputHandler.setIP(inputHandler.getLastIP());
            CommandProcessor.parse(finalRequest, outputHandler, inputter);
            log.debug("Payload sent response to: " + inputHandler.getLastIP() + ":" + inputHandler.getLastPort());
        };
        executorService.execute(requestProcessor);
    }

}
